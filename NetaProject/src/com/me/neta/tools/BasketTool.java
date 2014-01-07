package com.me.neta.tools;

import com.me.neta.Size;
import com.me.neta.events.TrashButtonEvent;
import com.me.neta.util.WorkspaceState;


public class BasketTool extends AbstractTool{

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
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}


	
}
