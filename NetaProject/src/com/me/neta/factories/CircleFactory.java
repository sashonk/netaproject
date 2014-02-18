package com.me.neta.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Factory;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.figures.Circle;

public class CircleFactory extends Factory{
	private float width;
	private float height;
	private String assetName;
	private TextureRegion tr;


	public CircleFactory( float width, float height, String assetName,NetaGame ng){
		super(ng);
		this.width= width;
		this.height= height;		
		this.assetName = assetName;
		setSize(width, height);
		
		tr = ng.getManager().getAtlas().findRegion(assetName);

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
		return new Circle(ng, width,height, assetName);
	}

	@Override
	public Actor createInvalidDragActor() {
		return new Actor();
	}

}
 