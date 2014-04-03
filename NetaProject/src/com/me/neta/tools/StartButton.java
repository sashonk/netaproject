package com.me.neta.tools;

import com.me.neta.Context;
import com.me.neta.Context.ContextProperty;
import com.me.neta.NetaGame;
import com.me.neta.Size;

public class StartButton extends AbstractTool{
	Context context;
	public StartButton(NetaGame ng) {
		super(ng);
		blink = false;
		context = ng.getContext();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Size getSize() {
		return new Size(130, 143);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.LETTERS);
	}

	@Override
	public void doAction() {
		context.setProperty(ContextProperty.INGAME, true);
		
	}

	@Override
	public String getImagePath() {
		return "start";
	}
	
}
