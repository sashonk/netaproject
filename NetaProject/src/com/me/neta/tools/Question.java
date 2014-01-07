package com.me.neta.tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Size;
import com.me.neta.events.WorkspaceStateEvent;
import com.me.neta.util.WorkspaceState;


public class Question extends AbstractTool{

	public Actor panel;
	
	@Override
	public Size getSize() {
		return new Size(60, 60);
	}

	@Override
	public void doAction() {
		if(!panel.isVisible()){
			panel.setVisible(true);
			fire(new WorkspaceStateEvent(WorkspaceState.HALT));
		}
		else{
			panel.setVisible(false);
		}
	}

	@Override
	public String getImagePath() {
		return "question";
	}
	
	public void setPanel(Actor p){
		panel = p;
	}

	@Override
	public boolean accept(WorkspaceState state) {
		return state!=WorkspaceState.HALT;
	}

}
