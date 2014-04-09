package com.me.neta.dummy;

import com.badlogic.gdx.Input.Buttons;
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

public class Dummy extends Moveable{
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
		
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(button  == Buttons.RIGHT){
					event.getListenerActor().remove();
				}
				else{
					OrthographicCamera c = ((OrthographicCamera)event.getListenerActor().getStage().getCamera());
					
					System.out.println(event.getListenerActor()+String.format(" [zoom=%f; x=%f; y=%f]", c.zoom, c.position.x, c.position.y));
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
