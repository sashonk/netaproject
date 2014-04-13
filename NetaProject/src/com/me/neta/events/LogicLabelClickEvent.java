package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class LogicLabelClickEvent extends Event{
	char ch;
	public LogicLabelClickEvent(char c){
		ch = c;
	}
	
	public char getChar(){
		return ch;
	}
}
