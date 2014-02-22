package com.me.neta.tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.neta.Context;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.QuestionEvent;



public class QuestionTool extends TopTool{

	Context context;
	public QuestionTool(NetaGame ng) {
		super(ng);
		context = ng.getContext();
		// TODO Auto-generated constructor stub
	}

	public Actor panel;
	


	@Override
	public void doAction() {
		if(!panel.isVisible()){
			panel.setVisible(true);
			context.setProperty(ContextProperty.HALT, true) ;
			fire(new QuestionEvent());
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
	public boolean accept(Context ctx) {
		return !ctx.getProperty(ContextProperty.HALT) ;
	}

}
