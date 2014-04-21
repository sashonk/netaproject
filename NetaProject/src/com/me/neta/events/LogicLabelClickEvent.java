package com.me.neta.events;

import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LogicLabelClickEvent extends Event{
	String ch;
	Map<Character, Label> context;
	public LogicLabelClickEvent(String c, Map<Character, Label> context){
		ch = c;
		this.context = context;
	}
	
	public Map<Character, Label> getContext(){
		return context;
	}
	
	public String getChar(){
		return ch;
	}
}
