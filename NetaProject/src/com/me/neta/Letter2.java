package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.me.neta.figures.AbstractFigure;

public class Letter2 extends AbstractFigure{
	
	TextureRegion circle;
	float sc = 0.4f;

	public Letter2(NetaGame ng, char c){			
		Label l = new Label(new String(new char[]{c}), ng.getManager().getSkin(), "letter");
		l.setX(15);
		l.setTouchable(Touchable.disabled);
		addActor(l);
		setSize(30, 30);
		 circle = ng.getManager().getAtlas().findRegion("circle");
		 
		 this.setScale(sc);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a*parentAlpha);
		batch.draw(circle, getX(), getY(), getOriginX(), getOriginY(), getWidth()/sc, getHeight()/sc, getScaleX(), getScaleY(), getRotation());			
	
		
		super.oldFashionedDraw(batch, parentAlpha);
	}

	@Override
	public void drawFilled(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawEmpty(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
	}
}
