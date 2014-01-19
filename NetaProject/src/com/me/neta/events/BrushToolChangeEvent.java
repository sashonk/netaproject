package com.me.neta.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class BrushToolChangeEvent extends Event{
	public BrushToolChangeEvent(boolean checked){
		m_checked = checked;
	}
	
	public boolean isChecked(){
		return m_checked;
	}
	
	private boolean m_checked;
}
