package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Popup.PopupGroup;
import com.me.neta.events.FiguresShowEvent;


public class FiguresTool extends PanelTool{

	int timesFired;

	public FiguresTool(NetaGame ng) {
		super(ng);
		timesFired = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getImagePath() {
		return "figures";
	}
	
	@Override
	public Size getSize() {
		return new Size(78 , 78);
	}

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null&&ctx.getProperty(ContextProperty.INGAME)==null &&ctx.getProperty(ContextProperty.WORKING)!=null&&ctx.getProperty(ContextProperty.CELLARS)!=null&&
				popupAccepted( ctx);	
	}
	
	
	@Override
	public void doAction() {

		if(panel.isVisible()){
			onHide();
			if(panel.isVisible()){
				fire(new FiguresShowEvent(++timesFired));
			}
			hide();

		}
		else{
			onShow();
			show();

		}		
	}
	

}
