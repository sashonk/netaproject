package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.me.neta.Context.ContextProperty;

public class LyricsFrame extends Group{
	public LyricsFrame(final NetaGame ng, String lyrics){


		 Skin skin = ng.getManager().getSkin();
		 Image panelImg = new Image(skin.getPatch("frame"));
		 addActor(panelImg);
		 
		 
		 Table table = new Table();
		 Image fillImg = new Image(skin.getPatch("fill"));
		 table.addActor(fillImg);
		 
		 TextButton close = new TextButton(lyrics, skin);
		 table.addActor(close);
		 table.pack();
		 
		 addActor(table);
	 

	}
}
