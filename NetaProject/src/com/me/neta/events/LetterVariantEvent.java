package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class LetterVariantEvent extends Event{
	int ID;
	public LetterVariantEvent(int ID){
		this.ID = ID;
	}
	
	public int getLetterGroupID(){
		return ID;
	}
}
