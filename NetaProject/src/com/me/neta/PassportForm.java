package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.me.neta.util.WorkHelper;

public class PassportForm extends Group{
	public PassportForm(float width, float height){
		setSize(width, height);
		TextureManager tm = TextureManager.get();
		Skin skin = tm.getSkin();
		
		Image panel = new Image(skin.getPatch("system"));
		panel.setSize(width,height);
		
		Label nameLabel = new Label("Имя", skin, "system");
		TextField nameField = new TextField("", skin, "system");
		
		Label ageLabel = new Label("Возраст", skin, "system");
		TextField ageField = new TextField("", skin, "system");		
		
		Table table = new Table();
		table.add(nameLabel);
		table.add(nameField);
		table.row();
		table.add(ageLabel);
		table.add(ageField);
		table.row();
		
		addActor(panel);
		addActor(table);
		WorkHelper.center(table);
	}
}
