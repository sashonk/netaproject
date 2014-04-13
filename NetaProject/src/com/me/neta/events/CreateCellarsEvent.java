package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;


public  class CreateCellarsEvent extends Event{
	public CreateCellarsEvent(int choise){
		this.choice = choise;
	}
	
	public int getChoice(){
		return choice;
	}
	
	private int choice;
}