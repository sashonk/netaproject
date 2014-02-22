package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.QuestionEvent;
 
public  class Frame extends Group{
	
	public Frame(final NetaGame ng){
		float width = 980;
		float height = 500;
		 setBounds(20, 150, width, height);

		 Skin skin = ng.getManager().getSkin();
		 Image panelImg = new Image(skin.getPatch("frame"));
		 panelImg.setSize(width, height);
		 addActor(panelImg);
		 Image fillImg = new Image(skin.getPatch("fill"));
		 fillImg.setBounds(30, 30, width-60, height- 60);
		 addActor(fillImg);
		 

			Image levin = new Image(ng.getManager().getAtlas().findRegion("levin"));
			levin.setPosition(50, getHeight()-300);
			addActor(levin);
			Image decalArrow = new Image(ng.getManager().getAtlas().findRegion(abandonTextureName()));
			decalArrow.setPosition(getWidth()-200, 50);
			decalArrow.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.getTarget().getParent().setVisible(false);
									
					ng.getContext().setProperty(ContextProperty.PREPARED, true) ;
					ng.getContext().setProperty(ContextProperty.HALT, false) ;
				
				return false;
				}
			});
			addActor(decalArrow);
		 
			content(ng);
	}

	public String abandonTextureName(){
		return "decalArrow";
	}
	
	public  void content(NetaGame ng){};
}
