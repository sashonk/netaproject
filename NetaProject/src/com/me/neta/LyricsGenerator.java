package com.me.neta;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LyricsGenerator {
	private static float mul = 0.4f;
	
	public static List<Actor> getLyrics(int id){
	
		
		List<Actor> regions = new ArrayList<Actor>(5);
		for(int i = 1; ; i++){
			
				AtlasRegion region = TextureManager.get().getAtlas().findRegion(String.format("STIHI-%d-%d", id, i));
				
				if(region==null){
					break;
				}
			
				Texture tx = region.getTexture();
				final TextureRegion tr = new TextureRegion(tx, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
				
				Actor actor = new Moveable(){

					public void draw(SpriteBatch batch , float parentAlpha){
						batch.draw(tr, getX(), getY(),getWidth(), getHeight());
					}

					@Override
					public boolean isDisposable() {
						return false;
					}
					
				};
				
				actor.setSize(tr.getRegionWidth()*mul, tr.getRegionHeight()*mul);
				
				regions.add(actor);
				
			
		}
		
		return regions;
	}
}
