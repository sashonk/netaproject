package com.me.neta.figures;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.neta.Moveable;
import com.me.neta.Util;
import com.me.neta.events.SelectFigureEvent;

public abstract class AbstractFigure extends Group implements Moveable {
	protected boolean filled;
	
	public boolean isFilled(){
		return filled;
	}
	
	public AbstractFigure(){
		
		filled= false;
		
	}
	
	public void oldFashionedDraw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
	}
	
	public void fill(Color c){
		super.setColor(c);
		filled = true;	
	}
	
	public void unfill(){
		filled = false;
	}
	
	static Action createAnimationAction(){
		 float duration = .1f;
		 float minAlpha = .2f;
		 float maxAlpha = 1;
		 return   forever(sequence(alpha(minAlpha, duration), alpha(maxAlpha ,duration)));		 	
	 }
	
	private Action animationAction;
	
	public void animateSelected(){
		
		animationAction = createAnimationAction();
		this.addAction(animationAction);

	}
	
	public void animateVacant(){
		this.removeAction(animationAction);		
		this.getColor().a = 1;
	}
		
	public abstract void drawFilled(SpriteBatch batch, float parentAlpha);
	
	public abstract void drawEmpty(SpriteBatch batch, float parentAlpha);

	public void draw(SpriteBatch batch , float parentAlpha){
		if(filled){
			drawFilled(batch, parentAlpha);
		}
		else{
			drawEmpty(batch, parentAlpha);
		}
	}
	
	@Override
	public boolean isDisposable() {
		return true;
	}
}
