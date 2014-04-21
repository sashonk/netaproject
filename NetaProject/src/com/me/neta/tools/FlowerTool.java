package com.me.neta.tools;

import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;

public class FlowerTool extends PanelTool{

	public FlowerTool(NetaGame ng) {
		super(ng);
	}

	@Override
	public Size getSize() {
		return new Size(85, 70);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null &&ctx.getProperty(ContextProperty.WORKING)!=null && ctx.getProperty(ContextProperty.LETTERS)==null && (ctx.getProperty(ContextProperty.INGAME)==null) && ctx.getProperty(ContextProperty.CELLARS)!=null;
	}


	@Override
	public String getImagePath() {
		return "ZVETOKOK_A";
	}

} 
