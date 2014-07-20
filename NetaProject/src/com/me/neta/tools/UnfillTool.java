package com.me.neta.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.BrushToolChangeEvent;
import com.me.neta.events.RotationEvent;
import com.me.neta.events.UnfillEvent;

public class UnfillTool extends TopTool{

	public UnfillTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void doAction() {

		fire(new UnfillEvent());
	}

	@Override
	public String getImagePath() {
		return "ZAPERED";
	}
	

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null&&ctx.getProperty(ContextProperty.INGAME)==null &&ctx.getProperty(ContextProperty.WORKING)!=null&& ctx.getProperty(ContextProperty.CELLARS)!=null&&
				popupAccepted( ctx);	
	}

	

}
