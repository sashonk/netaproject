package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.AdultsCloseEvent;
import com.me.neta.events.InstructionCloseEvent;
import com.me.neta.events.QuestionEvent;

public class AdultsPanel extends Frame{
	
	NinePatch panel;
	NinePatch fill;
	public AdultsPanel(final NetaGame ng){
		super(ng);
		



	}
	
	public void content(NetaGame ng){
		 Skin skin = ng.getManager().getSkin();
		 Table content  =  new Table();
		 Label title = new Label("ЗАПИСКА ДЛЯ ВЗРОСЛЫХ", skin, "instructionTitle");
		 Label description = new Label("Письмо взрослым от автора проекта.", skin, "instruction");

		 
		 Label question = new Label("Читать научились все, а много ли читателей?", skin, "instruction2");
		 question.setAlignment(Align.left);
		 
		 String lyricsString = "Сказать ли,\n" +
		 		"О чем они втайне мечтают - \n" +
		 		"Те, чьи книги охотно читают?\n" +
		 		"Мечтают, чтоб мы научились читать...\n" +
		 		"Ах, об этом\n" +
		 		"Можно только мечтать\n" +
		 		"Борис Заходер \"Мечты и звуки\"\n";
		 Label lyrics = new Label(lyricsString, skin, "instruction");
		 
		 
		 FileHandle infoFile =Gdx.files.internal("data/forAdults.txt");
		 String infoText = infoFile.readString();	
		
		 Label info = new Label(infoText, skin, "instruction");
		// info.setAlignment(Align.right, Align.center);
		// info.setWidth(300);
		 info.setWrap(true);
		 
		ScrollPane pane = new ScrollPane(info, skin);
		pane.setFadeScrollBars(false);
		//pane.set
		//pane.setFillParent(true);
		//pane.setPosition(getWidth()/3,50);
	
		content.defaults().align(Align.left).padBottom(10);
		content.add(title).padLeft(10).row();		
		content.add(description).row();
		content.add(question).row();
		content.add(lyrics).row();
		content.add(pane).width(380).height(180);
		content.pack();
		content.setPosition(getWidth()/3, 55);
		
		addActor(content);
		
		Actor decal = findActor("close");
		decal.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fire(new AdultsCloseEvent());
				return true;
			}
		});
	}
}
