package com.me.neta;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Barrier extends Group{
	private boolean opened;
	Image axisImg;
	Image boomImg ;
	float k = 0.25f;
	
	public Barrier(NetaGame ng){
		opened = false;
		
		TextureRegion axis = ng.getManager().getAtlas().findRegion("axis");
		 axisImg = new Image(axis);
		axisImg.setSize(42*k, 124*k);
		addActor(axisImg);
		
		TextureRegion boom = ng.getManager().getAtlas().findRegion("boom");
		 boomImg = new Image(boom);
		boomImg.setSize(270*k, 24*k);		
		addActor(boomImg);
		boomImg.setPosition(-15*k, 80*k);
		boomImg.setOrigin(48*k, 12*k);
	}

	public void open(){
		if(!opened){
			boomImg.addAction(Actions.rotateBy(45, 0.3f));
			opened = true;
		}
	}
	
	public void close(){
		boomImg.setRotation(0);
		opened = false;
	}
	
	public boolean isOpened(){
		return opened;
	}
}
