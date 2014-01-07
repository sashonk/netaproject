package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.LyricsPanel;
import com.me.neta.Size;
import com.me.neta.util.WorkspaceState;


public class ColorTool extends PanelTool{


	
	@Override
	public String getImagePath() {
		return "palette";
	}
	
	@Override
	public Size getSize() {
		return new Size(100 , 78);
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state==WorkspaceState.WORKING;
	}
}