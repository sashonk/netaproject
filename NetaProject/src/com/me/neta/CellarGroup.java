package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class CellarGroup extends Group{
	NetaGame theGame;
	public CellarGroup(NetaGame ng){
		theGame = ng;
	}
	
	public static class LogicFlower extends Group{
		
	}
	
	public void setSucceed(){
		Actor actor = findActor("gate");
		Gate gate =(Gate)actor;
		gate.open();
	}
	
	public void addFlower(LogicFlower flower){
		addActor(flower);
	}
}
