package com.me.neta.tools;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Popup.PopupGroup;
import com.me.neta.events.PaletteShowEvent;


public class ColorTool extends PanelTool{


	
	public ColorTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getImagePath() {
		return "palette";
	}
	
	@Override
	public Size getSize() {
		return new Size(100 , 78);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null &&ctx.getProperty(ContextProperty.WORKING)!=null&&ctx.getProperty(ContextProperty.INGAME)==null&&ctx.getProperty(ContextProperty.CELLARS)!=null&&
				popupAccepted( ctx);	
	}
	
	
	public void hide(){
		panel.addAction(sequence(fadeOut(FADE_INTERVAL), visible(false)));				

	}
	
	public void show(){
		panel.addAction(sequence(visible(true), fadeIn(FADE_INTERVAL)));
		fire(new PaletteShowEvent());
	}
}
