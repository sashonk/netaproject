package com.me.neta.tools;

import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class ShopTool extends PanelTool{


	public ShopTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
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
