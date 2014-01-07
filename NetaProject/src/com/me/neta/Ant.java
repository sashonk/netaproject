package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;


public class Ant extends Moveable{
	
	float touchx = 0;
	float touchy = 0;
	float startx = 0;
	float starty = 0;
	
	float dragx= 0;
	float dragy = 0;
	
	TextureRegion tr;
	public Ant(){
		setSize(90, 185);
		AtlasRegion ar = TextureManager.get().getAtlas().findRegion("ant");
		Texture tex = ar.getTexture();
		tr = new TextureRegion(tex, ar.getRegionX(), ar.getRegionY(), ar.getRegionWidth(), ar.getRegionHeight());
		
	
		
		
	setOrigin(getWidth()/2, getHeight()/2);
		
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		batch.setColor(Color.WHITE);
		batch.draw(tr, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public boolean isDisposable() {
		return false;
	}
}
