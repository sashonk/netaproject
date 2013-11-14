package com.me.neta;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
 

public class NetaGame implements ApplicationListener {
	
	static final float pad = 15;
	static final boolean debug= true;

	Sprite panelSprite;
	Sprite splashSprite;
	Texture splashTex;
	SpriteBatch batch;
	Texture panelTex;
	
	Table toolPanel;
	
	
	Stage stage;
	
	
	
	boolean splash = false;
	
	
	@Override
	public void create() {
		
		splashTex = TextureManager.get().getFieldsAtlas().findRegion("IGROVIE-POLY").getTexture();//new Texture(Gdx.files.internal("data/zastavka.jpg"));
		TextureRegion region = new TextureRegion(splashTex, 0, 0, 1024, 600);
		splashSprite = new Sprite(region);
		splashSprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		
		
		panelTex = new Texture(Gdx.files.internal("data/panel.png"));
		TextureRegion panelRegion = new TextureRegion(panelTex, 0, 0, 1024, 64);
		Image img = new Image(panelRegion);

		
		stage= new Stage(1024,768, false);

		Table table = new Table();
		

		table.debug();
		table.debugTable();
		
		table.add(new LinkTool()).padRight(pad).padLeft(pad);
		table.add(new BasketTool()).padRight(pad).padLeft(pad);
		table.add(new FieldsTool()).padRight(pad).padLeft(pad);
		table.add(new BookTool()).padRight(pad).padLeft(pad);
		table.add(new LetterTool()).padRight(pad).padLeft(pad);
		table.add(new FiguresTool()).padRight(pad).padLeft(pad);
		table.add(new PaletteTool()).padRight(pad).padLeft(pad);
		table.add(new CarriageTool()).padRight(pad).padLeft(pad);
		table.add(new SaveTool()).padRight(pad).padLeft(pad);
		table.add(new SettingsTool()).padRight(pad).padLeft(pad);
				
		stage.addActor(table);
		stage.addActor(img);
		table.setZIndex(99);
		
		
		Question q = new Question();
		q.setBounds(960, 700, 40, 40);
		stage.addActor(q);
		
		

		table.pack();
		System.out.println("table:"+table.getWidth()+" " +table.getHeight());

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, .1f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(splash){

			splashSprite.draw(batch);

		}
		else{
			
			stage.act();
			stage.draw();
			

		}
		batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			splash = false;
			Gdx.input.setInputProcessor(stage);
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
		splashTex.dispose();
		panelTex.dispose();
		
		TextureManager.dispose();
	}
	

}


