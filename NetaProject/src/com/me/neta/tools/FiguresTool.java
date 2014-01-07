package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class FiguresTool extends PanelTool{



	@Override
	public String getImagePath() {
		return "figures";
	}
	
	@Override
	public Size getSize() {
		return new Size(78 , 78);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING ;
	}
}
