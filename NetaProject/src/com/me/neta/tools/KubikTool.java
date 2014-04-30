package com.me.neta.tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.sql.RowSet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.neta.Context;
import com.me.neta.Context.ContextProperty;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Util;
import com.me.neta.Util.OnEventAction.Predicate;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class KubikTool extends TopTool{


	int position;
	int offset = 60;
	boolean rotating ;
	public KubikTool(NetaGame ng) {
		super(ng);
		

		rotating = false;
	}
	
	

	@Override
	public boolean accept(Context ctx) {
		return ctx.getProperty(ContextProperty.WORKING)!=null&& ctx.getProperty(ContextProperty.BETWEEN_CELLARS)!=null&& popupAccepted(ctx);
	}

	@Override
	public void doAction() {
		if(!rotating){
			
			
			this.clearActions();
			
			Random rnd = new Random(System.currentTimeMillis());
			int count = rnd.nextInt(30)+1;
			final int targetValue = count * offset;
			
			this.addAction(sequence(new TemporalAction(count * .15f) {
				
				@Override
				protected void update(float percent) {
					// TODO Auto-generated method stub
					int percentInt = (int) (100f * percent);
					int value  =  (int) ((targetValue ) *percentInt)/ 100;
					int rawValue = value % 360;
					
					position = 360 - rawValue;
					
				}
			}, run(new Runnable() {
				
				@Override
				public void run() {
					rotating = false;
				}
			})) );
			

			

			rotating = true;
		}
		
		
	}

	
	void trigger(final LinkedList<Actor> parts){
		

			Action trigger123 = Util.onEvent(run(new Runnable() {
				
				@Override
				public void run() {
					Actor lower = parts.get(1);
					float y = lower.getY();
					lower.setY(y+ (parts.get(0).getHeight()+ parts.get(0).getHeight()));
					parts.addFirst(parts.remove(1));
					
					trigger(parts);
				}
			}), new Predicate() {

				@Override
				public boolean accept() {
					//return actor123.getY()-KubikTool.this.getSize().width<=-actor123.getHeight();
					return parts.get(0).getY()-KubikTool.this.getSize().width<=-(parts.get(0).getHeight());
					
					
				} 
				
			});
			
			addAction(trigger123);
		
		

	}
	
	@Override
	public String getImagePath() {
		return "kubik";
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		float alphaMul = enabled() ? 1 : disabledAlphaValue;
		Color c = getColor();
		batch.setColor(1, 1, 1, c.a*alphaMul*parentAlpha);
		
		if(position+offset > region.getRegionHeight()){
			batch.draw(region.getTexture(), getX(), getY()+getHeight()*(position+offset - region.getRegionHeight())/offset, getWidth(), getHeight()*(region.getRegionHeight()-position)/offset, region.getRegionX(), region.getRegionY()+position, region.getRegionWidth(), region.getRegionHeight()-position, false, false);
			batch.draw(region.getTexture(), getX(), getY(), getWidth(), getHeight()*(position+offset - region.getRegionHeight())/offset, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), position+offset - region.getRegionHeight(), false, false);

		}

		else{
			batch.draw(region.getTexture(),getX(), getY(), getWidth(), getHeight(), region.getRegionX(), region.getRegionY()+position, region.getRegionWidth(), offset, false, false);
		}
		
		
	}
}
