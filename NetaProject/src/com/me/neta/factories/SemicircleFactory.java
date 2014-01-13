package com.me.neta.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Factory;
import com.me.neta.TextureManager;
import com.me.neta.figures.Circle;
import com.me.neta.figures.Semicircle;

public class SemicircleFactory extends Factory{
	private float width;
	private float height;
	private String assetName;
	private TextureRegion tr;

	public SemicircleFactory(float width, float height, String assetName, Actor workspace){
		super(workspace);
		this.width= width;
		this.height= height;		
		this.assetName = assetName;
		setSize(width, height);
		
	//	TextureRegion tr0 = TextureManager.get().getAtlas().findRegion(assetName);
		tr = TextureManager.get().getAtlas().findRegion(assetName);//new TextureRegion(tr0, tr0.getRegionX(), tr0.getRegionY(), tr0.getRegionWidth(), tr0.getRegionHeight()/2);

	}
	
	public void draw(SpriteBatch batch, float parentAlpha){

		batch.draw(tr, getX(), getY(), getWidth(), getHeight());
		

	}

	@Override
	public Actor createDragActor() {				
		Actor actor = new Actor(){
			public void draw(SpriteBatch batch , float parentAlpha){
				batch.draw(tr, getX(), getY(), getWidth(), getHeight());				
			}
		};
		
		actor.setWidth(width);
		actor.setHeight(height);
		
		return actor;
	}

	@Override
	public Actor createActor() {
		return new Semicircle(width,height, assetName);
	}

	@Override
	public Actor createInvalidDragActor() {
		return new Actor();
	}

}
 