package com.me.neta;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class AuthorsPanel extends Frame{

	public AuthorsPanel(NetaGame ng) {
		super(ng);
	}

	public void content(NetaGame ng){
		Skin skin = ng.getManager().getSkin();
		Table content = new Table();
		content.defaults().align(Align.left).width(600).padBottom(10);
		
		String[] items = {
			"Вадим Левин - детский поэт, доктор психологии, лауреат литературной премии имени Корнея Чуковского. Читает стихи в этой игре"	,
			"Наталья Голомбек - разработчик обучающих игр для детей, руководитель ЛИтературной СТудии ЛИСТочек, учитель с 30-летним стажем",
			"Денис Голомбек - художник, дизайнер, детский психолог",
			"Кисоржевский Александр - программист",
			"Ксения Трещёва (9 лет) - озвучка букв"
								
		};
		
		content.add(new Label("ОБ АВТОРАХ", skin, "instructionTitle")).padLeft(20).row();
		for(String item : items){
			Label label = new Label(item, skin , "instruction");
			label.setWrap(true);
			content.add(label).row();
		}
		content.pack();
		content.setPosition(310, 280);
		addActor(content);
		//Label title = new Label("ОБ АВТОРАХ", skin, "instructionTitle");
		
	}

}
 