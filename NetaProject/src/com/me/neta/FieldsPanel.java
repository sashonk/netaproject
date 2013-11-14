package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

public class FieldsPanel extends Group{
	
	public static float HOTSPOT_X =150;
	public static float HOTSPOT_Y = 0;
	
	TextureRegion region;
	
	Sprite sprite ;
	
	public FieldsPanel(){
		AtlasRegion atlasRegion = TextureManager.get().getMiscAtlas().findRegion("fieldsPanel");
		Texture tex = atlasRegion.getTexture();
		region = new TextureRegion(tex, atlasRegion.getRegionX(),atlasRegion.getRegionY(),atlasRegion.getRegionWidth(),atlasRegion.getRegionHeight() );
		sprite= new Sprite(region);
		
	}
	
	
	
	@Override
	public void draw(SpriteBatch batch , float parentA){
		super.draw(batch, parentA);
		
		Color c = getColor();
		sprite.setColor(c.r, c.g,c.b,c.a);
		sprite.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		sprite.draw(batch, parentA);
		
/*		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentA);
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());*/
	}
}
