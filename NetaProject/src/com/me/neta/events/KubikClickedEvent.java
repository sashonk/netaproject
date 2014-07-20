package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class KubikClickedEvent extends Event{
	public KubikClickedEvent(int timesFired){
		tf = timesFired;
	}
	
	public int getTimesFired(){
		return tf;
	}
	
	int tf;
}
 