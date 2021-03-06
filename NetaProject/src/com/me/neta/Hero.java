package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends Actor implements Moveable{
	
	TextureRegion tr;

	public Hero(NetaGame ng, String assetName){
		this.setName("hero"+System.currentTimeMillis());
		AtlasRegion ar = ng.getManager().getAtlas().findRegion(assetName);
		Texture tex = ar.getTexture();
		tr = new TextureRegion(tex, ar.getRegionX(), ar.getRegionY(), ar.getRegionWidth(), ar.getRegionHeight());
		

		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		Color c = getColor();
		batch.setColor(c.r, c.g, c.b, c.a*parentAlpha);
		batch.draw(tr, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public boolean isDisposable() {
		return false;
	}
}
