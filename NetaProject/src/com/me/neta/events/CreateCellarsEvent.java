package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;


public  class CreateCellarsEvent extends Event{
	public CreateCellarsEvent(String choise){
		this.choice = choise;
	}
	
	public String getChoice(){
		return choice;
	}
	
	private String choice;
}