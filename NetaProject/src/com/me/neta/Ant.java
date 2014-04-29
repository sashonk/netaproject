package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Ant  extends Actor implements Moveable{
	
	float touchx = 0;
	float touchy = 0;
	float startx = 0;
	float starty = 0;
	
	float dragx= 0;
	float dragy = 0;
	//ShaderProgram sp;
	NetaGame theGame ;
	TextureRegion tr;
	//FrameBuffer fb ;
	public Ant(NetaGame ng){
		theGame = ng;
		setSize(90, 185);
		tr = new TextureRegion(ng.getManager().getAtlas().findRegion("ant")/*ng.getManager().getAssetManager().get("data/ant.pack", TextureAtlas.class).findRegion("antblur")*/);
		//tr.flip(false, true);	
		setOrigin(getWidth()/2, getHeight()/2);
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		batch.setColor(getColor());
		batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public boolean isDisposable() {
		return false;
	}
}
