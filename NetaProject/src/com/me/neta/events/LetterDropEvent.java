package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class LetterDropEvent extends Event{
	Actor letter;
	public LetterDropEvent(Actor letter){
		this.letter = letter;
	}
	
	public Actor getActor(){
		return letter;
	}

}
