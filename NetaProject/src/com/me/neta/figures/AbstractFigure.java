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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.neta.DoubleClickListener;
import com.me.neta.Moveable;
import com.me.neta.Util;
import com.me.neta.events.SelectFigureEvent;

public abstract class AbstractFigure extends Moveable{
	protected boolean filled;
	
	public AbstractFigure(){
		
		filled= false;
		
		 this.addListener(new DoubleClickListener(.8f) {
				
				@Override
				public void doubleClick() {
					AbstractFigure.this.fire(new SelectFigureEvent());
					
/*					OrthographicCamera cam = (OrthographicCamera) AbstractFigure.this.getStage().getCamera();
					float[] widths = new float[]{1f, .2f, .1f};
					float[] heights = new float[]{1f, 0.2f, 0.1f};

					if(cam.zoom==1){
					AbstractFigure.this.addAction(Util.zoomTo(0.3f, 1, new Interpolation.BounceOut(widths, heights)));
					}
					else{
						AbstractFigure.this.addAction(Util.zoomTo(1, 1,new Interpolation.BounceOut(widths, heights)));
					}*/
				}
			});
		 

	}
	
	@Override
	public void setColor(Color c){
		super.setColor(c);
		filled = true;
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
