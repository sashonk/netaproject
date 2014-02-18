package com.me.neta.tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.NetaGame;

public abstract class PanelTool extends AbstractTool{
	public PanelTool(NetaGame ng) {
		super(ng);
		// TODO Auto-generated constructor stub
	}

	protected final static float FADE_INTERVAL = .5f;

	protected List<PanelToolListener> listeners = new LinkedList<PanelToolListener>();
	protected Actor panel;
	public void registerListener(PanelToolListener listener){
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}
	public void setPanel(Actor panel){
		this.panel= panel;
	}
	
	private void onHide(){
		for(PanelToolListener listener : listeners){
			listener.onHide(this);
		}
	}
	
	private void onShow(){
		for(PanelToolListener listener : listeners){
			listener.onShow(this);
		}
	}

	@Override
	public void doAction() {

		if(panel.isVisible()){
			onHide();
			hide();

		}
		else{
			onShow();
			show();

		}		
	}

	public void hide(){
		panel.addAction(sequence(fadeOut(FADE_INTERVAL), visible(false)));				

	}
	
	public void show(){
		panel.addAction(sequence(visible(true), fadeIn(FADE_INTERVAL)));

	}

}
 