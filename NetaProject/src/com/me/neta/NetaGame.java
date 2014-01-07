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
	static final boolean debug= true;

	Sprite panelSprite;
	Sprite splashSprite;
	Texture splashTex;
	SpriteBatch batch;
	
	Table toolPanel;
	ScreenUtils u;
	

	Stage stage;
	
	
	boolean workspaceChosen = false;
	boolean splash = true;
	
	Workspace space;
	@Override
	public void create() {
		splash =false;
		//splashTex = new Texture(Gdx.files.internal("data/zastavka.jpg"));
		//TextureRegion region = new TextureRegion(splashTex, 0, 0, 1024, 600);
		//splashSprite = new Sprite(region);
		//splashSprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		
		

		
		stage= new Stage(1024,768, false);
		
		
		
		 space= new Workspace();
		 space.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		
		//stage.addActor(new CredentialsPanel(20, stage.getHeight()-20, 100, 50));
		stage.addActor(space);
		

		Gdx.input.setInputProcessor(stage);
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
		
		
	
		if(splash){
			batch.begin();
			splashSprite.draw(batch);
			batch.end();
		}
		else{			
			stage.act();			
			stage.draw();	
		}
	
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		//splashTex.dispose();
		
		TextureManager.dispose();
	}
	
	
	EventListener clickListener(final AbstractTool tool){
		return new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof InputEvent){
					if(((InputEvent) event).getType()==InputEvent.Type.touchDown){
						tool.doAction();
					}
				}
				return true;
			}
		};
	}

}


