package com.me.neta;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Cloud extends Window{
	
	public Cloud(String text, NetaGame ng) {
		this(text, ng, null);
	}

	public Cloud(String text, NetaGame ng, final Callback r ) {
		super("", ng.getManager().getSkin());
		setClip(false);
		defaults().padLeft(30).padRight(30).padTop(35).padBottom(35);
		
		
		TextureRegion tr = new TextureRegion(ng.getManager().getAtlas().findRegion("cloud"));		
		this.setBackground(new TextureRegionDrawable(tr));
		
		Label label = new Label(text, ng.getManager().getSkin());
		add(label);
		
		if(r!=null){
			Image arrow = new Image( ng.getManager().getAtlas().findRegion("OBLAKO_STRELKA"));
			arrow.setSize(100, 50);
			arrow.setPosition(230, 00);
			addActor(arrow);
			
			float dur = .6f; 
			Interpolation inter = Interpolation.sine;
			arrow.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(0.7f, 0.7f, dur,inter), Actions.scaleTo(1f, 1f, dur, inter))));
			
			addListener(new ClickListener(){
				public void clicked (InputEvent event, float x, float y) {
					r.run(Cloud.this);
				}
			});
		}
		
		
		pack();
	}

	public static interface Callback{
		public  void run(Cloud cloud);
	}
}
