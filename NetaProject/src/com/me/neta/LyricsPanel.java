package com.me.neta;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.me.neta.events.LyricsIconEvent;

public class LyricsPanel extends Group{
	
	TextureRegion treg ;
	Table t;
	static float mul = .4f;
	
	Drawable d;
	
	LyricsPanel(final float width,final float height){
		
		Skin skin = TextureManager.get().getSkin();
		d = skin.newDrawable("white");
		
		setWidth(width);
		setHeight(height);
		
		AtlasRegion atlasRegion = TextureManager.get().getMiscAtlas().findRegion("lyricsPanel");
		Texture tex = atlasRegion.getTexture();
		treg= new TextureRegion(tex, atlasRegion.getRegionX(),atlasRegion.getRegionY(),atlasRegion.getRegionWidth(),atlasRegion.getRegionHeight() );

		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {						
				Rectangle r1 = new Rectangle(21f/800f*width, 90f/400f*height, (220f-21f)/800f*width, (370f-90f)/400f*height);
				Rectangle r2 = new Rectangle(246f/800f*width, 90f/400f*height, (375f-246f)/800f*width, (370f-90f)/400f*height);
				Rectangle r3 = new Rectangle(405f/800f*width, 90f/400f*height, (570f-405f)/800f*width, (370f-90f)/400f*height);
				Rectangle r4 = new Rectangle(600f/800f*width, 90f/400f*height, (773f-600f)/800f*width, (370f-90f)/400f*height);

				int choice = -1;
				if(r1.contains(x, y)){
					choice = 1;				}
				
				if(r2.contains(x, y)){
					choice =2;				}				
				if(r3.contains(x, y)){
					choice = 3;				}				
				if(r4.contains(x, y)){
					choice = 4;}
				
				if(choice>0){
					System.out.println("lyricsPanel::choise="+choice);
					LyricsIconEvent ev = new LyricsIconEvent(choice);
					
					fire(ev);
				}
				
				return true;
			}
		});


	}
		
	
	@Override
	public void draw(SpriteBatch batch , float parentA){

				
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentA);
		batch.draw(treg, this.getX(), this.getY(), this.getWidth(), this.getHeight());				
		super.draw(batch, parentA);

	}
	


}
