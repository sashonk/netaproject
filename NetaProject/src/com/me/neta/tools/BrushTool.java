package com.me.neta.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.TextureManager;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.BrushToolChangeEvent;

public class BrushTool extends TopTool{
	TextureManager tm;
	public BrushTool(NetaGame ng){
		super(ng);
		blink = false;
		checked =false;
		tm = ng.getManager();
		
	}
	
	private boolean checked;
	
	public boolean checked(){
		return checked;
	}


	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null &&ctx.getProperty(ContextProperty.WORKING)!=null;
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
			batch.draw(tx, this.getX()+4, this.getY()+4, this.getWidth()-8, this.getHeight()-8);
		}else{
			batch.setColor(1, 1, 1, c.a*.4f*parentAlfa*k);
		}
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public String getImagePath() {
		return "brush";
	}

}
 