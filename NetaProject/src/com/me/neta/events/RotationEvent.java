package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class RotationEvent extends Event{
	private float degrees;
	public RotationEvent(float degrees){
		this.degrees = degrees;
	}
	
	public float getDegrees(){
		return degrees;
	}
}
 