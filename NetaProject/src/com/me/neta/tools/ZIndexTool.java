package com.me.neta.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Workspace;
import com.me.neta.events.RotationEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.util.WorkspaceState;

public class ZIndexTool extends AbstractTool{

	public ZIndexTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	public Actor panel;
	public AbstractFigure selected;
	private boolean  pressed = false;
	
	@Override
	public Size getSize() {
		return new Size(36, 36);
	}
	
	public void pushDown(){
		pressed =true;
	}
	
	public void pushUp(){
		
	}
	
	public void setSelectedFigure(AbstractFigure figure){
		if(figure!=null && selected!=null && pressed && figure!=selected){
			fire(new ZIndexEvent(selected, figure));
			pressed = false;
		}
		selected = figure;
		
	}

	@Override
	public void doAction() {
		if(selected!=null && !pressed){
			pressed = true;
		}
		else{
			pressed = false;
		}
	}

	@Override
	public String getImagePath() {
		return "ZAPERED";
	}
	

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}

	private Color tint = new Color(.4f,1f,.4f, 1);
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		if(enabled()){
			Color c = getColor();
			if(pressed){
				batch.setColor(tint.r, tint.g, tint.b, tint.a*parentAlfa);
			}else{
				batch.setColor(c.r, c.g, c.b, c.a*parentAlfa);				
			}
			batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}else{
			Color c = getColor();
			batch.setColor(c.r, c.g, c.b, c.a*.4f*parentAlfa);
			batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
	
	}
}
