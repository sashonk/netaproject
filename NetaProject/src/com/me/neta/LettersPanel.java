package com.me.neta;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.events.LetterGroupEvent;

public class LettersPanel extends Group{
	
	public LettersPanel(NetaGame ng){
	
		Table table = new Table();		
		Label variant1 = variantLabel("Вариант 1", 1,  ng);				
		Label variant2 = variantLabel("Вариант 2", 2,  ng);

		table.defaults().align(Align.left);
		table.add(variant1).row();
		table.add(variant2);
		
		table.pack();
		setSize(table.getWidth(), table.getHeight());
		
		Image back = new Image(ng.getManager().getSkin().getPatch("frame"));
		back.setSize(table.getWidth()+60, table.getHeight()+60);
		back.setPosition(-30, -40);
		addActor(back);
		addActor(table);
		
		this.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof LetterGroupEvent){
					event.getListenerActor().setVisible(false);
					return true;
				}
				return false;
			}
		});
	}
	
	Label variantLabel(String text, final int variantID, NetaGame ng){
		final Label variant1 = new Label(text, ng.getManager().getSkin(), "title_orange");
		variant1.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.getTarget().fire(new LetterGroupEvent(variantID));
				return false;
			}		
		});
		
		return variant1;
	}

}
