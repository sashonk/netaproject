package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.QuestionEvent;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SettingsPanel extends Window{
	TextureRegion treg;
	NetaGame ng;
	public SettingsPanel(final NetaGame ng){
		//super(ng, );

		super("", ng.getManager().getSkin());
		this.ng = ng;
		this.setClip(false);

		
		Table table = this;
		TextButton item1 = new TextButton("Подсказка для взрослых", ng.getManager().getSkin(), "menuitem");
		TextButton item2 = new TextButton("Записка для взрослых", ng.getManager().getSkin(), "menuitem");
		TextButton item3 = new TextButton("Настройки", ng.getManager().getSkin(), "menuitem");
		TextButton item4 = new TextButton("Об авторах", ng.getManager().getSkin(), "menuitem");

		table.add(item1).row();
		table.add(item2).row();
		table.add(item3).row();
		table.add(item4);


		TextureRegion tr = new TextureRegion( ng.getManager().getAtlas().findRegion("frame-tail"));
		Image tail = new Image(tr);
		tail.setSize(52, 65);
		tail.setPosition(230, -51);
		addActor(tail);
		table.pack();
		
		item1.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					showPanel(hintPanel);
					SettingsPanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});
		
		item2.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					showPanel(adultsPanel);
					SettingsPanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});
		
		item3.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					settingsEditor.readData();
					showPanel(settingsEditor);
					SettingsPanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});		
		
		item4.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					showPanel(authorsPanel);
					SettingsPanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});
		

	}
	
	void showPanel(Actor panel){
		if(panel!=null){
			hintPanel.setVisible(false);
			adultsPanel.setVisible(false);
			authorsPanel.setVisible(false);
			settingsEditor.setVisible(false);
			
			if(panel.isVisible()){
				panel.setVisible(false);
			}
			else{
			
				
				panel.setVisible(true);
				ng.getContext().setProperty(ContextProperty.HALT, Boolean.TRUE);

			}
			
		}
	}
	
	public void setAuthorsPanel(Actor authorsPanel){
		this.authorsPanel = authorsPanel;
	}
	
	public void setAdultsPanel(Actor adultsPanel){
		this.adultsPanel = adultsPanel;
	}
	
	public void setSettingsEditor(SettingsEditor settingsEditorPanel){
		this.settingsEditor = settingsEditorPanel;
	}
	
	public void setHintPanel(Actor hintPanel){
		this.hintPanel = hintPanel;
	}
	
	private SettingsEditor settingsEditor;
	
	private Actor authorsPanel;
	
	private Actor adultsPanel;
	
	private Actor hintPanel;
}
