package com.me.neta.figures;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.util.ColorHelper;


public class Letter extends AbstractFigure{
	
	public static final Map<Character, String> translit = new HashMap<Character, String>();
	static{
		translit.put(Character.valueOf('а'),"a");
		translit.put(Character.valueOf('б'),"b");
		translit.put(Character.valueOf('в'), "v");
		translit.put(Character.valueOf('г'), "g");
		translit.put(Character.valueOf('д'), "d");
		translit.put(Character.valueOf('е'),"e");
		translit.put(Character.valueOf('ё'), "eo");
		translit.put(Character.valueOf('ж'),"zh");
		translit.put(Character.valueOf('з'), "z");
		translit.put(Character.valueOf('и'),"i");
		translit.put(Character.valueOf('к'), "k");
		translit.put(Character.valueOf('л'), "l");
		translit.put(Character.valueOf('м'),"m");
		translit.put(Character.valueOf('н'), "n");
		translit.put(Character.valueOf('о'), "o");
		translit.put(Character.valueOf('п'),"p");
		translit.put(Character.valueOf('р'), "r");
		translit.put(Character.valueOf('с'), "s");
		
		translit.put(Character.valueOf('С'), "S");
		translit.put(Character.valueOf('Б'), "B");
		translit.put(Character.valueOf('Ж'), "Zh");
		
		translit.put(Character.valueOf('т'), "t");
		translit.put(Character.valueOf('у'), "u");
		translit.put(Character.valueOf('ф'), "f");		
		translit.put(Character.valueOf('х'), "h");
		translit.put(Character.valueOf('ц'), "c");
		translit.put(Character.valueOf('ч'),"ch");
		translit.put(Character.valueOf('ш'), "sh");
		translit.put(Character.valueOf('щ'),"sch");
		translit.put(Character.valueOf('ы'), "y");
		translit.put(Character.valueOf('э'), "ea");
		translit.put(Character.valueOf('ю'), "ju");
		translit.put(Character.valueOf('я'), "ya");
		translit.put(Character.valueOf('й'), "ii");
		translit.put(Character.valueOf('ъ'), "sign_hard");
		translit.put(Character.valueOf('ь'), "sign_soft");


	}
	
	public static final Map<Character, Integer> characterToIdMap = new HashMap<Character, Integer>();
	static {
		characterToIdMap.put(Character.valueOf('а'), Integer.valueOf(25));
		characterToIdMap.put(Character.valueOf('б'), Integer.valueOf(9));
		characterToIdMap.put(Character.valueOf('в'), Integer.valueOf(10));
		characterToIdMap.put(Character.valueOf('г'), Integer.valueOf(11));
		characterToIdMap.put(Character.valueOf('д'), Integer.valueOf(12));
		characterToIdMap.put(Character.valueOf('е'), Integer.valueOf(8));
		characterToIdMap.put(Character.valueOf('ё'), Integer.valueOf(6));
		characterToIdMap.put(Character.valueOf('ж'), Integer.valueOf(27));
		characterToIdMap.put(Character.valueOf('з'), Integer.valueOf(13));
		characterToIdMap.put(Character.valueOf('и'), Integer.valueOf(7));
		characterToIdMap.put(Character.valueOf('к'), Integer.valueOf(14));
		characterToIdMap.put(Character.valueOf('л'), Integer.valueOf(15));
		characterToIdMap.put(Character.valueOf('м'), Integer.valueOf(16));
		characterToIdMap.put(Character.valueOf('н'), Integer.valueOf(17));
		characterToIdMap.put(Character.valueOf('о'), Integer.valueOf(1));
		characterToIdMap.put(Character.valueOf('п'), Integer.valueOf(18));
		characterToIdMap.put(Character.valueOf('р'), Integer.valueOf(28));
		characterToIdMap.put(Character.valueOf('с'), Integer.valueOf(19));
		characterToIdMap.put(Character.valueOf('т'), Integer.valueOf(20));
		characterToIdMap.put(Character.valueOf('у'), Integer.valueOf(2));
		characterToIdMap.put(Character.valueOf('ф'), Integer.valueOf(21));		
		characterToIdMap.put(Character.valueOf('х'), Integer.valueOf(22));
		characterToIdMap.put(Character.valueOf('ц'), Integer.valueOf(23));
		characterToIdMap.put(Character.valueOf('ч'), Integer.valueOf(32));
		characterToIdMap.put(Character.valueOf('ш'), Integer.valueOf(24));
		characterToIdMap.put(Character.valueOf('щ'), Integer.valueOf(29));
		characterToIdMap.put(Character.valueOf('ы'), Integer.valueOf(3));
		characterToIdMap.put(Character.valueOf('э'), Integer.valueOf(4));
		characterToIdMap.put(Character.valueOf('ю'), Integer.valueOf(26));
		characterToIdMap.put(Character.valueOf('я'), Integer.valueOf(5));
		characterToIdMap.put(Character.valueOf('й'), Integer.valueOf(33));
		characterToIdMap.put(Character.valueOf('ъ'), Integer.valueOf(31));
		characterToIdMap.put(Character.valueOf('ь'), Integer.valueOf(30));

		characterToIdMap.put(Character.valueOf('С'), Integer.valueOf(19));
		characterToIdMap.put(Character.valueOf('Ж'), Integer.valueOf(27));
		characterToIdMap.put(Character.valueOf('Б'), Integer.valueOf(9));

	}
	

