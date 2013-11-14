package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Profile {
	public static Profile get(){
		if(instance==null){
			instance = new Profile();
		}
		
		return instance;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	private static Profile instance;
	
	private Stage stage;
}
