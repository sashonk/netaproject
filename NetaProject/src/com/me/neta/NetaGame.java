package com.me.neta;


import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.me.neta.Context.ContextProperty;




public class NetaGame implements ApplicationListener {
	
	boolean inited;
	 Native natiff;
	 boolean error = false;
	
	public Native getNative(){
		return natiff;
	}
	
	public NetaGame(Native platform){
		this.natiff = platform;
	}
	
	public static final boolean debug= true;
	  Stage stage;
	

	
	Workspace space;
	TextureManager texManager ;
	
	public Workspace getWorkspace(){
		return space;
	}
	
	public TextureManager getManager(){
		return texManager;
	}
	
	Context context;
	
	public Context getContext(){
		return context;
	}

	Texture splash;
	ShaderProgram sp;
	@Override
	public void create() {
		


		
		inited  = false;
		lastSplashRenderCall = false;
		context = new Context();
	
		 splash = new Texture(Gdx.files.internal("data/zastavka.jpg"));
		 splashBatch = new SpriteBatch();
/*			 sp = new ShaderProgram(Gdx.files.internal("data/vertexShader.txt"), Gdx.files.internal("data/fragmentShader.txt"));
			if(!sp.isCompiled()){
				Gdx.app.log("Problem loading shader:", sp.getLog());
				
			}

			splashBatch.setShader(sp);*/

		texManager = new TextureManager();
		texManager.loadResources();
		//texManager.finishLoading();
		


		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/wonderland.ttf"));
		splashFont = gen.generateFont(36, TextureManager.DEFAULT_CHARS, false);
		splashFont.setColor(Color.GRAY);
		gen.dispose();

		
		
	}
	
	SpriteBatch splashBatch;
	BitmapFont splashFont;

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	float d;
	String progress;
	boolean lastSplashRenderCall;
	
	void renderSplash(){
		splashBatch.begin();
/*		
		Random rnd = new Random();
		sp.setUniformf("u_myvar", rnd.nextFloat());
*/
		splashBatch.draw(splash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, 1024, 600, false, false);
		splashFont.draw(splashBatch, progress, 300*Gdx.graphics.getWidth()/640, 150*Gdx.graphics.getHeight()/480);
		splashBatch.end();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(!texManager.update()){
			if(d>0.4 || progress ==null){
				progress = texManager.getProgressAsString();
				d = 0;
			}
			renderSplash();

			d+=Gdx.graphics.getDeltaTime();
		}
		else{			
			if(!inited){
				if(!lastSplashRenderCall){
					progress = texManager.getProgressAsString();
					renderSplash();
					lastSplashRenderCall = true;
				}				
				else{
					texManager.init();				
					stage= new Stage(1024,768, false);
/*					PerspectiveCamera  cam = new PerspectiveCamera(90, 1024, 768);
				       cam.position.set(1024/2,368/2, 400f);
				       cam.lookAt(1024/2,768/2,0);
				       cam.near = 0.1f;
				       cam.far = 1500f;
	
				       cam.update();
					stage.setCamera(cam);*/


					
/*					 sp = new ShaderProgram(Gdx.files.internal("data/vertexShader.txt"), Gdx.files.internal("data/fragmentShader.txt"));
					if(!sp.isCompiled()){
						Gdx.app.log("Problem loading shader:", sp.getLog());						
					}*/
					
				//	stage.getSpriteBatch().setShader(sp);
				//	sp.setUniformf("u_myvar", 1f);
					
					space= new Workspace(this, 0, 0, stage.getWidth(), stage.getHeight());
					Gdx.app.debug(this.getClass().getName(), "initializing workspace");

					space.initialize();
					stage.addActor(space);		
					Gdx.input.setInputProcessor(stage);

					
					splash.dispose();
					splashBatch.dispose();
					splashFont.dispose();
					inited = true;
					
					Gdx.app.debug(this.getClass().getName(), "ready");

				}
			}			
			else{
				try{
					stage.draw();	
					
					if(!error){
						stage.act();
					}
					else{
						if(Gdx.input.isTouched()){
							Gdx.app.exit();
						}			}
				}
				catch(Exception ex){
					MessageHelper.error(this, "Критическая ошибка!", ex);
					error = true;
				}
			}
		}
		
	
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}

	}

	@Override
	public void pause() {
		System.out.println("pause");
		texManager.manage();
	}

	@Override
	public void resume() {
		System.out.println("resume");
	
	}

	@Override
	public void dispose() {

		stage.dispose();
		texManager.dispose();
	}
	


}


