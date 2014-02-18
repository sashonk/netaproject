package com.me.neta.tools;


import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class SettingsTool extends PanelTool{




	public SettingsTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getImagePath() {
		return "tools";
	}
	
	@Override
	public Size getSize() {
		return new Size(78 , 78);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING || state==WorkspaceState.PREPARED;
	}
}
