package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;


public class InstructionPanel extends Frame{
	public InstructionPanel(NetaGame ng){
		super(ng);
	}

	@Override
	public void content(NetaGame ng) {
		Skin skin = ng.getManager().getSkin();
		 Table content  =  new Table();
		 
		 
		 String[] items = {
				 "Спроси взрослого, который будет тебе помогать, внимательно ли он прочитал короткую записку для взрослых.",
				 "Нажми на кнопку (      ) и выбери ИГРОВОЕ ПОЛЕ",
				 "Нажми на кнопку (      ) и напиши (сам или с помощью взрослого) вверху игрового поля свое имя и где ты живёшь",
				 "Нажми на кнопку (      ), прочти (самостоятельно или со взрослым) стихи, сделай выбор и перенеси стих на ИГРОВОЕ ПОЛЕ.",
				 "Подготовь ИГРОВОЕ ПОЛЕ к игре. Для этого надо проложить извилистый маршрут со СТРАНЦИЯМИ.",
				 "СТАНЦИИ изготовь так: раздели стих на части (в рамочках). Каждая такая рамочка будет СТАНЦИЕЙ.",
				 "Размести СТАНЦИИ по всему полю так, чтобы первая СТАНЦИЯ была в левом углу поля, последняя - в правом углу, а остальные - между ними.",
				 "Нажми на кнопку (      ) и соедини СТАНЦИИ дорожкой из кружочков или квадратиков. Только не перепутай порядок СТАНЦИЙ!",
				 "Теперь сделай из КРУЖОЧКОВ, КВАДРАТИКОВ И ДРУГИХ ФИГУР рядом со стихами  картину - избрази героев стихотворения. Для этого нажми кнопку с КРУЖОЧКОМ и выбери нужные тебе фигуры, перетащи их на ИГРОВОЕ ПОЛЕ.",
				 "Нажми на кнопку (      ) и выбери цвета и раскрась свою картину.",
				 "Отлично! Игра почти готова! Осталось украсить цветами ИРОВОЕ ПОЛЕ.",
				 "Нажми на кнопку (      ), выбери фигуры и сложи из них цветы. Выбери буквы - лепстки, размести их на цветочках.",
				 "Отлично! Игра - шагалка готова! Ты молодчина!",
				 "ВСЁ! Теперь в шагалку можно играть - шагать по дорожкам, а по пути читать стихотворение - по кусочку на каждой станции.",
				 "Когда пройдете маршрут, выберите букву из тех, которые внизу на лепестках цветов. Выбирать букву можно с помощью считалочки. А считалочка - это то стихотворение, с которым вы играете. Букву, на которой окончилась считалка, разыщите во всех строчках стихов.",
				 "Точно так же ты можешь создать игры и с другими стихами, сохранить их, а потом поиграть с друзьями и знакомыми - ровесниками или взрослыми. Или нажать на кнопку (      ) прислать на страничку ИГРЫ ОТ НИКОЛЬ И Ко в фейсбуке."
			
		 };
		 
		
		 Label title = new Label("ИНСТРУКЦИЯ", skin, "instructionTitle");


		//content.setBackground(skin.getDrawable("debugTable"));
		content.defaults().align(Align.left).padBottom(10).padRight(5);

		for(String item : items){
			addItem(content, item, ng);
		}
		
		float w = 25, h = 20;
		Image monitor = new Image(ng.getManager().getAtlas().findRegion("monitor"));
		monitor.setBounds(146, 928, w, h);
		content.addActor(monitor);
		
		Image letter = new Image(ng.getManager().getAtlas().findRegion("letter"));
		letter.setBounds(146, 900 , w, h);
		content.addActor(letter);
		
		Image book = new Image(ng.getManager().getAtlas().findRegion("book"));
		book.setBounds(146, 838 , w, h);
		content.addActor(book);
		
		Image figures = new Image(ng.getManager().getAtlas().findRegion("figures"));
		figures.setBounds(146, 614 , w, h);
		content.addActor(figures);
		
		Image palette = new Image(ng.getManager().getAtlas().findRegion("palette"));
		palette.setBounds(146, 464 , w, h);
		content.addActor(palette);
		
		Image figures2 = new Image(ng.getManager().getAtlas().findRegion("figures"));
		figures2.setBounds(146, 376, w, h);
		content.addActor(figures2);
		
		Image save = new Image(ng.getManager().getAtlas().findRegion("save"));
		save.setBounds(146, 70, w, h);
		content.addActor(save);
		
		
		//content.addListener(new MetricListener());
		
		 Label end = new Label("Интересной Вам игры!\nВсего доброго!", ng.getManager().getSkin(), "instruction");
		 end.setWrap(true);
		content.add(end).colspan(2).width(width).row();
		
		
		content.pack();		
		
		Table container = new Table();
		ScrollPane pane = new ScrollPane(content, skin);		
		container.add(pane).width(width+50).height(380);
		container.pack();
		pane.setFadeScrollBars(false);
		
		Table wrapper = new Table();
		wrapper.defaults().align(Align.left);
		wrapper.add(title).padLeft(20).padBottom(10).row();
		wrapper.add(container);
		wrapper.pack();
		wrapper.setPosition(300, 50);

		addActor(wrapper);
	}
	
	static float width = 380;
	void addItem(Table content, String text, NetaGame ng){
		 Label i1 = new Label(text, ng.getManager().getSkin(), "instruction");
		 i1.setWrap(true);
		 content.add(l(ng.getManager())).align(Align.top).padTop(5);
		content.add(i1).width(width).row();
	}
	
	Actor l(TextureManager tm){
		//return new Label("* ", s, "marker");
		Texture region = tm.getCircle(new Color(120 /255f, 200 /255f, 250 /255f, 1), 5);
		Image img = new Image(region);
		img.setSize(5, 5);
		return img;
	}
	
	Actor li(Skin skin, String text){
		Group li = new Group();		
		Label marker = new Label("*", skin, "marker");		
		li.addActor(marker);
		
		Label data = new Label(text, skin, "instruction");
		data.setWrap(true);
		data.setPosition(marker.getX(), 0);
		li.addActor(data);
		li.setSize(marker.getWidth()+data.getWidth(), data.getHeight());
		
		return li;
	}
}
