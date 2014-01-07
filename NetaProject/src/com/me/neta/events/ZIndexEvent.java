package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class ZIndexEvent extends Event{
	Actor above;
	Actor below;
	public ZIndexEvent(Actor actorAbove, Actor actorBelow){
		this.above = actorAbove;
		this.below= actorBelow;
	}
	
	public Actor getAbove(){
		return above;
	}
	
	public Actor getBelow(){
		return below;
	}
}
