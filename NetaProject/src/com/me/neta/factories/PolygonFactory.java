package com.me.neta.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Factory;
import com.me.neta.Size;
import com.me.neta.TextureManager;
import com.me.neta.figures.PolygonFigure;

public class PolygonFactory extends Factory{
	TextureRegion tx;
	Size size;
	String assetName;
	float[] polyVertices;
	
	public PolygonFactory(float[] vertices, String assetName, Size size,  Actor workspace){
		super(workspace);
		this.size =size;
		this.setWidth(size.width);
		this.setHeight(size.height);
		polyVertices = vertices;
		this.assetName = assetName;

		tx = TextureManager.get().getAtlas().findRegion(assetName);

	}
	
	public void draw(SpriteBatch batch, float parentAlpha){

		batch.draw(tx, getX(), getY(), getWidth(), getHeight());
		

	}
	
	@Override
	public Actor createDragActor() {
		Actor actor = new Actor(){
			public void draw(SpriteBatch batch , float parentAlpha){
				batch.draw(tx, getX(), getY(), getWidth(), getHeight());				
			}
		};
		
		actor.setWidth(size.width);
		actor.setHeight(size.height);
		
		return actor;
	}

	@Override
	public Actor createActor() {
		return new PolygonFigure(polyVertices, assetName, size);
	}

	@Override
	public Actor createInvalidDragActor() {
		 		return new Actor();

	}
 
}
