package com.me.neta.tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.RotationEvent;

public class RotateTool extends TopTool{
	int timesFired;
	public RotateTool(NetaGame ng) {
		super(ng);
		timesFired = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		fire(new RotationEvent(-45, ++timesFired));
	}

	@Override
	public String getImagePath() {
		return "POVOROT";
	}
	

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null&&ctx.getProperty(ContextProperty.INGAME)==null &&ctx.getProperty(ContextProperty.WORKING)!=null &&ctx.getProperty(ContextProperty.CELLARS)!=null&&
				popupAccepted( ctx);	
	}


}
