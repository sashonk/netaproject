package com.me.neta;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.events.LyricsIconEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.BrushTool;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.ZIndexTool;

public abstract class World extends Group{
	//Pixmap pm;
	//Texture tx;
	ZIndexTool ztool;
	BrushTool btool;
	boolean lyricsAdded = false;
	private int id;
	TextureManager tm;
	private boolean colorize;
	private AbstractFigure selectedActor;
	private Color selectedColor ;
	protected static final float FIELD_HEIGHT= 20;
	
	public AbstractFigure getSelected(){
		return selectedActor;
	}
	
	public void setSelectedActor(AbstractFigure figure){
		selectedActor =figure;
		ztool.setSelectedFigure(figure);

	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setColorizing(boolean value){
		colorize = value;
	}
	
	public void addLyrics(int choice){
		
		
		
		if(lyricsAdded || choice!=id){
			return;
		}
		
		List<Actor> lyrics = LyricsGenerator.getLyrics(choice);
		
		int x = 220;
		int y = 350;
		for(Actor act : lyrics){
			act.setPosition(x, y);
			addActor(act);
			y-=50;
		}
		
		lyricsAdded = true;
	}
	
	public void setSelectedColor(Color c){
		selectedColor = c;
	}
	
	public Color getSelectedColor(){
		return selectedColor;
	}
	
	public World(float width, float height){
		setName(getTitle());
		
		colorize = false;
		selectedColor = Color.WHITE.cpy();
		tm = TextureManager.get();		
		this.setBounds(0, 0, width, height);		


		
		RotateTool r = new RotateTool();
		r.setEnabled(true);
		r.setBounds(492, 700, 40, 40);
		addActor(r);	
		
		ztool = new ZIndexTool();
		ztool.setEnabled(true);
		ztool.setBounds(536, 702, 36, 36);
		addActor(ztool);
		
		
		this.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof ZIndexEvent){
					ZIndexEvent zevent = (ZIndexEvent)event;
					World.this.removeActor(zevent.getAbove());
					World.this.addActorAfter(zevent.getBelow(),zevent.getAbove());
					
					return true;
				}
				
				return false;
			}
		});
		
		
		 btool = new BrushTool();
		btool.setEnabled(true);
		btool.setBounds(536+40, 701, 40, 40);
		addActor(btool);
		
	//	this.addListener(pinch2Zoom);
		this.addListener(new MetricListener());

		
		
		
////////////////////////////////////
/////////// PASSPORT //////////////
////////////////////////////////////
	Group textGroup = new Group();
	textGroup.addCaptureListener(new InputListener(){
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		event.setBubbles(false);
		return false;
	}
	});
	textGroup.setPosition(0, getHeight()-155);
	Image textImg = new Image(TextureManager.get().getAtlas().findRegion("passport-"+getTitle()));
	textImg.setBounds(0, 0, 330, 155);		
	textGroup.addActor(textImg);		
	
	Label author = new Label("", TextureManager.get().getSkin(), getTitle());
	author.setAlignment(Align.center);
	author.setName("author");
	Rectangle authorBounds = getAuthorBounds();
	author.setBounds(authorBounds.x, authorBounds.y, authorBounds.width, authorBounds.height);
	textGroup.addActor(author);		
	
	Label age = new Label("", TextureManager.get().getSkin(), getTitle());
	age.setAlignment(Align.center);
	age.setName("age");			
	Rectangle ageBounds = getAgeBounds();
	age.setBounds(ageBounds.x, ageBounds.y, ageBounds.width, ageBounds.height);
	textGroup.addActor(age);	
	
	Label city = new Label("", TextureManager.get().getSkin(), getTitle());
	city.setAlignment(Align.center);	
	city.setName("city");
	Rectangle cityBounds = getCityBounds();
	city.setBounds(cityBounds.x, cityBounds.y, cityBounds.width, cityBounds.height);
	textGroup.addActor(city);
		
	Label state = new Label("", TextureManager.get().getSkin(), getTitle());
	state.setAlignment(Align.center);	
	state.setName("state");				
	Rectangle stateBounds = getStateBounds();
	state.setBounds(stateBounds.x, stateBounds.y, stateBounds.width, stateBounds.height);
	textGroup.addActor(state);
	
	Label year = new Label("", TextureManager.get().getSkin(), getTitle());
	year.setAlignment(Align.center);	
	year.setName("year");		
	Rectangle yearBounds = getYearBounds();
	year.setBounds(yearBounds.x, yearBounds.y, yearBounds.width, yearBounds.height);
	textGroup.addActor(year);
	
	textGroup.setName("passport");
	addActor(textGroup);
	textImg.addListener(new MetricListener());
////////////////////////////////////////////////
////////////////////////////////////////////////


	}
	
	public abstract Rectangle getAuthorBounds();	
	public abstract Rectangle getAgeBounds();
	public abstract Rectangle getCityBounds();
	public abstract Rectangle getStateBounds();
	public abstract Rectangle getYearBounds();

	
	void setPinch2ZoomEnabled(boolean value){
	//	pinch2Zoom.setCanPan(value);
	}
	
	void tintBrush(Color c){
		btool.setColor(c);
	}
	

	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		Color c = this.getColor();
		Texture tx = tm.getUnmanaged(c);
		
		
		batch.setColor(1, 1, 1, c.a* parentAlpha);		
		//batch.draw(tx, getX(), getY(), getWidth(), getHeight());
		batch.draw(tx, getX(), getY(),getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(),0,0,1,1 ,false, false);
		
		super.draw(batch, parentAlpha);
	}
	
	public void setFocus(){
		Group passport = (Group) findActor("passport");
		for(Actor actor : passport.getChildren()){
			if(actor instanceof TextField){
				getStage().setKeyboardFocus(actor);												
				break;
			}
		}
	}
	
	public boolean isColorizing(){
		return colorize;
	}
	
	public void drawPassport(Passport p){
		Group passport = (Group) findActor("passport");
		Label author =  (Label) passport.findActor("author");
		Label age =  (Label) passport.findActor("age");
		Label city =  (Label) passport.findActor("city");
		Label state =  (Label) passport.findActor("state");
		Label year =  (Label) passport.findActor("year");
		
		author.setText(p.name!=null ? p.name : "");
		age.setText(p.age!=null ? p.age.toString() : "");
		city.setText(p.city!=null ? p.city : "");
		state.setText(p.country!=null ? p.country : "");
		year.setText(p.year!=null ? p.year.toString() : "");
	}
	
	public abstract String getTitle();
	
	public Passport getPassport(){
		Group passport = (Group) findActor("passport");
		TextField author =  (TextField) passport.findActor("author");
		TextField age =  (TextField) passport.findActor("age");
		TextField city =  (TextField) passport.findActor("city");
		TextField state =  (TextField) passport.findActor("state");
		TextField year =  (TextField) passport.findActor("year");
		
		pass.year = Integer.parseInt(year.getText());
		pass.age = Integer.parseInt(age.getText());
		pass.name = author.getText();
		pass.city = city.getText();
		pass.country = state.getText();
		
		return pass;
	}
	
	public void setSelectedFigure(AbstractFigure figure){
		ztool.setSelectedFigure(figure);
	}
	
	public abstract void populate();
	
	private Passport pass;
}
