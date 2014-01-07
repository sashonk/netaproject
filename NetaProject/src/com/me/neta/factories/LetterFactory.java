package com.me.neta.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.me.neta.Factory;
import com.me.neta.TextureManager;
import com.me.neta.events.DragEvent;
import com.me.neta.events.DragStartEvent;
import com.me.neta.events.DragStopEvent;
import com.me.neta.events.LetterDropEvent;
import com.me.neta.figures.Letter;

public class LetterFactory extends Factory{
	TextureManager tm;
	TextureRegion circle;
	TextureRegion char1;
	int id;
	
	public LetterFactory(int id){
		this.id = id;
		this.setSize(30, 30);
		 tm = TextureManager.get();
		TextureAtlas a = tm.getAtlas();
		
		 circle = a.findRegion("FIGURA2W");
		 char1 = a.findRegion(String.format("CHAR%dW", id));
		

	}
	
	public void draw(SpriteBatch batch , float parentAlpha){

		batch.draw(circle, getX(), getY(), getWidth(), getHeight());
		batch.draw(char1, getX()+6, getY()+6);			
	}
	
	public Actor create(){
		return new Letter(id);
	}

	@Override
	public Actor createDragActor() {
		Actor drag = new Actor(){
			public void draw(SpriteBatch batch, float parentAlpha){
				batch.draw(circle, getX(), getY(), getWidth(), getHeight());
				batch.draw(char1, getX()+6, getY()+6);	
			}
		};
		
		drag.setWidth(30);
		drag.setHeight(30);
		return drag;
	}

	@Override
	public Actor createActor() {
		return new Letter(id);
	}

	@Override
	public Actor createInvalidDragActor() {
		return new Label("NO!", tm.getSkin());
	}
}