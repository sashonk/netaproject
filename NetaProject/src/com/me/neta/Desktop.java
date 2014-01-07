package com.me.neta;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.me.neta.events.LyricsIconEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.ZIndexTool;

public class Desktop extends Group{
	Pixmap pm;
	Texture tx;
	ZIndexTool ztool;
	boolean lyricsAdded = false;
	private int id;
	
	public void setId(int id){
		this.id = id;
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
	
	public Desktop(float width, float height){
		
		
		this.setBounds(0, 0, width, height);		
		pm = new Pixmap(1, 1, Format.RGBA8888);
		pm.fill();
		tx = new Texture(pm);		
		
		

		
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
					Desktop.this.removeActor(zevent.getAbove());
					Desktop.this.addActorAfter(zevent.getBelow(),zevent.getAbove());
					
					return true;
				}
				
				return false;
			}
		});
		
		this.addListener(new MetricListener());

	}
	
	void setBackground(Color c){
		pm.setColor(c);
		pm.fill();
		tx.draw(pm, 0, 0);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentAlpha);		
		batch.draw(tx, getX(), getY(), getWidth(), getHeight());
		
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
	
	private Passport pass;
}
