package com.me.neta;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Context.ContextProperty;

public class SettingsEditor extends Window{
	final CheckBox showNikole;
	final CheckBox showPopup ;
	public void readData(){
		Preferences prefs = Gdx.app.getPreferences(NetaGame.class.getName());
		showNikole.setChecked(prefs.getBoolean("showNikole", true));
		showPopup.setChecked(prefs.getBoolean("showPopup", true));
	}
	
	public SettingsEditor(final NetaGame ng) {
		super("", ng.getManager().getSkin());
		
		
		
		showNikole = new CheckBox("Письмо Николь", ng.getManager().getSkin());
		 showPopup = new CheckBox("Всплывающие подсказки", ng.getManager().getSkin());
		TextButton save = new TextButton("Сохранить", ng.getManager().getSkin(), "button");
		TextButton close = new TextButton("Отмена", ng.getManager().getSkin(), "button");
		
		this.defaults().align(Align.left).pad(5);
		this.add(showNikole).row();
		this.add(showPopup).row();
		
		Table buttons = new Table();
		buttons.add(save);
		buttons.add(close);
		buttons.pack();
		this.add(buttons);			
		this.pack();
		
		
		
		save.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Preferences prefs = Gdx.app.getPreferences(NetaGame.class.getName());
				prefs.putBoolean("showNikole", showNikole.isChecked());
				prefs.putBoolean("showPopup", showPopup.isChecked());
				prefs.flush();
				SettingsEditor.this.setVisible(false);

				ng.getContext().setProperty(ContextProperty.HALT, null);
				
				return false;
			}	
		});
		
		close.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				//SettingsEditor.this.addAction(sequence(fadeOut(0.4f), visible(false)));
				SettingsEditor.this.setVisible(false);

				ng.getContext().setProperty(ContextProperty.HALT, null);

				
				return true;
			}	
		});
	}

}
