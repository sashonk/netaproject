package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.SavePanel;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;


public class SaveTool extends PanelTool{




	public SaveTool(NetaGame ng) {
		super(ng);
		timesFired=0;	}

	@Override
	public String getImagePath() {
		return "save";
	}
	
	@Override
	public Size getSize() {
		return new Size(80 , 80);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null&&ctx.getProperty(ContextProperty.INGAME)==null &&ctx.getProperty(ContextProperty.WORKING)!=null&&
				popupAccepted( ctx);	
	}
	

	public void hide(){
		if(panel.isVisible()){
			SavePanel sp = (SavePanel) panel;
			int timesHidden = sp.getTimesHidden();
			fire(new SavePanelHideEvent(timesHidden+1));
			sp.setTimesHidden(timesHidden+1);
		}
		panel.addAction(sequence(fadeOut(FADE_INTERVAL), visible(false)));				


	}
	
	int timesFired;
	
	public static final class SavePanelHideEvent extends Event{
		public SavePanelHideEvent(int timesFired){
			this.tf = timesFired;
		}
		
		public int getTimesFired(){
			return tf;
		}
		int tf;
	}
}
