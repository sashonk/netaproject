package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.neta.Hero;
import com.me.neta.Piton;
import com.me.neta.TextureManager;
import com.me.neta.World;

public class PitonWorld extends World{

	public PitonWorld(float width, float height) {
		super(width, height);
		setColor(new Color(182/255f, 221/255f, 200/255f, 1));
	}

	@Override
	public Rectangle getAuthorBounds() {
		return new Rectangle(122, 32, 100, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getAgeBounds() {
		return new Rectangle(263, 33, 21, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getCityBounds() {
		return new Rectangle(86, 6, 72, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getStateBounds() {
		return new Rectangle(200, 6, 262-198, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getYearBounds() {
		return new Rectangle(285, 6, 324-285, FIELD_HEIGHT);
	}

	@Override
	public String getTitle() {
		return "piton";
	}

	@Override
	public void populate() {
		Image flower1 = new Image(TextureManager.get().getAtlas().findRegion("ZVET4"));
		flower1.setBounds(200, 115,57,57);
		addActor(flower1);
/*				
		Image bird = new Image(TextureManager.get().getAtlas().findRegion("POPUGAI"));
		bird.setBounds(10, 50,43,30);*/
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		addActor(zactor);
		
		Piton piton = new Piton();
		piton.setPosition(460, 130);
		piton.setZIndex(10);
		addActor(piton);
		
		Hero bird = new Hero("POPUGAI");
		bird.setBounds(200+10, 115+50,43,30);
		bird.setZIndex(9);
		addActor(bird);		
	}

}
