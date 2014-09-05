package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent;
import com.me.neta.events.PassportEvent;

public class PassportForm2 extends Window{
	
	static final String TEXT_FIELD_STYLE = "system2";
	static final float PADX = 22;
	static final float H = 22;
	
	TextField tfAge;
	TextField tfName;
	TextField tfState;
	TextField tfCity;
	TextField tfYear;
	
	Vector2 originalCamPosition;
	
	public void end(){
		this.addAction( Util.zoomTo(1f, 0, null));
		originalCamPosition = null;
		//getStage().getCamera().position.set(0, 0, 0);
	}
	
	public void begin(){
		this.addAction(Util.zoomTo(0.6f, 0, null));
		//originalCamPosition = new Vector2(getStage().getCamera().position.x, getStage().getCamera().position.y);
		
	}
	
	public PassportForm2(final NetaGame ng){
		super("", ng.getManager().getSkin());
		this.setClip(false);

		

		this.setOrigin(getWidth()/2, getHeight()/2);
		TextureManager tm = ng.getManager();
		Skin skin = tm.getSkin();

		
		tfName = new TextField("", skin, TEXT_FIELD_STYLE);
		tfName.setWidth(250);
		tfName.setMessageText("Имя ребёнка");
		//tfName.setBounds(PADX, 240, 228, H);
		//tfName.addListener(focusListener());
		tfName.setFocusTraversal(true);
		wrap(tfName);
	//	tfName.setFocusTraversal(focusTraversal)

		add(tfName).row();
				
		 tfAge = new TextField("", skin, TEXT_FIELD_STYLE);
		// tfAge.addListener(focusListener());
		tfAge.setMessageText("Возраст");
		//tfAge.setBounds(PADX, 205, 228, H);
		tfAge.setMaxLength(2);
		tfAge.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		add(tfAge).row();
		wrap(tfAge);
				
		 tfCity = new TextField("", skin, TEXT_FIELD_STYLE);
		tfCity.setMessageText("Город (село)");
		//tfCity.setBounds(PADX, 170, 228, H);
		//tfCity.addListener(focusListener());
		wrap(tfCity);
		add(tfCity).row();
		
		 tfState = new TextField("", skin, TEXT_FIELD_STYLE);
		tfState.setMessageText("Страна");
		//tfState.setBounds(PADX, 138, 228, H);
		//tfState.addListener(focusListener());

		add(tfState).row();
		wrap(tfState);		
		
		 tfYear = new TextField("", skin, TEXT_FIELD_STYLE);
		tfYear.setMessageText("Год");
		tfYear.setMaxLength(4);
		//tfYear.setBounds(PADX, 105, 228, H);
		tfYear.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		//tfYear.addListener(focusListener());
		
		add(tfYear).padBottom(5).row();
		wrap(tfYear);
		
		final TextButton tbOk = new TextButton("OK", skin, "button");
		add(tbOk);
		tbOk.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				PassportForm2.this.end();
				ng.getWorkspace().getPinch2Zoom().setCanPan(true);
				ng.getWorkspace().getPinch2Zoom().setCanZoom(true);
				fire(new PassportEvent());
				return true;
			}
		});
		
		
		TextureRegion tailTr = new TextureRegion( ng.getManager().getAtlas().findRegion("frame-tail"));
		Image tail = new Image(tailTr);
		tail.setSize(52, 65);
		tail.setPosition(80, -51);		
		addActor(tail);
		
		pack();
		
		
		
	}
	
	void wrap(final TextField textField){
		   textField.setOnscreenKeyboard(new TextField.OnscreenKeyboard() {
		        @Override
		        public void show(boolean visible) {
		            //Gdx.input.setOnscreenKeyboardVisible(true);
		            Gdx.input.getTextInput(new Input.TextInputListener() {
		                @Override
		                public void input(String text) {
		                    textField.setText(text);
		                }

		                @Override
		                public void canceled() {
		                    System.out.println("Cancelled.");
		                }
		            },textField.getMessageText(), "");
		        }
		    });
	}
	
/*	EventListener focusListener(){
		return new FocusListener() {
			
			public void keyboardFocusChanged (FocusEvent event, Actor actor, boolean focused) {
				if(originalCamPosition==null){
					originalCamPosition = new Vector2(getStage().getCamera().position.x, getStage().getCamera().position.y);
				}
				
				if(event.isFocused()){
				//	OrthographicCamera cam = (OrthographicCamera) actor.getStage().getCamera();
				//	cam.position.set(originalCamPosition.x, originalCamPosition.y, 0);
					
					Vector2 target = new Vector2(tfName.getX(), tfName.getY());
					Vector2 origin = new Vector2(actor.getX(), actor.getY());
					Vector2 diff = target.sub(origin);
					

					//cam.translate(-diff.x, -diff.y);
					
					actor.addAction(Util.zoomTo(0.6f, 0, null));
					
				}
				
			}
			
		};
		
	
	}*/
	
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
