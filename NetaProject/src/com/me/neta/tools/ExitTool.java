package com.me.neta.tools;

import com.badlogic.gdx.Gdx;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class ExitTool extends AbstractTool{

	public ExitTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		Gdx.app.exit();
	}

	@Override
	public String getImagePath() {
		return "link";
	}
	
	@Override
	public Size getSize() {
		return new Size(76 , 78);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING || state==WorkspaceState.PREPARED;
	}
}
