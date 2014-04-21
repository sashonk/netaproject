package com.me.neta;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.me.neta.events.DragEvent;
import com.me.neta.events.DragStartEvent;
import com.me.neta.events.DragStopEvent;
import com.me.neta.events.LetterDropEvent;
import com.me.neta.events.SelectFigureEvent;
import com.me.neta.figures.AbstractFigure;

public abstract class Factory extends Actor{
	protected NetaGame ng;
	
	public Factory(NetaGame ng){
		this.ng = ng;
		 final DragAndDrop dad = new DragAndDrop();
		 dad.addSource(new DragAndDrop.Source(this) {
			
			@Override
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				

				 
				
				Actor dragActor = createDragActor();
				dragActor.setOrigin(x, y);
				
				dad.setDragActorPosition(-x, dragActor.getHeight()-y);
				payload.setValidDragActor(dragActor);
				payload.setInvalidDragActor(createInvalidDragActor());
				
				payload.setDragActor(dragActor);
				
				getActor().fire(new DragStartEvent());
				
				return payload;
			}
			
			
			public void dragStop (InputEvent event, float x, float y, int pointer, Target target) {
				getActor().fire(new DragStopEvent());
				
			}
		});
		 
		 dad.addTarget(new DragAndDrop.Target(ng.getWorkspace()) {
			
			@Override
			public void drop(Source source, Payload payload, float x, float y,
					int pointer) {
				float ox = payload.getDragActor().getOriginX();
				 float oy = payload.getDragActor().getOriginY();
				
				
				Actor letter = createActor();
				letter.addListener(new InputListener() {
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						event.getListenerActor().fire(new SelectFigureEvent());

						return false;
					}

				});
				
/*				letter.addListener(new DoubleClickListener(.8f) {
						
						@Override
						public void doubleClick() {
							getActor().fire(new SelectFigureEvent());

						}
					});*/
				letter.addListener(new InputListener(){
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						event.getListenerActor().fire(new SelectFigureEvent());
						return false;
					}
				});
				letter.setPosition(getActor().getX()+x-ox, getActor().getY()+y-oy);
				
				Factory.this.fire(new LetterDropEvent(letter));
			}
			
			@Override
			public boolean drag(Source source, Payload payload, float x, float y,
					int pointer) {
				
				source.getActor().fire(new DragEvent(payload.getDragActor()));
				
				
				return true;
			}
		});
	}
	
	public abstract Actor createDragActor();
	
	
	public abstract Actor createActor();
	
	public abstract Actor createInvalidDragActor();
}
