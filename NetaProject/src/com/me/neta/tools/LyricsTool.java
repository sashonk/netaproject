package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;


import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;


public class LyricsTool extends PanelTool{
	
		@Override
	public void hide(){
		panel.addAction(sequence(fadeOut(FADE_INTERVAL), visible(false)));				

	}
	
		@Override
	public void show(){
		panel.addAction(sequence(visible(true), fadeIn(FADE_INTERVAL)));
	}

	public LyricsTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getImagePath() {
		return "book";
	}
	
	@Override
	public Size getSize() {
		return new Size(128 , 78);
	}


	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null &&ctx.getProperty(ContextProperty.WORKING)!=null &&
				popupAccepted( ctx);	
	}

}
