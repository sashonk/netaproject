package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.neta.Ant;
import com.me.neta.Hero;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;

public class AntWorld extends World{

	public AntWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
		// TODO Auto-generated constructor stub
		setColor(new Color(.72f, .86f, .48f, 1));
	}
	
	public String getPassportColor(){
		return "ant";
	}
	
	public String getAuthors(){
		return "Вадим Левин и Рената Муха";
	}
	
	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("муравей", "title", new String[]{"red", "orange", "yellow", "green", "blue", "green", "orange"}, ng.getManager().getSkin());
	}

	@Override
	protected
	void createCellars(){
		Image cellar1 = new Image(ng.getManager().getAtlas().findRegion("DOM1"));
		cellar1.setBounds(300, 300, 167, 137);
		addActor(cellar1);
	}

	@Override
	public String getTitle() {
		return "ant";
	}

	@Override
	public void populateBackground() {
		Image flower1 = new Image(ng.getManager().getAtlas().findRegion("ZVET3"));
		flower1.setBounds(162, 80, 57,73);
		addActor(flower1);
		
		Image flower2 = new Image(ng.getManager().getAtlas().findRegion("ZVET4"));
		flower2.setBounds(900, 80,57,57);
		addActor(flower2);	
	}
		
	public void populateForeground() {
		Hero ant2 = new Hero(ng, "ant2");
		ant2.setBounds(800, 260, 55, 115);
		ant2.setZIndex(9);
		addActor(ant2);
		
		Ant ant = new Ant(ng);
		ant.setPosition(100, 250);
		ant.setZIndex(10);
		addActor(ant);		
	}

}
