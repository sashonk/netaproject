package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.neta.Ant;
import com.me.neta.Hero;
import com.me.neta.TextureManager;
import com.me.neta.World;

public class AntWorld extends World{

	public AntWorld(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		setColor(new Color(.72f, .86f, .48f, 1));
	}

	@Override
	public Rectangle getAuthorBounds() {
		return new Rectangle(122, 40, 100, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getAgeBounds() {
		return new Rectangle(267, 40, 21, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getCityBounds() {
		return new Rectangle(86, 7, 72, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getStateBounds() {
		return new Rectangle(200, 7, 262-198, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getYearBounds() {
		return new Rectangle(285, 7, 324-285, FIELD_HEIGHT);
	}

	@Override
	public String getTitle() {
		return "ant";
	}

	@Override
	public void populate() {
		Image flower1 = new Image(TextureManager.get().getAtlas().findRegion("ZVET3"));
		flower1.setBounds(162, 80, 57,73);
		addActor(flower1);
		
		Image flower2 = new Image(TextureManager.get().getAtlas().findRegion("ZVET4"));
		flower2.setBounds(900, 80,57,57);
		addActor(flower2);	
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		addActor(zactor);
		

		Hero ant2 = new Hero("ant2");
		ant2.setBounds(800, 260, 55, 115);
		ant2.setZIndex(9);
		addActor(ant2);
		
		Ant ant = new Ant();
		ant.setPosition(100, 250);
		ant.setZIndex(10);
		addActor(ant);		
	}

}
