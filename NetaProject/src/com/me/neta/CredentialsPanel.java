package com.me.neta;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class CredentialsPanel extends Group{
	
	BitmapFont wonderFont;
	public CredentialsPanel(NetaGame ng, float x, float y){
		this.setPosition(x, y);
		
		
		
		 wonderFont = ng.getManager().getWonderlandFont();
	}
	
	
	public void draw(SpriteBatch batch, float a){
		wonderFont.draw(batch, "MURAVEY", this.getX(), this.getY());
		
	}
	
	
	public abstract String getTitle();
}
