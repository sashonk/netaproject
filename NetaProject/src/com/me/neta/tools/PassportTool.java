package com.me.neta.tools;

import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Util;
import com.me.neta.events.RequestFocusEvent;


public class PassportTool extends PanelTool{


	public PassportTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void doAction() {

		if(panel.isVisible()){
			onHide();
			hide();
			panel.addAction(Util.zoomTo(1f, 0, null));
		}
		else{
			onShow();
			show();
			
			panel.addAction(Util.zoomTo(0.4f, 0, null));
			
		}		
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
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.HALT)==null &&ctx.getProperty(ContextProperty.WORKING)!=null&&
				popupAccepted( ctx);	
		}

}
