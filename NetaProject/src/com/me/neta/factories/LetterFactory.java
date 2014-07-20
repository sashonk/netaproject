package com.me.neta.factories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.me.neta.Factory;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.events.DragEvent;
import com.me.neta.events.DragStartEvent;
import com.me.neta.events.DragStopEvent;
import com.me.neta.events.FigureDropEvent;
import com.me.neta.figures.Letter;

public class LetterFactory extends Factory {
	TextureManager tm;
	TextureRegion circle;
	TextureRegion char1;
	
	int padx;
	int pady;
	char _ch;
	
	
	 Label lb;
	 Image circleImg;
	 Image fillCircle;
	public LetterFactory(char ch, NetaGame ng){
		super(ng);
		
		this.setSize(30, 30);

		_ch = ch;
		tm = ng.getManager();
		TextureAtlas a = tm.getAtlas();	
		
		// this.setTouchable(Touchable.disabled);
		  lb = new Label(new String(new char[]{ch}), ng.getManager().getSkin(), "letter");
		  lb.setTouchable(Touchable.disabled);


		 // float tx = 15;
		 // float ty = 15;
		//  lb.translate(tx, ty);
		//  lbWhite.translate(tx, ty);
		 this.addActor(lb);
		 Util.center(lb);
		 

		 circleImg = new Image(a.findRegion("FIGURA2W"));
		 circleImg.setTouchable(Touchable.disabled);
		 circleImg.setSize(getWidth(), getHeight());
		 this.addActor(circleImg);
		 lb.toFront();
		 Util.center(circleImg);

	}
	

	@Deprecated
	public LetterFactory(int id, NetaGame ng){
		super(ng);
	//	this.id = id;
		this.setSize(30, 30);
		 tm = ng.getManager();
		TextureAtlas a = tm.getAtlas();
		
		 circle = a.findRegion("FIGURA2W");
		 char1 = a.findRegion(String.format("CHAR%dW", id));
		 char ch=  Letter.lookupChar(id);
		
		 // "stupid designer" hack
		 padx = (id >=31 && id <=33) ? 4 : 6;
		 pady = (id==29) ? 4 : 6;
	
		 Image circleImg = new Image(a.findRegion("FIGURA2W"));
		 circleImg.setSize(getWidth(), getHeight());
		 addActor(circleImg);
		 
		 
		 Label lb = new Label(new String(new char[]{Character.toUpperCase(ch)}), ng.getManager().getSkin(), "letter");
		 this.addActor(lb);
		 Util.center(lb);

	}
/*	
	public void draw(SpriteBatch batch , float parentAlpha){
		
		batch.draw(circle, getX(), getY(), getWidth(), getHeight());
		batch.draw(char1, getX()+padx, getY()+pady, 18, 19);			
	}
	*/


	@Override
	public Actor createDragActor() {
		Actor drag =new Letter(ng, _ch);
		
		drag.setWidth(30);
		drag.setHeight(30);
		return drag;
	}

	@Override
	public Actor createActor() {

		return new Letter(ng, _ch);

	}

	@Override
	public Actor createInvalidDragActor() {
		return new Label("NO!", tm.getSkin());
	}
}
