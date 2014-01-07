package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.neta.events.SelectColorEvent;
import com.me.neta.util.ColorHelper;

public class ColorPanel extends Group{	TextureRegion treg;

	Rectangle[][] regions ;
	Color[][] colors ;
	 Texture debug;
	
	public ColorPanel(){
		treg = TextureManager.get().getMiscAtlas().findRegion("colorPanel");
		
		final float dx = 87;
		final float dy = 73;
		final float startx = 31;
		final float starty = 378;
		final float w = 65;
		final float h = 55;
		 regions = new Rectangle[5][6];
		// colors = new Color[5][6];
		 
		 Pixmap pmap = new Pixmap(1, 1 , Format.RGBA8888);
		 pmap.setColor(new Color(1,1,1, .3f));
		 pmap.fill();
		 debug = new Texture(pmap);
		 
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 6 ; j++){
				regions[i][j] = new Rectangle(startx + j*dx, starty - i*dy , w, h );
				
			}
		}
		
		addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {										
					for(int i = 0; i < 5; i++){
						for(int j = 0; j < 6 ; j++){
							if(regions[i][j].contains(x, y)){
								SelectColorEvent ev = new SelectColorEvent(colors[i][j]);
								event.getTarget().fire(ev);
							}
						}
					}
					
					
					return false;
				}
		});
		
		populateColors();
		
		addListener(new MetricListener());
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentAlpha);
		batch.draw(treg, this.getX(), this.getY(), this.getWidth(), this.getHeight());				
		super.draw(batch, parentAlpha);
	
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 6 ; j++){
				//batch.setColor(Color.BLACK);
				//batch.draw(debug, regions[i][j].x+getX(), regions[i][j].y+getY(), regions[i][j].width,regions[i][j].height);
			}
		}
	}
	
	void populateColors(){
		colors = ColorHelper.getColors();
	}
	

}
