package com.me.neta;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

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
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.LetterVariantEvent;

public class FlowerPanel extends Group{
	Label variant1 ;
	Label variant2 ;
	Table table;
	Image back;
	public FlowerPanel(NetaGame ng){
	
		 table = new Table();		
		 variant1 = variantLabel("Вариант 1", 1,  ng);				
		 variant2 = variantLabel("Вариант 2", 2,  ng);

		table.defaults().align(Align.left);
		table.add(variant1).row();
		table.add(variant2);
		
		table.pack();
		setSize(table.getWidth(), table.getHeight());
		
		
		 back = new Image(ng.getManager().getSkin().getPatch("frame"));

		back.setSize(table.getWidth()+60, table.getHeight()+60);
		back.setPosition(-30, -40);
		addActor(back);
		addActor(table);
		
		this.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof LetterVariantEvent){
					event.getListenerActor().addAction(sequence(fadeOut(0.4f), visible(false)));	
					return true;
				}
				return false;
			}
		});
	}
	
	public void setVariants(Map<Integer, Map<Integer, List<Character>>> variants){
		Map<Integer, List<Character>> v1 = variants.get(Integer.valueOf(1));
		StringBuilder sb = new StringBuilder();
		for(Integer group : v1 .keySet()){
			List<Character> chars = v1.get(group);
			int count = 0;
			for(Character c : chars){
				if(count>0){
					sb.append(',');
					count++;
				}
				sb.append(c);				
			}
			sb.append(' ');
		}
		variant1.setText(sb.toString());
		//variant1.pack();
		
		StringBuilder sb2 = new StringBuilder();	
		Map<Integer, List<Character>> v2 = variants.get(Integer.valueOf(2));
		for(Integer group : v2 .keySet()){
			List<Character> chars = v2.get(group);
			int count = 0;
			for(Character c : chars){
				if(count>0){
					sb2.append(',');
					count++;
				}
				sb2.append(c);				
			}
			sb2.append(' ');
		}
		variant2.setText(sb2.toString());
		//variant2.pack();
		
		table.pack();
		setSize(table.getWidth(), table.getHeight());
		
		back.setSize(table.getWidth()+60, table.getHeight()+60);

		Util.center(this);
		

	}
	
	Label variantLabel(String text, final int variantID, NetaGame ng){
		final Label variant1 = new Label(text, ng.getManager().getSkin(), "title_red");
		variant1.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.getTarget().fire(new LetterVariantEvent(variantID));
				return false;
			}		
		});
		
		return variant1;
	}

}