	@Deprecated
	public static Integer getCharId(Character ch){
		return characterToIdMap.get(ch);
	}

	TextureManager tm;
	char ch;
	
	public static char lookupChar(int id){
		for(Character ch : characterToIdMap.keySet() ){
			if(characterToIdMap.get(ch).equals(Integer.valueOf(id))){
				return ch;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	public Sound getSound(){
		return tm.letterSound(ch);
	}
	

	 Label lb;
	 Label lbWhite;
	 Image circleImg;
	 Image fillCircle;
	 
		public Letter(NetaGame ng, char c){
			this.setSize(30, 30);
			this.setOrigin(15, 15);
			tm = ng.getManager();
			TextureAtlas a = tm.getAtlas();	
			 ch = c;
			 
			// this.setTouchable(Touchable.disabled);
			  lb = new Label(new String(new char[]{ch}), ng.getManager().getSkin(), "letter");
			  lb.setTouchable(Touchable.disabled);
			  lbWhite = new Label(new String(new char[]{ch}), ng.getManager().getSkin(), "letterWhite");
			  lbWhite.setTouchable(Touchable.disabled);

			 // float tx = 15;
			 // float ty = 15;
			//  lb.translate(tx, ty);
			//  lbWhite.translate(tx, ty);
			 this.addActor(lb);
			 this.addActor(lbWhite);
			 Util.center(lb);
			 Util.center(lbWhite);
			 

			 circleImg = new Image(a.findRegion("FIGURA2W"));
			 circleImg.setTouchable(Touchable.disabled);

			 circleImg.setSize(getWidth(), getHeight());
			 this.addActor(circleImg);
			 fillCircle = new Image(tm.getCircle(Color.WHITE, getWidth()));
			 fillCircle.setSize(getWidth(), getHeight());
			 this.addActor(fillCircle);
			 fillCircle.setTouchable(Touchable.disabled);

			 lb.toFront();
			 lbWhite.toFront();
			 
			 unfill();
		}

	 @Deprecated
	public Letter(NetaGame ng, int id){
		this.setSize(30, 30);
		this.setOrigin(15, 15);
		tm = ng.getManager();
		TextureAtlas a = tm.getAtlas();	
		 ch = lookupChar(id);
		 
		// this.setTouchable(Touchable.disabled);
		  lb = new Label(new String(new char[]{Character.toUpperCase(ch)}), ng.getManager().getSkin(), "letter");
		  lb.setTouchable(Touchable.disabled);
		  lbWhite = new Label(new String(new char[]{Character.toUpperCase(ch)}), ng.getManager().getSkin(), "letterWhite");
		  lbWhite.setTouchable(Touchable.disabled);

		 // float tx = 15;
		 // float ty = 15;
		//  lb.translate(tx, ty);
		//  lbWhite.translate(tx, ty);
		 this.addActor(lb);
		 this.addActor(lbWhite);
		 Util.center(lb);
		 Util.center(lbWhite);
		 

		 circleImg = new Image(a.findRegion("FIGURA2W"));
		 circleImg.setTouchable(Touchable.disabled);

		 circleImg.setSize(getWidth(), getHeight());
		 this.addActor(circleImg);
		 fillCircle = new Image(tm.getCircle(Color.WHITE, getWidth()));
		 fillCircle.setSize(getWidth(), getHeight());
		 this.addActor(fillCircle);
		 fillCircle.setTouchable(Touchable.disabled);

		 lb.toFront();
		 lbWhite.toFront();
		 
		 unfill();
	}
	
	public char getCharacter(){
		return ch;
	}
	public void fill(Color c){
		super.setColor(c);
		filled = true;	
		
		circleImg.setVisible(false);
		fillCircle.setColor(c);
		fillCircle.setVisible(true);
		if(ColorHelper.isDark(c)){
			lb.setVisible(false);
			lbWhite.setVisible(true);
		}
		else{
			lbWhite.setVisible(false);
			lb.setVisible(true);
		}
	}
	
	public void unfill(){
		filled = false;
		circleImg.setVisible(true);
		fillCircle.setVisible(false);
		lb.setVisible(true);
		lbWhite.setVisible(false);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		super.oldFashionedDraw(batch, parentAlpha);
	}
	

	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {
		//no-op
	}



	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		//no-op
	}
	

}
