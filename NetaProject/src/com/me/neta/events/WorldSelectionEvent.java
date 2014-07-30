package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class WorldSelectionEvent extends Event{
	
	public WorldSelectionEvent(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}

	private String id;
}
