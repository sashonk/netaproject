package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public abstract class Moveable extends Actor{

	protected float dragStartX ;
	protected float dragStartY ;
	
	protected float sx ;
	protected float sy ;
	
	public abstract boolean isDisposable();
	
	
	public Moveable(){
		
	
		
		DragListener listener = new DragListener(){

			public void dragStart (InputEvent event, float x, float y, int pointer) {
				Actor t = event.getTarget();
				sx = t.getOriginX();
				sy = t.getOriginY();
				t.setOrigin(t.getWidth()/2, t.getHeight()/2);
			}
			
			public void drag (InputEvent event, float x, float y, int pointer) {
				Actor t = event.getTarget();
				
				Vector3 v3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				getStage().getCamera().unproject(v3);		
				
				float ox = v3.x-t.getOriginX();
				float oy = v3.y-t.getOriginY();
				float sw = getStage().getWidth();
				float sh = getStage().getHeight();
				
				if(ox<=0){
					ox = 0;
				}
				if(ox >= sw - t.getWidth()){
					ox = sw - t.getWidth();
				}
				if(oy <= 0 ){
					oy = 0;
				}
				if(oy >= sh - t.getHeight()){
					oy = sh - t.getHeight();
				}
				
				t.setPosition(ox, oy);
			}
			
			
			public void dragStop (InputEvent event, float x, float y, int pointer) {
				Actor t = event.getTarget();
				t.setOrigin(sx, sy);
				
			
			}
		};
		
		listener.setTapSquareSize(5);
		
		
		this.addListener(listener);
	}
}
