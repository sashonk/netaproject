package com.me.neta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public abstract class AbstractTool extends Actor{
	
	final static float k = .75f;
	
	TextureRegion region;
	
	AbstractTool(NetaGame ng){
			
		AtlasRegion reg = ng.getManager().getAtlas().findRegion(getImagePath());
		Texture tex = reg.getTexture();
		
		 region = new TextureRegion(tex, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight());
				
		this.addListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof InputEvent){
					if(((InputEvent) event).getType()==InputEvent.Type.touchDown){
						doAction();
					}
				}
				return true;
			}
		});
		
		Size size = getSize();
		this.setSize(size.width*k, size.height*k);
		
	}
	
	public abstract Size getSize();
	
	
	@Override
	public void act(float f){
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	


	abstract void doAction();
	
	abstract String getImagePath();
}
