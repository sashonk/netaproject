package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureManager {
	private static TextureManager instance;
	
	public static TextureManager get(){
		if(instance==null){
			instance = new TextureManager();
			
		}
		
		return instance;
	}
	
	
	private TextureManager(){
		atlas = new TextureAtlas(Gdx.files.internal("data/atlas.pack"));
		fieldsAtlas = new TextureAtlas(Gdx.files.internal("data/fields/fields.pack"));
		miscAtlas = new TextureAtlas(Gdx.files.internal("data/misc/misc.pack"));

	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}
	
	public TextureAtlas getFieldsAtlas(){
		return fieldsAtlas;
	}
	
	public TextureAtlas getMiscAtlas(){
		return miscAtlas;
	}
	
	private TextureAtlas atlas;
	
	private TextureAtlas fieldsAtlas;
	
	private TextureAtlas miscAtlas;

	
	private void disposeInternal(){
		atlas.dispose();
		fieldsAtlas.dispose();
		miscAtlas.dispose();
	}
	
	public static void dispose(){
		if(instance==null){
			return;
		}
		
		instance.disposeInternal();
	}
}
