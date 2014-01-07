package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;


public  class LyricsIconEvent extends Event{
	public LyricsIconEvent(int choise){
		this.choice = choise;
	}
	
	public int getChoice(){
		return choice;
	}
	
	private int choice;
}