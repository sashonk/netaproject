package com.me.neta.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Hero;
import com.me.neta.NetaGame;
import com.me.neta.Spider;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;

public class SpiderWorld extends World{

	public SpiderWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
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
	public void populateBackground() {
		Actor title = Util.multiColorLabel("ПАУЧОК", "title", new String[]{"yellow", "red", "green", "yellow", "blue", "green"}, ng.getManager().getSkin());
	
		Label description = new Label("Шагалка - искалка", ng.getManager().getSkin(), "green"); 
		Label authorLabel = new Label("Авторы стихов ", ng.getManager().getSkin(), "yellow"); 
		Label authorValue = new Label("Вадим Левин и Рената Муха", ng.getManager().getSkin(), "orange"); 
		Label nameLabel = new Label("Автор иллюстратор ", ng.getManager().getSkin(), "blue");
		Label nameValue = new Label("..................", ng.getManager().getSkin(), "blue");
		Label ageLabel = new Label(", возраст", ng.getManager().getSkin(), "blue");
		Label ageValue = new Label(".....", ng.getManager().getSkin(), "blue");
		Label cityName = new Label("Город (село) ", ng.getManager().getSkin(), "blue");
		Label cityValue = new Label(".......", ng.getManager().getSkin(), "blue");
		Label countryName = new Label(", страна ", ng.getManager().getSkin(), "blue");
		Label countryValue = new Label("........", ng.getManager().getSkin(), "blue");
		Label yearName = new Label(", год ", ng.getManager().getSkin(), "blue");
		Label yearValue = new Label("....", ng.getManager().getSkin(), "blue");
		
		Table table = new Table();
		table.defaults().align(Align.left);
		table.add(title).row();
		table.add(description).row().padBottom(10);
		
		Table authRow = new Table();
		authRow.add(authorLabel);
		authRow.add(authorValue);
		authRow.pack();
		table.add(authRow).row();
		
		Table nameRow = new Table();
		nameRow.add(nameLabel);
		nameRow.add(nameValue);
		nameRow.add(ageLabel);
		nameRow.add(ageValue);
		nameRow.pack();
		table.add(nameRow).row();
		
		Table geoRow = new Table();
		geoRow.add(cityName);
		geoRow.add(cityValue);
		geoRow.add(countryName);
		geoRow.add(countryValue);
		geoRow.add(yearName);
		geoRow.add(yearValue);
		geoRow.pack();
		table.add(geoRow);
		table.pack();
		
		table.setPosition(20, ng.getWorkspace().getHeight()-table.getHeight()-20);
		addActor(table);

		
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
