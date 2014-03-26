package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class TextChanged extends Event{
	private String text;
	public TextChanged(String newText){
		text = newText;
	}
	
	public String getText(){
		return text;
	}
}
