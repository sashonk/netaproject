package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class DoubleClickListener extends InputListener{
	float tapInterval;
	boolean tapping;
	long tapStartTime;
	int tapCount;
	public DoubleClickListener(float tapInterval){
		this.tapInterval =tapInterval;
	}
	
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	
		return true;
	}
	
	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		if(!tapping){
			tapping = true;
			tapStartTime = System.currentTimeMillis();
		}else{
			
			long now = System.currentTimeMillis();
			if((now-tapStartTime)/(float)1000 <=tapInterval){
				doubleClick();
				tapping = false;	

			}
			else{
				tapStartTime = now;
			}
		

		}
	}
	
	public abstract void doubleClick();
}
