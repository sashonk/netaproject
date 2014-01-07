package com.me.neta;

import java.util.LinkedList;
import java.util.List;

import com.me.neta.tools.PanelTool;
import com.me.neta.tools.PanelToolListener;

public class PanelToolGroup implements PanelToolListener{
	
	List<PanelTool> panelTools = new LinkedList<PanelTool>(); 
	public void addTool(PanelTool tool){
		if(!panelTools.contains(tool)){
			panelTools.add(tool);
			tool.registerListener(this);
		}
	}
	@Override
	public void onHide(PanelTool onHideTool) {
		// no-op
	}
	@Override
	public void onShow(PanelTool onShowTool) {
		for(PanelTool tool : panelTools){
			if(tool!=onShowTool)
			tool.hide();
		}
	}
}
