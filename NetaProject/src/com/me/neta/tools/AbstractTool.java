package com.me.neta.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.TextureManager;
import com.me.neta.util.WorkspaceState;
import com.me.neta.util.WorkspaceStateListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

public abstract class AbstractTool extends Actor implements WorkspaceStateListener{
	
	
	final static float k = .75f;
	
	TextureRegion region;
	
	protected boolean blink;
	
	public AbstractTool(NetaGame ng){
		enabled = false;
		blink = true;
			
		AtlasRegion reg = ng.getManager().getAtlas().findRegion(getImagePath());
		Texture tex = reg.getTexture();
		
		 region = new TextureRegion(tex, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight());
				

		
		
		
		this.addListener(new InputListener(){

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(enabled()){
					if(blink)event.getTarget().addAction(alpha(.4f));					
					doAction();
				}
				
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(blink)event.getTarget().addAction(alpha(1, .2f));
			}
		});
		
		Size size = getSize();
		this.setSize(size.width*k, size.height*k);
		
	}
	
	public abstract Size getSize();
	
	public void stateChanged(WorkspaceState oldState, WorkspaceState newState){
		setEnabled(accept(newState));
	}

	public abstract boolean accept(WorkspaceState state);

	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		if(enabled()){
			Color c = getColor();
			batch.setColor(c.r, c.g, c.b, c.a*parentAlfa);
			batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}else{
			Color c = getColor();
			batch.setColor(c.r, c.g, c.b, c.a*.4f*parentAlfa);
			batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
	
	}
	
	public void setEnabled(boolean value){
		enabled = value;
	}
	
	private boolean enabled;
	
	public  boolean enabled(){
		return enabled;
	}

	public abstract void doAction();
	
	public abstract String getImagePath();
}
