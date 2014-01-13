package com.me.neta.figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.neta.TextureManager;

public class Circle extends AbstractFigure{
	TextureManager tm;
	float width;
	float height;
	String assetName;
	TextureRegion tr;
	
	public Circle(float width, float height, String assetName){
		tm = TextureManager.get();
		setWidth(width);
		setHeight(height);
		setOrigin(width/2, height/2);
		
		tr = tm.getAtlas().findRegion(assetName);
	}

	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		Color opaque = c.cpy();
		opaque.a = 1;
		Texture tx = tm.getCircle(opaque, Math.max(getWidth(), getHeight()));		
		batch.setColor(1,1,1,c.a*parentAlpha);
		batch.draw(tx, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, tx.getWidth(), tx.getHeight(), false, false);			
	}

	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),  getScaleX(), getScaleY(), getRotation());			
		
	}



}
