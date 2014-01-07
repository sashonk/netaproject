package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class DesktopIconEvent extends Event{
	
	public DesktopIconEvent(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

	private int id;
}
