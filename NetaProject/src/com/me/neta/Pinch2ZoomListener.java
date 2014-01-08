package com.me.neta;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Pinch2ZoomListener extends InputListener{
	private Actor actor;
	
	public Pinch2ZoomListener(Actor a){
		actor = a;
	}
	
	 int numberOfFingers = 0;
	 int fingerOnePointer;
	 int fingerTwoPointer;
	 float lastDistance = 0;
	 Vector3 fingerOne = new Vector3();
	 Vector3 fingerTwo = new Vector3();
	
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		 numberOfFingers++;
		 if(numberOfFingers == 1)
		 {
		        fingerOnePointer = pointer;
		        fingerOne.set(x, y, 0);
		 }
		 else if(numberOfFingers == 2)
		 {
		        fingerTwoPointer = pointer;
		        fingerTwo.set(x, y, 0);
		 
		       float distance = fingerOne.dst(fingerTwo);
		        lastDistance = distance;
		 }
		
		return true;
	}


	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		 Camera cam = actor.getStage().getCamera();

		if(numberOfFingers == 1)
		{
        Vector3 touchPoint = new Vector3(x, y, 0);
        cam.unproject(touchPoint);
		}
		numberOfFingers--;
 
		// just some error prevention... clamping number of fingers (ouch! :-)
		 if(numberOfFingers<0){
		        numberOfFingers = 0;
		 }
	

		lastDistance = 0;
}


	public void touchDragged (InputEvent event, float x, float y, int pointer) {
		 if (pointer == fingerOnePointer) {
		        fingerOne.set(x, y, 0);
		 }
		 if (pointer == fingerTwoPointer) {
		        fingerTwo.set(x, y, 0);
		 }
		 
		float distance = fingerOne.dst(fingerTwo);
		 float factor = distance / lastDistance;
		 

		 Vector3 center = fingerOne.add(fingerTwo).div(2);
		 Camera cam = actor.getStage().getCamera();
	//	 float cx = cam.position.x;
	//	 float cy = cam.position.y;
		 float cz = cam.position.z;
		 
		if (lastDistance > distance) {
		        actor.getStage().getCamera().translate(center.x, center.y, cz+factor);
		 } else if (lastDistance < distance) {
		        actor.getStage().getCamera().translate(center.x, center.y, cz-factor);
		 }
		 

		// clamps field of view to common angles...
		// if (cam.fieldOfView < 10f) cam.fieldOfView = 10f;
		// if (cam.fieldOfView > 120f) cam.fieldOfView = 120f;
		 

		lastDistance = distance;
		 

		//cam.update();

	}
}
