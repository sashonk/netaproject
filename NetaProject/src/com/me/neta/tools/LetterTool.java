package com.me.neta.tools;

import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.events.RequestFocusEvent;
import com.me.neta.util.WorkspaceState;


public class LetterTool extends PanelTool{


	public LetterTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getImagePath() {
		return "letter";
	}
	
	
	@Override
	public Size getSize() {
		return new Size(118 , 78);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}

}
