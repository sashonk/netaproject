package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.ContextChangeEvent;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SettingsPanel extends Group{
	TextureRegion treg;
	
	public SettingsPanel(final NetaGame ng){

		treg= ng.getManager().getMiscAtlas().findRegion("helpPanel");

		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.setBubbles(false);

				Actor panel = null;
				if(x>26 && x < 274 && y > 190 && y < 231){
					panel = hintPanel;
				}
				
				if(x>26 && x < 274 && y > 131 && y < 172){
					panel = adultsPanel;
				}
				
				if(x>26 && x < 274 && y > 77 && y < 115){
					panel = authorsPanel;
				}
				
				if(panel!=null){
					hintPanel.setVisible(false);
					adultsPanel.setVisible(false);
					authorsPanel.setVisible(false);
					
					if(panel.isVisible()){
						panel.setVisible(false);
					}
					else{
						panel.setVisible(true);
						ng.getContext().setProperty(ContextProperty.HALT, true);
						fire(new ContextChangeEvent());

					}
					
					event.getTarget().addAction(sequence(fadeOut(0.4f), visible(false)));
				}
				
				return false;
			}
			
	
		});
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentAlpha);
		batch.draw(treg, this.getX(), this.getY(), this.getWidth(), this.getHeight());				
		super.draw(batch, parentAlpha);
	}
	
	public void setAuthorsPanel(Actor authorsPanel){
		this.authorsPanel = authorsPanel;
	}
	
	public void setAdultsPanel(Actor adultsPanel){
		this.adultsPanel = adultsPanel;
	}
	
	public void setHintPanel(Actor hintPanel){
		this.hintPanel = hintPanel;
	}
	
	private Actor authorsPanel;
	
	private Actor adultsPanel;
	
	private Actor hintPanel;
}
