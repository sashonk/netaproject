package com.me.neta.tools;

import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class CarriageTool extends AbstractTool{

	@Override
	public void doAction() {
		
	}

	@Override
	public String getImagePath() {
		return "carriage";
	}
	
	@Override
	public Size getSize() {
		return new Size(93 , 100);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING || state==WorkspaceState.PREPARED;
	}
}
