package com.me.neta;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class CellarGroup extends Group{
	NetaGame theGame;
	TextureRegion tr;
	public CellarGroup(NetaGame ng){
		theGame = ng;
		
		
		Image cellar1 = new Image(ng.getManager().getAtlas().findRegion("DOM1"));
		cellar1.addListener(new CoorListener());
		cellar1.setBounds(0, 0, 167, 137);
		cellar1.addListener(new MetricListener(){});
		addActor(cellar1);
		

		
	}
	
	
	
	
	public static class LogicLabel extends WidgetGroup{
		public LogicLabel(NetaGame ng, String text){
			Label lab = new Label(text, ng.getManager().getSkin(), "ant");
			addActor(lab);
		}
	}
	
	public static class LogicFlower extends Group{
		
		public LogicFlower(NetaGame ng){
			TextureRegion reg = ng.getManager().getAtlas().findRegion("FLOWER1");
			Image img = new Image(reg);
			img.setSize(66, 66);
			setSize(img.getWidth(), img.getHeight());			
			addActor(img);
			
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
