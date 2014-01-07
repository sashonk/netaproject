package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.me.neta.util.WorkspaceState;

public class WorkspaceStateEvent extends Event{
	WorkspaceState state;
	public WorkspaceStateEvent(WorkspaceState state){
		this.state = state;
	}
	
	public WorkspaceState getState(){
		return state;
	}
}
