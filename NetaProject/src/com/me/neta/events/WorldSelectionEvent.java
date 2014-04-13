package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class WorldSelectionEvent extends Event{
	
	public WorldSelectionEvent(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	private int id;
}
