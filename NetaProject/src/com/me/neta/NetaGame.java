package com.me.neta;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.neta.Context.ContextProperty;




public class NetaGame implements ApplicationListener {
	
	
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
	
	void initContext(){
		context.setProperty(ContextProperty.HALT, Boolean.TRUE);


	}
	
	@Override
	public void create() {
		
		context = new Context();
		initContext();
	
		texManager = new TextureManager();
		texManager.init();
				
		stage= new Stage(1024,768, false);
			
		space= new Workspace(this, 0, 0, stage.getWidth(), stage.getHeight());
		space.initialize();

		stage.addActor(space);
		

		Gdx.input.setInputProcessor(stage);


	}
	
	public void zoom(float value){
		OrthographicCamera camera = (OrthographicCamera) stage.getCamera();
		camera.zoom = value;	
	}
	
	public void setCameraPosition(Vector2 position){
		OrthographicCamera camera = (OrthographicCamera) stage.getCamera();
		camera.position.set(position.x, position.y, 0);
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


