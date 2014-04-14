package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.LogicLabelClickEvent;
import com.me.neta.figures.Letter;

public class CellarGroup extends Group{
	NetaGame theGame;
	int ID;
	boolean isDone;
	Vector2 groupOrigin;
	float zoom;
	boolean enabled;
	public CellarGroup(NetaGame ng, int id){
		theGame = ng;
		ID= id;		
	}
	
	public int getOrder(){
		return ID;
	}
	
	public boolean  isDone(){
		return isDone;
	}
	
	public void setDone(boolean value){
		isDone = value;
	}
	
	public void setEnabled(boolean value){
		enabled = value;
		
		if(!enabled){
			this.addAction(Actions.alpha(0.5f));
		}
		else{
			this.addAction(Actions.alpha(1f));
		}
	}
	
	public boolean isEnabled(){
		return enabled;
	}

	public static class LogicLabel extends Table{
		public LogicLabel(final NetaGame ng, String text, String styleSuff){
			defaults().padRight(1);
			for(int i=0; i<text.length();i++){
				final char c = text.charAt(i);
				final Label lab = new Label(new String(new char[]{c}), ng.getManager().getSkin(), "small-"+styleSuff);
				
				lab.addListener(new InputListener(){
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						if(ng.getContext().getProperty(ContextProperty.INGAME)==null){
							return false;
						}
						
						event.getListenerActor().fire(new LogicLabelClickEvent(c));
											
						return true;
					}
				});
				
				add(lab);
				
			}
			pack();

		}
	}
	
	public static class LogicFlower extends Group{
		
		int order;
		boolean isDone;
		LogicFlower next;
		CellarGroup cellarGroup;
		String info;
		
		public CellarGroup getGroup(){
			return cellarGroup;
		}
		
		public int getOrder(){
			return order;
		}
		
		public void setOrder(int value){
			order = value;
		}
		
		public boolean  isDone(){
			return isDone;
		}
		
		public void setDone(boolean value){
			isDone = value;
		}


		public Letter getLetter(){
			return (Letter) findActor("letter");
		}
		
		public void setLetter(Letter lt){
			Actor letter = findActor("letter");
			if(letter!=null){
				removeActor(letter);
			}
			addActor(lt);
			
		}
		
		public String getInfo(){
			return info;
		}
		
		public LogicFlower(NetaGame ng, String info, CellarGroup cg){
			this.info = info;
			cellarGroup= cg;
			TextureRegion reg = ng.getManager().getAtlas().findRegion(info);
			Image img = new Image(reg);
			img.setSize(reg.getRegionWidth(), reg.getRegionHeight());
			setSize(img.getWidth(), img.getHeight());			
			addActor(img);
			
/*			this.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					Actor letter=LogicFlower.this.findActor("letter");
					
					
					return true;
				}
			});*/
		}

	
	}
	
	public void setZoom(float zoom){
		this.zoom = zoom;
	}

	public float getZoom(){
		return zoom;
	}
	
	public void setGroupOrigin(Vector2 value){
		groupOrigin = value;
	}
	
	public Vector2 getGroupOrigin(){
		return groupOrigin;
	}
	
}
