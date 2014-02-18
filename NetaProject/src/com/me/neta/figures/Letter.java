package com.me.neta.figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.util.ColorHelper;


public class Letter extends AbstractFigure{

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
