package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class ForbiddenEvent extends Event{
	public ForbiddenEvent(String mes){
		msg =mes;
	}
	
	public String getMessage(){
		return msg;
	}
	
	String msg;
}
