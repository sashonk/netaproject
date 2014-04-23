package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class FigureDropEvent extends Event{
	Actor letter;
	public FigureDropEvent(Actor letter){
		this.letter = letter;
	}
	
	public Actor getActor(){
		return letter;
	}

}
