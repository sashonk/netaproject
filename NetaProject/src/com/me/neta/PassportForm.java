package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.me.neta.events.PassportEvent;
import com.me.neta.util.WorkHelper;

public class PassportForm extends Group{
	
	static final String TEXT_FIELD_STYLE = "system2";
	static final float PADX = 22;
	static final float H = 22;
	TextureRegion tr;
	
	TextField tfAge;
	TextField tfName;
	TextField tfState;
	TextField tfCity;
	TextField tfYear;
	public PassportForm(){

		setSize(275, 275);
		TextureManager tm = TextureManager.get();
		tr = tm.getAtlas().findRegion("passform");			
		
		Image panel = new Image(tr);
		panel.setBounds(0,0, getWidth(), getHeight());
		this.addActor(panel);
		this.addListener(new MetricListener());
		Skin skin= tm.getSkin();
		
		tfName = new TextField("", skin, TEXT_FIELD_STYLE);
		tfName.setMessageText("Имя ребёнка");
		tfName.setBounds(PADX, 240, 228, H);
		addActor(tfName);
				
		 tfAge = new TextField("", skin, TEXT_FIELD_STYLE);
		tfAge.setMessageText("Возраст");
		tfAge.setBounds(PADX, 205, 228, H);
		tfAge.setMaxLength(2);
		tfAge.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		addActor(tfAge);
				
		 tfCity = new TextField("", skin, TEXT_FIELD_STYLE);
		tfCity.setMessageText("Город (село)");
		tfCity.setBounds(PADX, 170, 228, H);
		addActor(tfCity);
		
		 tfState = new TextField("", skin, TEXT_FIELD_STYLE);
		tfState.setMessageText("Страна");
		tfState.setBounds(PADX, 138, 228, H);
		addActor(tfState);
				
		 tfYear = new TextField("", skin, TEXT_FIELD_STYLE);
		tfYear.setMessageText("Год");
		tfYear.setMaxLength(4);
		tfYear.setBounds(PADX, 105, 228, H);
		tfYear.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());	
		addActor(tfYear);
		
		
		panel.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Rectangle r = new Rectangle(171,74, 220 -171, 91 - 74);
				if(r.contains(x,y)){ //touch!
					fire(new PassportEvent());
				}
				return false;
			}
		});
	}
	
	public void update(Passport passport){
//		form.
		passport.name = tfName.getText();
		
		if(tfAge.getText().length()>0)
		passport.age = Integer.valueOf(tfAge.getText());
		else{
			passport.age = null;
		}
		
		if(tfYear.getText().length()>0){
			passport.year = Integer.valueOf(tfYear.getText());
		}
		else{
			passport.year = null;
		}
		
		passport.country = tfState.getText();
		passport.city = tfCity.getText();
				
	}
	

}
