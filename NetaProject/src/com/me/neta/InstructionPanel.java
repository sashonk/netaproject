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
import com.me.neta.events.InstructionCloseEvent;


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
				 "Нажми на кнопку (      ) и напиши (сам или с помощью взрослого) свое имя и где ты живёшь",
				 "Нажми на кнопку (      ), прочти (самостоятельно или со взрослым) стихи, сделай выбор и перенеси стих на ИГРОВОЕ ПОЛЕ.",
				 "Подготовь ИГРОВОЕ ПОЛЕ к игре. Для этого надо проложить извилистый маршрут между СТРАНЦИЯМИ. Нажми на кнопку (      ) и соедини СТАНЦИИ каменной  дорожкой из кружочков или квадратиков. Только не перепутай порядок СТАНЦИЙ!",
				 "Нажми на кнопку (      ) и выбери  одну полоску с буквами  для игры на этом поле.",
				 "Теперь сделай  из КРУЖОЧКОВ, КВАДРАТИКОВ И ДРУГИХ ФИГУР  рядом со стихами  картину – изобрази героев стихотворения. Для этого нажми кнопку (      ) и выбери нужные тебе фигуры, перетащи их на ИГРОВОЕ ПОЛЕ.",
				 "Нажми на кнопку (      ),  выбери цвета  и раскрась  свою картину. Отлично! Игра-шагалка готова! Ты молодчина!",
				 "ВСЁ! Теперь в шагалку   можно играть. Выбери себе на игровом поле героя,  вместе с которым ты  будешь  шагать по дорожкам, передвинь его на СТАРТ.",
				 "Нажми на кнопку СТАРТ. Игра началась! Прикоснись к кубику и передвинь своего героя по дорожке к станции  на столько камешков,  сколько точек выпало на кубике.",
				 "Когда придешь на станцию, прочитай стих и найди в нем буквы, такие же, как на цветочках",
				 "Прикоснись к кубику и передвинь своего героя по дорожке к следующей станции  на столько камешков,  сколько точек выпало на кубике. Если вы играете вдвоем, то шагать  надо по очереди-каждому со своим героем.",
				 "На каждой станции прочитай кусочек стихотворения (или попроси взрослого прочитать тебе) и найди в нем буквы, такие же, как на цветах.",
				 "Точно так же ты можешь создать игры и с другими стихами , а потом  поиграть с друзьями и знакомыми – ровесниками или взрослыми. Или нажать на кнопку (со стрелкой) прислать сохраненные игровые поля на страничку НИКОЛЬ И ЕЁ ДРУЗЬЯ в фейсбук."
			
		 };
		 
		
		 Label title = new Label("ИНСТРУКЦИЯ", skin, "instructionTitle");


		//content.setBackground(skin.getDrawable("debugTable"));
		content.defaults().align(Align.left).padBottom(10).padRight(5);

		for(String item : items){
			addItem(content, item, ng);
		}
		
		float w = 25, h = 20;
		Image monitor = new Image(ng.getManager().getAtlas().findRegion("monitor"));
		monitor.setBounds(146, 920, w, h);
		content.addActor(monitor);
		
		Image letter = new Image(ng.getManager().getAtlas().findRegion("letter"));
		letter.setBounds(146, 891 , w, h);
		content.addActor(letter);
		
		Image book = new Image(ng.getManager().getAtlas().findRegion("book"));
		book.setBounds(146, 846 , w, h);
		content.addActor(book);
		
		Image figures = new Image(ng.getManager().getAtlas().findRegion("figures"));
		figures.setBounds(250, 754 , w, h);
		content.addActor(figures);
		
		Image palette = new Image(ng.getManager().getAtlas().findRegion("ZVETOKOK_A"));
		palette.setBounds(146, 678 , w, h);
		content.addActor(palette);
		
		Image figures2 = new Image(ng.getManager().getAtlas().findRegion("figures"));
		figures2.setBounds(72, 587, w, h);
		content.addActor(figures2);
		
		Image save = new Image(ng.getManager().getAtlas().findRegion("palette"));
		save.setBounds(146, 543, w, h);
		content.addActor(save);
		
		
		content.addListener(new MetricListener());
		
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
		
		Actor decal = findActor("close");
		decal.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				fire(new InstructionCloseEvent());
				return true;
			}
		});
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
