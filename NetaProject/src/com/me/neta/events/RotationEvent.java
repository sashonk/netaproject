package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class RotationEvent extends Event{
	private float degrees;
	private int id;
	public RotationEvent(float degrees, final int id){
		this.degrees = degrees;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public float getDegrees(){
		return degrees;
	}
}
 