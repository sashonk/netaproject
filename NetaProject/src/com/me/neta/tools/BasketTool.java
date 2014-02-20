package com.me.neta.tools;

import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.TrashButtonEvent;


public class BasketTool extends AbstractTool{

	public BasketTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		fire(new TrashButtonEvent());
	}

	@Override
	public String getImagePath() {
		return "basket";
	}

	@Override
	public Size getSize() {
		return new Size(68 , 78);
	}

	@Override
	public boolean accept(Context ctx) {
		return !ctx.getProperty(ContextProperty.HALT) &&ctx.getProperty(ContextProperty.WORKING);
			
	}


	
}
