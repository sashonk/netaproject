package com.me.neta;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CoorListener extends InputListener{

	
	public boolean mouseMoved (InputEvent event, float x, float y) {
		Vector2 v = event.getListenerActor().localToStageCoordinates(new Vector2(event.getListenerActor().getX(), event.getListenerActor().getY()));
		System.out.println(String.format("COOR:obj=%s [x=%2f y=%2f]", event.getListenerActor().toString(),v.x, v.y  ));
		return false;
	}
}