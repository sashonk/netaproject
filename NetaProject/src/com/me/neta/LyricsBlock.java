package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
 
public class LyricsBlock extends Moveable{
	private static float mul = 0.4f;
	TextureRegion region;
	private long lastTapTime ;
	public LyricsBlock(int i,int j){
		lastTapTime = 0;
		 region = TextureManager.get().getAtlas().findRegion(String.format("STIHI-%d-%d", i, j));
		 setSize(region.getRegionWidth()*mul, region.getRegionHeight()*mul);
		 setOrigin(getWidth()/2, getHeight()/2);

		 this.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
							
					if(System.currentTimeMillis() - lastTapTime < 500){
						event.getTarget().addAction(sequence(scaleTo(0.9f, 0.9f, .1f), scaleTo(1, 1, 0.2f)));

					}
					
					lastTapTime = System.currentTimeMillis();

					return false;
				}
		 });
	}
	
	@Override
	public boolean isDisposable() {
		return false;
	}

	public void draw(SpriteBatch batch , float parentAlpha){
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
