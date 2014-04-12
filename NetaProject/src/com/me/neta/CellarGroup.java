package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.me.neta.figures.Letter;

public class CellarGroup extends Group{
	NetaGame theGame;
	int ID;
	public CellarGroup(NetaGame ng, int id){
		theGame = ng;
		ID= id;		
	}

	public static class LogicLabel extends Table{
		public LogicLabel(NetaGame ng, String text, String styleSuff){
			defaults().padRight(1);
			for(int i=0; i<text.length();i++){
				char c = text.charAt(i);
				Label lab = new Label(new String(new char[]{c}), ng.getManager().getSkin(), "small-"+styleSuff);
				add(lab);
				
			}
			pack();

		}
	}
	
	public static class LogicFlower extends Group{
		
		public LogicFlower(NetaGame ng, String info){
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
	
	public void setSucceed(){
		Actor actor = findActor("gate");
		
		//gate.open();
	}
	
	public void addFlower(LogicFlower flower){
		addActor(flower);
		flower.addListener(new CoorListener());
	}


	
	
}
