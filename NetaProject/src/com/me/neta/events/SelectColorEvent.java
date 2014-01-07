package com.me.neta.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;

public class SelectColorEvent extends Event{
	private Color c;
	public SelectColorEvent(Color c){
		this.c = c;
	}
	
	public Color getColor(){
		return c;
	}
}
