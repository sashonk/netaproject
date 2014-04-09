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
		return ctx.getProperty(ContextProperty.WORKING);
	}


	@Override
	public String getImagePath() {
		return "letter";
	}

} 
