package com.me.neta.dummy;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.me.neta.Moveable;
import com.me.neta.NetaGame;
import com.me.neta.Size;

public class Dummy extends Moveable{
	
/*	public enum DummyType implements Serializable{
		HOUSE,
		BARRIER,
		FLOWER,
		START;
	}*/
	
	private String type;
	
	public Vector2 getGroupOrigin() {
		return groupOrigin;
	}

	public void setGroupOrigin(Vector2 groupOrigin) {
		this.groupOrigin = groupOrigin;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	private Vector2 groupOrigin;
	
	private float zoom;
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
			
	}
	
	TextureRegion reg;
	int group;
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	public Dummy(final NetaGame ng, TextureRegion tr){
		reg = tr;
	}

	public Dummy(final NetaGame ng, TextureRegion tr, final DummyHelper helper){
		reg = tr;
	
		
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(button  == Buttons.RIGHT ){
					if(Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)){
						event.getListenerActor().remove();
					}
					if(Gdx.input.isKeyPressed(Keys.I)){
						
						OrthographicCamera c = (OrthographicCamera) getStage().getCamera();
						Dummy.this.zoom = c.zoom;
						Dummy.this.setGroupOrigin(new Vector2(c.position.x, c.position.y));
						System.out.println("INJECTED!");
						//Dummy.this.setGroupOrigin(c.position);
					} 
					else{

						
						DummyForm form = helper.getForm();
						form.show(Dummy.this);
					}
				}
				else{
					OrthographicCamera c = ((OrthographicCamera)event.getListenerActor().getStage().getCamera());
					
					System.out.println(event.getListenerActor()+String.format("(x=%f; y=%f) [zoom=%f; x=%f; y=%f]", x,y,c.zoom, c.position.x, c.position.y));
				}
				return false;
			}
		});
	}

	@Override
	public boolean isDisposable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(SpriteBatch sb, float parentA){
		Color c = getColor();
		sb.setColor(1, 1, 1, c.a*parentA);
		sb.draw(reg, getX(), getY(), getWidth(), getHeight());
		

	}
}
