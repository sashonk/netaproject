package com.me.neta.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class WorkHelper {

	public static void center(Actor actor){
		Actor parent = actor.getParent();
		if(parent==null){
			return;
		}
		
		float x = parent.getWidth()/2 - actor.getWidth()/2;
		float y = parent.getHeight()/2 - actor.getHeight()/2;		
		actor.setPosition(x, y);
	}

}
