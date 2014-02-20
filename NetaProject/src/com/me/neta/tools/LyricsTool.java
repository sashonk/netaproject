package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;


import com.me.neta.Context;
import com.me.neta.LyricsPanel;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;


public class LyricsTool extends PanelTool{
	


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
		return !ctx.getProperty(ContextProperty.HALT) &&ctx.getProperty(ContextProperty.WORKING);
	}

}
