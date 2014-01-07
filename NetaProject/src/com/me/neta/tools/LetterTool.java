package com.me.neta.tools;

import com.me.neta.Size;
import com.me.neta.events.RequestFocusEvent;
import com.me.neta.util.WorkspaceState;


public class LetterTool extends AbstractTool{

	@Override
	public void doAction() {
		fire(new RequestFocusEvent());
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
