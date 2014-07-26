package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ExitCloud extends Group{

	public ExitCloud(NetaGame ng, float x, float y, float w, float h) {
		this.setBounds(x, y, w, h);
		
		Image back = new Image(ng.getManager().getSkin().getDrawable("black"));
		Color transparent = new Color(1,1,1, 0.4f);
		back.setColor(transparent);
		back.setFillParent(true);
		addActor(back);
		
		
		Table cloud = new Table();
		TextureRegion tr = new TextureRegion(ng.getManager().getAtlas().findRegion("cloud"));		
		cloud.setBackground(new TextureRegionDrawable(tr));

		Label label = new Label("Точно хочешь выйти?", ng.getManager().getSkin());
		cloud.add(label).row();
		
		Table t = new Table();
		t.defaults().pad(5);
		TextButton yes = new TextButton("Да", ng.getManager().getSkin(), "button");
		yes.addListener(new ClickListener(){
			public void clicked (InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		t.add(yes);
		
		TextButton no = new TextButton("Нет", ng.getManager().getSkin(), "button");
		no.addListener(new ClickListener(){
			public void clicked (InputEvent event, float x, float y) {
				ExitCloud.this.remove();
			}
		});
		t.add(no);
		
		t.pack();
		cloud.add(t);
		
		cloud.pack();
		
		
		addActor(cloud);
		Util.center(cloud);
	}

}
