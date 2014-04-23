package com.me.neta.tools;

import com.me.neta.Context;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.DragStartEvent;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.World;

public class StartButton extends AbstractTool{
	Context context;
	World world;

	public StartButton(NetaGame ng, World w) {
		super(ng);
		
		blink = false;
		context = ng.getContext();
		world = w;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Size getSize() {
		return new Size(130, 143);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.LETTERS)!=null && ctx.getProperty(ContextProperty.INGAME)==null && 
				popupAccepted( ctx);	
	}

	@Override
	public void doAction() {
		context.setProperty(ContextProperty.INGAME, Boolean.TRUE);
		context.setProperty(ContextProperty.HALT, Boolean.TRUE);
		world.start();
		fire(new DragStartEvent()); //to stop workspace pan
	}

	@Override
	public String getImagePath() {
		return "start";
	}
	
}
