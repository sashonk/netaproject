package com.me.neta;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class ShopPanel extends Window{
	NetaGame ng;

	public ShopPanel(final NetaGame ng) {
		super("", ng.getManager().getSkin());
		this.ng = ng;
		this.setClip(false);
		

		Map<String, String> mm = new LinkedHashMap<String, String>();
		mm.put("shopZhilNaSvete", null);
		mm.put("shopNaZabore", "https://play.google.com/store/apps/details?id=com.yablo4ko_demo.nkgames");
	//	mm.put("shopZhilaLoshad", "https://play.google.com/store/apps/details?id=com.yablo4ko_demo.nkgames");
	//	mm.put("shopChudoRybka", "https://play.google.com/store/apps/details?id=com.yablo4ko_demo.nkgames");
	//	mm.put("shopKoshkiMyshki", "https://play.google.com/store/apps/details?id=com.yablo4ko_demo.nkgames");
	//	mm.put("shopUzhiEzhi", "https://play.google.com/store/apps/details?id=com.yablo4ko_demo.nkgames");


		this.defaults().padRight(20);
		for(String name : mm.keySet()){
			final String url = mm.get(name);
			Image item1 = new Image(ng.getManager().getAtlas().findRegion(name));
			item1.setSize(93, 93);
			this.add(item1);
			
			if(url!=null){
				item1.addListener(new InputListener(){
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						ng.getNative().openWebPage(url);
						return true;
					}
				});
			}
		}
		this.pack();
		

		
		TextureRegion tr =new TextureRegion(ng.getManager().getAtlas().findRegion("frame-tail")) ;
		tr.flip(true, false);
		Image tail = new Image(tr);
		tail.setSize(52, 65);
		tail.setPosition(360-200, -51);
		addActor(tail);
	}

}
