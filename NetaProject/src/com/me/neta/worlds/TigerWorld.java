package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.neta.Hero;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Tiger;
import com.me.neta.World;

public class TigerWorld extends World{

	public TigerWorld(NetaGame ng, float width, float height) {
		super(ng,width, height);
		setColor(new Color(255/255f, 250/255f, 156/255f, 1));
	}
	@Override
	public Rectangle getAuthorBounds() {
		return new Rectangle(122, 31, 100, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getAgeBounds() {
		return new Rectangle(260, 31, 21, FIELD_HEIGHT);
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
		return "tiger";
	}
	@Override
	public void populate() {
		Image flower1 = new Image(ng.getManager().getAtlas().findRegion("ZVET5"));
		flower1.setBounds(140, 135,78,97);
		addActor(flower1);
		
		
		Image butterfly = new Image(ng.getManager().getAtlas().findRegion("PARPAR"));
		butterfly.setBounds(690, 768-200,38,38);
		addActor(butterfly);
		
		Image flower2 = new Image(ng.getManager().getAtlas().findRegion("ZVET6"));
		flower2.setBounds(820,150,74,48);
		addActor(flower2);
		
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		addActor(zactor);
		
		Hero zebra = new Hero(ng,"ZEBRA");
		zebra.setBounds(420, 170, 93, 72);
		zebra.setZIndex(9);
		addActor(zebra);
	//420 170
				
		Tiger tiger = new Tiger(ng);
		tiger.setPosition(120, 320);
		tiger.setZIndex(10);
		addActor(tiger);		
	}

}
