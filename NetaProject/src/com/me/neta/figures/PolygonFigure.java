package com.me.neta.figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.TextureManager;

public class PolygonFigure extends AbstractFigure{
	TextureRegion tr;
	Polygon poly;
	Texture tx;
		
	public PolygonFigure(NetaGame ng, float[] vertices, String assetName, Size size){
		tr = ng.getManager().getAtlas().findRegion(assetName);
		this.setWidth(size.width);
		this.setHeight(size.height);
		this.setOrigin(getWidth()/2, getHeight()/2);		
		poly = new Polygon(vertices);
		
		Pixmap pmap = new Pixmap(1,1, Format.RGBA8888);
		pmap.setColor(Color.WHITE);
		pmap.fill();
		tx = new Texture(pmap);
	}
	
	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {

		float[] v = poly.getTransformedVertices();
		Color c = getColor();
		

		float[] arr = new float[] {
				v[0], v[1], Color.toFloatBits(c.r, c.g, c.b, c.a), 0, 0,
				v[2], v[3],Color.toFloatBits(c.r, c.g, c.b, c.a), 0, 0,
				v[4], v[5], Color.toFloatBits(c.r, c.g, c.b, c.a), 0, 0,
				v[6], v[7],Color.toFloatBits(c.r, c.g, c.b, c.a), 0, 0					
		};

		batch.setColor(c.r, c.g,c.g,c.a*parentAlpha);
		batch.draw(tx, arr, 0, 20);
					
	}
	
	@Override
	public void act(float dt){
		super.act(dt);
		
		poly.setRotation(getRotation());
		poly.setPosition(getX(), getY());
		poly.setOrigin(getOriginX(),getOriginY());
		poly.setScale(getScaleX(), getScaleY());

	}

	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),  getScaleX(), getScaleY(), getRotation());			
		
	}



}
