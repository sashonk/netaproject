package com.me.neta.figures;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.util.ColorHelper;


public class Letter extends AbstractFigure{
	
	static final Map<Character, Integer> characterToIdMap = new HashMap<Character, Integer>();
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


	}
	
	public static Integer getCharId(Character ch){
		return characterToIdMap.get(ch);
	}

	TextureManager tm;
	TextureRegion circle;
	TextureRegion char1;
	TextureRegion char2;
	
	public Letter(NetaGame ng, int id){
		this.setSize(30, 30);
		this.setOrigin(15, 15);
		tm = ng.getManager();
		TextureAtlas a = tm.getAtlas();	
		 circle = a.findRegion("FIGURA2W");
		 char1 = a.findRegion(String.format("CHAR%dW", id));
		 char2 = a.findRegion(String.format("CHAR%dB", id));
	
	}
	




	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		Color opaque = c.cpy();	
		opaque.a = 1;
		Texture coloredCircle = tm.getCircle(opaque, getWidth());
		
		batch.setColor(1,1,1,c.a*parentAlpha);
		
		batch.draw(coloredCircle, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, coloredCircle.getWidth(), coloredCircle.getHeight(), false, false);			
		
		if(ColorHelper.isDark(opaque)){
			batch.draw(char2, getX()+6, getY()+6, 9, 9, char1.getRegionWidth(), char1.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
		}
		else{
			batch.draw(char1, getX()+6, getY()+6, 9, 9, char1.getRegionWidth(), char1.getRegionHeight(), getScaleX(), getScaleY(), getRotation());

		}
		
	}



	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(circle, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());			
		batch.draw(char1, getX()+6, getY()+6, 9, 9, char1.getRegionWidth(), char1.getRegionHeight(), getScaleX(), getScaleY(), getRotation());			
		
	}
	

}
