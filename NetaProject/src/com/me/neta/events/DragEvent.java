package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class DragEvent extends Event{
	private Actor dragActor;
	public DragEvent(Actor dragActor){
		this.dragActor = dragActor;
	}
	
	public Actor getDragActor(){
		return dragActor;
	}
}
 