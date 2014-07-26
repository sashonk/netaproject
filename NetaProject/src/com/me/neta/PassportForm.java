package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.me.neta.events.PassportEvent;

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
	public PassportForm(NetaGame ng){
		

		setSize(275, 275);
		this.setOrigin(getWidth()/2, getHeight()/2);
		TextureManager tm = ng.getManager();
		tr = tm.getAtlas().findRegion("passform");			
		
		Image panel = new Image(tr);
		panel.setBounds(0,0, getWidth(), getHeight());
		this.addActor(panel);
		Skin skin= tm.getSkin();
		
		tfName = new TextField("", skin, TEXT_FIELD_STYLE);
		tfName.setMessageText("Имя ребёнка");
		tfName.setBounds(PADX, 240, 228, H);
		tfName.addListener(hideKeyBoard());
		tfName.setFocusTraversal(true);
	//	tfName.setFocusTraversal(focusTraversal)

		addActor(tfName);
				
		 tfAge = new TextField("", skin, TEXT_FIELD_STYLE);
		 tfAge.addListener(hideKeyBoard());
		tfAge.setMessageText("Возраст");
		tfAge.setBounds(PADX, 205, 228, H);
		tfAge.setMaxLength(2);
		tfAge.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		addActor(tfAge);
				
		 tfCity = new TextField("", skin, TEXT_FIELD_STYLE);
		tfCity.setMessageText("Город (село)");
		tfCity.setBounds(PADX, 170, 228, H);
		tfCity.addListener(hideKeyBoard());

		addActor(tfCity);
		
		 tfState = new TextField("", skin, TEXT_FIELD_STYLE);
		tfState.setMessageText("Страна");
		tfState.setBounds(PADX, 138, 228, H);
		tfState.addListener(hideKeyBoard());

		addActor(tfState);
				
		 tfYear = new TextField("", skin, TEXT_FIELD_STYLE);
		tfYear.setMessageText("Год");
		tfYear.setMaxLength(4);
		tfYear.setBounds(PADX, 105, 228, H);
		tfYear.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		tfYear.addListener(hideKeyBoard());
		addActor(tfYear);
		
		final Label ok = new Label("OK", skin, "system2");
		ok.setPosition(188, 74);
		addActor(ok);
		
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				Rectangle r = new Rectangle(171,74, 220 -171, 91 - 74);
				if(r.contains(x,y)||event.getTarget()==ok){ //touch!
					
					PassportForm.this.addAction(Util.zoomTo(1, 0, null));
					fire(new PassportEvent());
				}
				return false;
			}
		});
	}
	
	EventListener hideKeyBoard(){
		return new InputListener(){
			public boolean keyTyped (InputEvent event, char character) {
				if(Keys.ENTER==event.getKeyCode()){				
					//Gdx.input.setOnscreenKeyboardVisible(false);
					Stage s = event.getStage();
					OrthographicCamera cam =  (OrthographicCamera) s.getCamera();
					cam.translate(new Vector2(0, -H));
					return true;
				}
				return false;
				
			}
	 };
	}
	
	public void update(Passport passport){
		if(tfName.getText().length()>0){
			passport.name = tfName.getText();
		}
		else{
			passport.name = null;
		}		
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
		if(tfState.getText().length()>0){
			passport.country = tfState.getText();
		}
		else{
			passport.country = null;
		}
		if(tfCity.getText().length()>0){
			passport.city = tfCity.getText();
		}
		else{
			passport.city = null;
		}
	}
	

}
