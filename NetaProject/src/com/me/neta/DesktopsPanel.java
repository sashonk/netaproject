package com.me.neta;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.neta.events.DesktopIconEvent;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class DesktopsPanel extends Group{
	
	public static float HOTSPOT_X =150;
	public static float HOTSPOT_Y = 0;
	
	TextureRegion region;
	
	Sprite sprite ;
			
	List<Texture> textures = new ArrayList<Texture>(4);
	
	Texture field1Tex;
	
	Texture field2Tex;

	
	Texture field3Tex;

	Texture field4Tex;

	
	public DesktopsPanel(NetaGame ng, float width, float height){
		this.setWidth(width);
		this.setHeight(height);
		
		AtlasRegion atlasRegion =ng.getManager().getMiscAtlas().findRegion("fieldsPanel");
		Texture tex = atlasRegion.getTexture();
		region = new TextureRegion(tex, atlasRegion.getRegionX(),atlasRegion.getRegionY(),atlasRegion.getRegionWidth(),atlasRegion.getRegionHeight() );
		sprite= new Sprite(region);
		


		
		final float FIELD_WIDTH = this.getWidth()/2 - 45;
		final float FIELD_HEIGHT = this.getHeight()/2 - 50;
		final float DX = 10;
		final float DY = 10;
		final float X = 33;
		final float Y = 62;
		
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				Rectangle r1 =new Rectangle(X, Y, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle r2 =new Rectangle(X+FIELD_WIDTH+DX, Y, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle r3 =new Rectangle(X, Y+ FIELD_HEIGHT+DY, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle r4 =new Rectangle(X+ FIELD_WIDTH+DX, Y+FIELD_HEIGHT+DY, FIELD_WIDTH, FIELD_HEIGHT);		
				
				int id = -1;
				if(r1.contains(x, y)){
					id = 2;
				}
				else if(r2.contains(x, y)){
					id = 4;
				}
				else if(r3.contains(x, y)){
					id = 1;
				}
				else if(r4.contains(x, y)){
					id = 3;
				}
				
				if(id>0){
					DesktopIconEvent ev = new DesktopIconEvent(id);
					fire(ev);
					DesktopsPanel.this.addAction(sequence(alpha(0), visible(false)));
				}
				
				return false;
			}
			
		});
		
		

	}
	
	
	
	@Override
	public void draw(SpriteBatch batch , float parentA){

				
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentA);
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		super.draw(batch, parentA);
		

	}
}
