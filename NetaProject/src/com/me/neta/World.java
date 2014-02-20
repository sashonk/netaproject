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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.me.neta.events.LyricsIconEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.BrushTool;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.ZIndexTool;

public abstract class World extends Group{
	//Pixmap pm;
	//Texture tx;
	BrushTool btool;
	boolean lyricsAdded = false;
	private int id;
	TextureManager tm;
	private boolean colorize;
	private AbstractFigure selectedActor;
	protected NetaGame ng; 
	protected static final float FIELD_HEIGHT= 20;
	
	public AbstractFigure getSelected(){
		return selectedActor;
	}
	
	public void setSelectedActor(AbstractFigure figure){
		selectedActor =figure;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setColorizing(boolean value){
		colorize = value;
	}
	
	public void addLyrics(int choice){
		//TODO
	}
	


	public World(NetaGame ng, float width, float height){
		this.ng = ng;
		setName(getTitle());
		
		colorize = false;
		tm = ng.getManager();		
		this.setBounds(0, 0, width, height);		
		setOrigin(getWidth()/2, getHeight()/2);

		//this.addListener(new MetricListener());

		
		this.addListener(new InputListener(){
			Vector2 startPosition;
			Vector2 mouseXY;
			int pointer;
			Actor actor;
			
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Actor actor = hit(x, y, false);
				if(actor instanceof Moveable ){
					if(actor instanceof AbstractFigure){
						if(selectedActor!=actor){
							return false;
						}
					}
					this.pointer = pointer;
					 startPosition = new Vector2(actor.getX(), actor.getY());
					 mouseXY = new Vector2(x, y);
					 //local.rotate(-actor.getRotation());
					 this.actor = actor;
					return true;
				}

				return false;
			}
			
			public void touchDragged (InputEvent event, float x, float y, int pointer) {
				if( actor!=null){
					actor.setPosition(startPosition.x+x-mouseXY.x,  startPosition.y +y- mouseXY.y );
				}
				
			}
			
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if(pointer==this.pointer){
						actor = null;
					}
				}
		});
		
		
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
	Image textImg = new Image(ng.getManager().getAtlas().findRegion("passport-"+getTitle()));
	textImg.setBounds(0, 0, 330, 155);		
	textGroup.addActor(textImg);		
	
	Label author = new Label("", ng.getManager().getSkin(), getTitle());
	author.setAlignment(Align.center);
	author.setName("author");
	Rectangle authorBounds = getAuthorBounds();
	author.setBounds(authorBounds.x, authorBounds.y, authorBounds.width, authorBounds.height);
	textGroup.addActor(author);		
	
	Label age = new Label("", ng.getManager().getSkin(), getTitle());
	age.setAlignment(Align.center);
	age.setName("age");			
	Rectangle ageBounds = getAgeBounds();
	age.setBounds(ageBounds.x, ageBounds.y, ageBounds.width, ageBounds.height);
	textGroup.addActor(age);	
	
	Label city = new Label("",ng.getManager().getSkin(), getTitle());
	city.setAlignment(Align.center);	
	city.setName("city");
	Rectangle cityBounds = getCityBounds();
	city.setBounds(cityBounds.x, cityBounds.y, cityBounds.width, cityBounds.height);
	textGroup.addActor(city);
		
	Label state = new Label("", ng.getManager().getSkin(), getTitle());
	state.setAlignment(Align.center);	
	state.setName("state");				
	Rectangle stateBounds = getStateBounds();
	state.setBounds(stateBounds.x, stateBounds.y, stateBounds.width, stateBounds.height);
	textGroup.addActor(state);
	
	Label year = new Label("", ng.getManager().getSkin(), getTitle());
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
	

	
	public abstract void populate();
	
	private Passport pass;
}
