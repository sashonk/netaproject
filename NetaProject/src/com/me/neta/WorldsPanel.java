package com.me.neta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.neta.events.ForbiddenEvent;
import com.me.neta.events.WorldSelectionEvent;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class WorldsPanel extends Window{
	
	public static float HOTSPOT_X =150;
	public static float HOTSPOT_Y = 0;
	

	
	public WorldsPanel(final NetaGame ng, float width, float height){
		super("", ng.getManager().getSkin());
		this.setWidth(width);
		this.setHeight(height);
		
		this.addListener(new MetricListener());
		
		AtlasRegion atlasRegion =ng.getManager().getMiscAtlas().findRegion("fieldsPanel");
		Texture tex = atlasRegion.getTexture();
		TextureRegion region = new TextureRegion(tex, atlasRegion.getRegionX(),atlasRegion.getRegionY(),atlasRegion.getRegionWidth(),atlasRegion.getRegionHeight() );

		this.setBackground(new TextureRegionDrawable(region));

/*		float w = 60, h = 60;
		float dx = 0, dy = -60;

		TextureRegion lockRegion = ng.getManager().getAtlas().findRegion("lock");*/
		
/*		Image lock1 = new Image(new TextureRegion(lockRegion));
		lock1.setBounds(215+dx, 270+dy, w, h);
		addActor(lock1);*/
		
/*		Image lock2 = new Image(new TextureRegion(lockRegion));
		lock2.setBounds(510+dx, 270+dy, w, h);
		addActor(lock2);
		
		Image lock3 = new Image(new TextureRegion(lockRegion));
		lock3.setBounds(215+dx, 125+dy, w, h);
		addActor(lock3);
		
		Image lock4 = new Image(new TextureRegion(lockRegion));
		lock4.setBounds(510+dx, 125+dy, w, h);
		addActor(lock4);*/
		
		final float FIELD_WIDTH = this.getWidth()/2 - 45;
		final float FIELD_HEIGHT = this.getHeight()/2 - 50;
		final float DX = 10;
		final float DY = 10;
		final float X = 33;
		final float Y = 62;
		
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				Rectangle spider =new Rectangle(X, Y, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle tiger =new Rectangle(X+FIELD_WIDTH+DX, Y, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle ant =new Rectangle(X, Y+ FIELD_HEIGHT+DY, FIELD_WIDTH, FIELD_HEIGHT);
				Rectangle piton =new Rectangle(X+ FIELD_WIDTH+DX, Y+FIELD_HEIGHT+DY, FIELD_WIDTH, FIELD_HEIGHT);		
				
				String id = null;
				if(ant.contains(x, y)){
					id = "ant";
				}
				else if(tiger.contains(x, y)){
					//fire(new ForbiddenEvent("Поле \"Тигр\" доступно в платной версии игры.\nПерейти к платной версии?"));
					id = "tiger";
				}
				else if(spider.contains(x, y)){
					//fire(new ForbiddenEvent("Поле \"Паучок\" доступно в платной версии игры.\nПерейти к платной версии?"));
					id = "spider";
				}
				else if(piton.contains(x, y)){
					id = "piton";
					//fire(new ForbiddenEvent("Поле \"Питон\" доступно в платной версии игры.\nПерейти к платной версии?"));

				}
				
				if(id != null){
					
					Random rnd = new Random(System.currentTimeMillis());					
					if(rnd.nextInt(100)>20)
					ng.getNative().showInterstitial();

						WorldSelectionEvent ev = new WorldSelectionEvent(id);
						fire(ev);
						WorldsPanel.this.addAction(sequence(alpha(0), visible(false)));
					
				}
				
				return false;
			}
			
		});
		
		

	}
	

}
