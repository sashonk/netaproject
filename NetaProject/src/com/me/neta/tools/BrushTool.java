package com.me.neta.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.neta.Size;
import com.me.neta.TextureManager;
import com.me.neta.events.BrushToolChangeEvent;
import com.me.neta.util.WorkspaceState;

public class BrushTool extends AbstractTool{
	TextureManager tm;
	public BrushTool(){
		blink = false;
		checked =false;
		tm = TextureManager.get();
		
	}
	
	private boolean checked;
	
	@Override
	public Size getSize() {
		return new Size(36, 36);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}

	@Override
	public void doAction() {
		if(checked){
			checked = false;
		}
		else{
			checked = true;
		}
		
		fire(new BrushToolChangeEvent(checked));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		Color c = getColor();
		Color opaque = c.cpy();
		opaque.a = 1;
		Texture tx = tm.getCircle(opaque, Math.max(getWidth(), getHeight()));	
		
		float k = checked ? 1 : 0.5f;
		if(enabled()){
			batch.setColor(1, 1, 1, c.a*parentAlfa*k);
		}else{
			batch.setColor(1, 1, 1, c.a*.4f*parentAlfa*k);
		}
		batch.draw(tx, this.getX()+4, this.getY()+4, this.getWidth()-8, this.getHeight()-8);
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public String getImagePath() {
		return "brush";
	}

}
 