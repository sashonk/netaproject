package com.me.neta;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.me.neta.tools.AbstractTool;



public class NetaGame implements ApplicationListener {
	
	public static Native natiff;
	
	public NetaGame(Native platform){
		this.natiff = platform;
	}
	
	static final boolean debug= true;
	public static Stage stage;
	

	
	Workspace space;
	@Override
	public void create() {
		TextureManager.destroy();
		if(stage!=null){
			stage.dispose();
		}
	
		

		stage= new Stage(1024,768, false);
		
		
		
		 space= new Workspace(0, 0, stage.getWidth(), stage.getHeight());
		// space.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		
		//stage.addActor(new CredentialsPanel(20, stage.getHeight()-20, 100, 50));
		stage.addActor(space);
		

		Gdx.input.setInputProcessor(stage);
		//stage.getRoot().addCaptureListener(new Pinch2ZoomListener2());
	//	Gdx.input.setOnscreenKeyboardVisible(true);
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
	//	Gdx.gl.glClearColor(1, .1f, 1, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
	
		try{
			stage.draw();	
			stage.act();
		}
		catch(Exception ex){
			MessageHelper.error("Критическая ошибка!", ex);
		}

		
	
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}

	}

	@Override
	public void pause() {
		System.out.println("pause");
		TextureManager.manage();
	}

	@Override
	public void resume() {
		System.out.println("resume");
	
	}

	@Override
	public void dispose() {
		stage.dispose();
		TextureManager.dispose();
	}
	


}


