package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.neta.Hero;
import com.me.neta.Spider;
import com.me.neta.TextureManager;
import com.me.neta.World;

public class SpiderWorld extends World{

	public SpiderWorld(float width, float height) {
		super(width, height);
		setColor(new Color(186/255f, 179/255f, 213/255f, 1));
	}
	@Override
	public Rectangle getAuthorBounds() {
		return new Rectangle(122, 33, 100, FIELD_HEIGHT);
	}

	@Override
	public Rectangle getAgeBounds() {
		return new Rectangle(260, 33, 21, FIELD_HEIGHT);
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
		return "spider";
	}
	@Override
	public void populate() {
		Image PAUTINA = new Image(TextureManager.get().getAtlas().findRegion("PAUTINA"));
		PAUTINA.setBounds(1024-304, 768-257,304, 257);
		addActor(PAUTINA);

		Image flower = new Image(TextureManager.get().getAtlas().findRegion("ZVET3"));
		flower.setBounds(875, 140,57, 73);
		addActor(flower);
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		addActor(zactor);
		
		Hero muha = new Hero("MUH");
		muha.setBounds(550, 560,53,31);
		muha.setZIndex(9);
		addActor(muha);
		
		Spider spider = new Spider();
		spider.setPosition(100, 250);
		spider.setZIndex(10);
		addActor(spider);		
	}

}
