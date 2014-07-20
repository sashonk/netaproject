package com.me.neta;


import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.CreateCellarsEvent;


public class LyricsPanel2 extends Window{
	
	TextureRegion treg ;
	Table t;
	static float mul = .4f;	
	Drawable d;
	
	LyricsPanel2(final NetaGame ng, final float width,final float height){
		super("", ng.getManager().getSkin());
		Skin skin =  ng.getManager().getSkin();
		this.setClip(false);
		defaults().minHeight(275);

		
		Map<Integer, Actor> mm = new HashMap<Integer, Actor>();
		String titleStyle = "lyrics_title";
		int padBottomT = 20;
		int pad = 3;
		//ant
		{
			String style = "lyrics_ant";
			Table t = new Table();
			t.defaults().align(Align.left).pad(pad);
			
			Label title = new  Label("Вадим Левин и Рената Муха\nМуравей", skin, titleStyle);
			Label l1 = new Label("Жил на свете муравей -\nБез ресниц и без бровей", skin, style);
			Label l2 = new Label("Он терпеть не мог девиц -\nБез бровей и без ресниц.", skin, style);
			Label l3 = new Label("Потому у муравья\nБыли только сыновья", skin, style);
			Label l4 = new Label("Сорок восемь сыновей -\nБез ресниц и без бровей", skin,style);
			
			t.add(title).align(Align.center).padBottom(padBottomT).row();
			t.add(l1).row();
			t.add(l2).row();
			t.add(l3).row();
			t.add(l4).row();
			t.pack();			
			
			t.setBackground(skin.getDrawable("unchecked"));
			
			add(t);
			mm.put(Integer.valueOf(1), t);
		}
		
		//spider
		{
			String style = "lyrics_spider";
			Table t = new Table();
			t.defaults().align(Align.left).pad(pad);
			
			
			Label title = new  Label("Владимир Орлов\nПаучок", skin, titleStyle);
			Label l1 = new Label("Паучок приезал\nНа базар:", skin, style);
			Label l2 = new Label("Мухам паучок\nПривёз товар.", skin, style);
			Label l3 = new Label("Он его развесил\nНа осинке:", skin, style);
			Label l4 = new Label("-Кто желает\nСвежей паутинки?", skin,style);
			
			t.add(title).padBottom(padBottomT).row();
			t.add(l1).row();
			t.add(l2).row();
			t.add(l3).row();
			t.add(l4).row();
			t.pack();			
			
			t.setBackground(skin.getDrawable("unchecked"));
			
			add(t);
			mm.put(Integer.valueOf(2), t);

		}
		
		
		//piton
		{
			String style = "lyrics_piton";
			Table t = new Table();
			t.defaults().align(Align.left).pad(pad);
			
			
			Label title = new  Label("Владимир Орлов\nПитон", skin, titleStyle);
			Label l1 = new Label("Плачет маленький питон:\nСам себя запутал он.", skin, style);
			Label l2 = new Label("Сам себя переползал\nИ себя узлом связал.", skin, style);
			//Label l3 = new Label("", skin, style);
			//Label l4 = new Label("", skin,style);
			Label l5 = new Label("Кто теперь ему поможет:\nОн себя найти не может!", skin,style);

			
			t.add(title).align(Align.center).padBottom(padBottomT).row();
			t.add(l1).row();
			t.add(l2).row();
		//	t.add(l3).row();
			//t.add(l4).row();
			t.add(l5).row();

			
			t.pack();			
			
			t.setBackground(skin.getDrawable("unchecked"));
			
			add(t);
			mm.put(Integer.valueOf(3), t);

		}
		
		
		//tiger
		{
			String style = "lyrics_tiger";
			Table t = new Table();
			t.defaults().align(Align.left).pad(pad);
			
			Label title = new  Label("Владимир Орлов\nТигр", skin, titleStyle);
			Label l1 = new Label("ТИГРЫ зебрам говорили:\n-Мы один секрет открыли!", skin, style);
			Label l2 = new Label("Оказалось, дорогие,\nВы нам родичи прямые!", skin, style);
			Label l3 = new Label("Гляньте, есть у нас полоски!\n-Есть они и на матроске!", skin, style);
			Label l4 = new Label("Но ни разу не бывала\nЗебра тёткой адмирала", skin,style);
			
			t.add(title).align(Align.center).padBottom(padBottomT).row();
			t.add(l1).row();
			t.add(l2).row();
			t.add(l3).row();
			t.add(l4).row();
			t.pack();			
			
			t.setBackground(skin.getDrawable("unchecked"));
			
			add(t);
			mm.put(Integer.valueOf(4), t);

		}
		
		for(final Integer key : mm.keySet()){
			Actor table = mm.get(key);
			table.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					if(ng.getContext().getProperty(ContextProperty.CELLARS)==null){
						CreateCellarsEvent ev = new CreateCellarsEvent(key);					
						LyricsPanel2.this.fire(ev);
					}
					
					return true;
				}
			});
		}
		
		TextureRegion tailTr = new TextureRegion( ng.getManager().getAtlas().findRegion("frame-tail"));
		Image tail = new Image(tailTr);
		tail.setSize(52, 65);
		tail.setPosition(225, -51);		
		addActor(tail);
		
	
		pack();
	}
		


}
