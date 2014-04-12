package com.me.neta;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

public class Util {


	public static TemporalAction zoomTo(float value, float duration, float x, float y, Interpolation interpolation){
		ZoomToAction zoomTo = new ZoomToAction(value, x ,y);
		zoomTo.setDuration(duration);
		if(interpolation!=null){
			zoomTo.setInterpolation(interpolation);
		}
		return zoomTo;
	}

	public static TemporalAction zoomTo(float value, float duration, Interpolation interpolation){
		ZoomToAction zoomTo = new ZoomToAction(value);
		zoomTo.setDuration(duration);
		if(interpolation!=null){
			zoomTo.setInterpolation(interpolation);
		}
		return zoomTo;
	}


	public static TemporalAction moveCameraTo(float tx, float ty, float duration, Interpolation interpolation){
		MoveCameraToAction zoomTo = new MoveCameraToAction(tx, ty,  duration, interpolation);
		zoomTo.setDuration(duration);
		if(interpolation!=null){
			zoomTo.setInterpolation(interpolation);
		}
		return zoomTo;
	}
	
	public static class MoveCameraToAction extends TemporalAction{
		float tx;
		float ty;
		float ox;
		float oy;
		boolean inited;
		public MoveCameraToAction(float targetX, float targetY, float duration, Interpolation interpolation ){

			tx = targetX;
			ty = targetY;
			setDuration(duration);
			setInterpolation(interpolation);
		}
		@Override
		protected void update(float percent) {
			Actor actor = getActor();
			Stage stage =  actor.getStage();
			OrthographicCamera camera = (OrthographicCamera) stage.getCamera();

			if(!inited){
				ox = camera.position.x;
				oy = camera.position.y;
				inited = true;
			}
			
			float x = ox*(1-percent) + tx*percent;
			float y = oy*(1-percent) + ty*percent;
			camera.position.set(x, y, 0);
			constrainPosition(camera, stage);
			constrainZoom(camera);
		}
	}
	
	public static class ZoomToAction extends TemporalAction{
		float ox;
		float oy;
		float targetZoom;
		float initialZoom;
		boolean inited;
		boolean orig;
		public ZoomToAction(float value, float x, float y){
			ox = x;
			oy = y;
			targetZoom = value;
			inited= false;
			orig = false;
		}
		
		public ZoomToAction(float value){
			targetZoom = value;
			inited= false;
			orig = true;
		}
		@Override
		protected void update(float percent) {
			Actor actor = getActor();
			Stage stage =  actor.getStage();
			OrthographicCamera camera = (OrthographicCamera) stage.getCamera();

			if(!inited){
				initialZoom = camera.zoom;						
				inited = true;

			}		
			if(orig){
				ox = actor.getOriginX();
				oy = actor.getOriginY();				
				orig = false;
			}
			
			Vector2 originGlobal = actor.localToStageCoordinates(new Vector2(ox, oy));
			camera.position.set(originGlobal.x, originGlobal.y, 0);		
			camera.zoom = targetZoom*percent + initialZoom*(1-percent);
			constrainPosition(camera, stage);
			constrainZoom(camera);
		}

	}
	
	
	public static void constrainPosition(OrthographicCamera cam, Stage s){
		
		float vw = cam.viewportWidth*.5f*cam.zoom;
		float vh = cam.viewportHeight*.5f*cam.zoom;
		
		if(cam.position.x - vw<0){
			cam.position.x = vw;
		}
		if(cam.position.y - vh< 0){
			cam.position.y = vh;
		}
		if(cam.position.x + vw > s.getWidth()){
			cam.position.x = s.getWidth() - vw;
		}
		if(cam.position.y + vh > s.getHeight()){
			cam.position.y = s.getHeight() - vh;
		}
	}
	
	private static float Zmin = 0.1f;
	public static void constrainZoom(OrthographicCamera cam){
		if(cam.zoom<Zmin){
			cam.zoom = Zmin;
		}
		if(cam.zoom>1){
			cam.zoom = 1;
		}
	}
	
	public static Actor multiColorLabel(String text, String baseStyle, String[] colorNames, Skin skin){
		Table table = new Table();
		boolean styleSet = false;
		for(int i = 0; i<text.length(); i++){
			char c = text.charAt(i);
			
			
			String style =new StringBuilder(baseStyle).append("_").append(colorNames[i]).toString() ; 									
			Label label = new Label(new String(new char[]{c}), skin, style);
			if(!styleSet){
				table.defaults().padRight(label.getStyle().font.getBounds(" ").width);
				styleSet = true;
			}
			table.add(label);
		}
		table.pack();
		
		return table;
	}

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
