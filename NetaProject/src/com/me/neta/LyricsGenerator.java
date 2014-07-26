package com.me.neta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class LyricsGenerator {
	
	public static List<Actor> getLyrics(int id){
	
		
		List<Actor> regions = new ArrayList<Actor>(5);
		for(int i = 1; i <= LyricsCounter.getBlockCount(id) ; i++){			
				//LyricsBlock block = new LyricsBlock(id, i);				
				///regions.add(block);							
		}
		
		return regions;
	}
	
	
	static final class LyricsCounter{
		static int getBlockCount(int i){
			Integer key = Integer.valueOf(i);
			Integer value = values.get(key);
			
			return value!=null ? value.intValue() : 0;
		}
		
		static Map<Integer, Integer> values = new HashMap<Integer, Integer>();
		
		static {
			values.put(Integer.valueOf(1), 4);
			values.put(Integer.valueOf(2), 4);
			values.put(Integer.valueOf(3), 5);
			values.put(Integer.valueOf(4), 4);
			
		}
	}
}
