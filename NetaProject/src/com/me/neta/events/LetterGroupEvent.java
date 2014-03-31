package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class LetterGroupEvent extends Event{
	int ID;
	public LetterGroupEvent(int ID){
		this.ID = ID;
	}
	
	public int getLetterGroupID(){
		return ID;
	}
}
