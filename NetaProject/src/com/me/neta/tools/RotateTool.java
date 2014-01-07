package com.me.neta.tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Size;
import com.me.neta.events.RotationEvent;
import com.me.neta.util.WorkspaceState;

public class RotateTool extends AbstractTool{

	public Actor panel;
	
	@Override
	public Size getSize() {
		return new Size(60, 60);
	}

	@Override
	public void doAction() {
		fire(new RotationEvent(-45));
	}

	@Override
	public String getImagePath() {
		return "POVOROT";
	}
	

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}


}
