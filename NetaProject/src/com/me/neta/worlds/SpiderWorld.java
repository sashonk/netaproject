package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Hero;
import com.me.neta.MetricListener;
import com.me.neta.NetaGame;
import com.me.neta.Spider;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;
import com.me.neta.events.TextChanged;

public class SpiderWorld extends World{

	public SpiderWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
		setColor(new Color(186/255f, 179/255f, 213/255f, 1));
	}
	
	public String getAuthors(){
		return "Владимир Орлов";
	}
	public String getPassportColor(){
		return "spider";
	}
	
	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("паучок", "title", new String[]{"red", "orange", "yellow", "green", "blue", "green"}, ng.getManager().getSkin());
	}

	@Override
	public String getTitle() {
		return "spider";
	}
	@Override
	public void populateBackground() {	
		Image PAUTINA = new Image(ng.getManager().getAtlas().findRegion("PAUTINA"));
		PAUTINA.setBounds(1024-304, 768-257,304, 257);
		addActor(PAUTINA);
	
		Image flower = new Image(ng.getManager().getAtlas().findRegion("ZVET3"));
		flower.setBounds(875, 340,57, 73);
		addActor(flower);
	}
	public void populateForeground() {
	
		Hero muha = new Hero(ng,"MUH");
		muha.setBounds(550, 560,53,31);
		muha.setZIndex(9);
		addActor(muha);
		
		Spider spider = new Spider(ng);
		spider.setPosition(100, 250);
		spider.setZIndex(10);
		addActor(spider);		
	}

	@Override
	public boolean showPassport(){
		return false;
	}

}
