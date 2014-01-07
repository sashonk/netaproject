package com.me.neta.figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.neta.TextureManager;


public class Semicircle extends AbstractFigure{
	TextureManager tm;
	float width;
	float height;
	String assetName;
	TextureRegion tr;
	
	public Semicircle(float width,float height, String assetName){
		setSize(width, height);
		setOrigin(width/2, height/2);
		tm = TextureManager.get();

		
	//	TextureRegion tr0 = tm.getAtlas().findRegion(assetName);
		tr= tm.getAtlas().findRegion(assetName);//new TextureRegion(tr0, tr0.getRegionX(), tr0.getRegionY(), tr0.getRegionWidth(), tr0.getRegionHeight()/2);
	}

	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {
		Texture tx = tm.getCircle(getColor(), Math.max(getWidth(), getHeight()));
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(tx, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, tx.getWidth(), tx.getHeight()/2, false, false);			
	}

	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),  getScaleX(), getScaleY(), getRotation());			
		
	}
}
