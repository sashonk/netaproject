package com.me.neta.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.neta.Hero;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Tiger;
import com.me.neta.Util;
import com.me.neta.World;

public class TigerWorld extends World{

	public TigerWorld(NetaGame ng, float width, float height) {
		super(ng,width, height);
		setColor(new Color(255/255f, 250/255f, 156/255f, 1));
	}
	
	public String getAuthors(){
		return "Владимир Орлов";
	}
	
	public String getPassportColor(){
		return "tiger";
	}

	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("тигр", "title", new String[]{"red", "orange", "yellow", "green"}, ng.getManager().getSkin());
	}

	 public Color letterColor(){
		 return Color.BLUE;
	 }

	@Override
	public String getTitle() {
		return "tiger";
	}
	@Override
	public void populateBackground() {
		Image flower1 = new Image(ng.getManager().getAtlas().findRegion("ZVET5"));
		flower1.setBounds(140, 135,78,97);
		addActor(flower1);
		
		
		Image butterfly = new Image(ng.getManager().getAtlas().findRegion("PARPAR"));
		butterfly.setBounds(690, 768-200,38,38);
		addActor(butterfly);
		
		Image flower2 = new Image(ng.getManager().getAtlas().findRegion("ZVET6"));
		flower2.setBounds(820,150,74,48);
		addActor(flower2);
		
		
	}
	public void populateForeground(){
		Hero zebra = new Hero(ng,"ZEBRA");
		zebra.setBounds(480, 150, 93, 72);
		zebra.setZIndex(9);
		addActor(zebra);
		zebra.setName("hero");
	//420 170
				
		Tiger tiger = new Tiger(ng);
		tiger.setPosition(120, 320);
		tiger.setZIndex(10);
		addActor(tiger);	
		
	}

	
	public void lyrics(){
		float dx = 30;
		float dy = 40;
		float scale = 0.16f;
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Тигры"));
			line1.add(label("зебрам"));

			
			//Table line2 = line();
			line1.add(label("говорили:"));

			
			Table line3 = line();
			line3.add(label("Мы"));
			line3.add(label("один"));

			
			//Table line4 = line();
			line3.add(label("секрет"));
			line3.add(label("открыли."));

			
			block1.add(line1).row();
		///	block1.add(line2).row();
			block1.add(line3).row();
		//	block1.add(line4).row();			
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg1");
			Actor dom1 = cg1.findActor("dom1");
			wrap.setPosition(dom1.getX()+dx, dom1.getY()+dy);		
			cg1.addActor(wrap);
		}
		
		{
			Table block1 = block();						
			Table line1 = line();		
			line1.add(label("Оказалось,"));
			
			//Table line2 =line();			
			line1.add(label("дорогие,"));
			
			Table line3 = line();		
			line3.add(label("Вы"));
			line3.add(label("нам"));
			
			//Table line4 =line();			
			line3.add(label("родичи"));
			line3.add(label("прямые."));

			
			block1.add(line1).row();
			//block1.add(line2).row();
			block1.add(line3).row();
			//block1.add(line4).row();
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg2");
			Actor dom1 = cg1.findActor("dom2");
			wrap.setPosition(dom1.getX()+dx, dom1.getY()+dy);		
			cg1.addActor(wrap);
		}
		
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Гляньте,"));
			line1.add(label("есть"));
			
			//Table line2 = line();
			line1.add(label("у"));
			line1.add(label("нас"));
			line1.add(label("полоски!"));
						
			Table line3 = line();
			line3.add(label("- Есть"));
			line3.add(label("они"));
			line3.add(label("и"));
			
			//Table line4= line();
			line3.add(label("на"));
			line3.add(label("матросске!"));
		
			
			block1.add(line1).row();
			//block1.add(line2).row();
			block1.add(line3).row();
		//	block1.add(line4).row();
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg3");
			Actor dom1 = cg1.findActor("dom3");
			wrap.setPosition(dom1.getX()+dx-5, dom1.getY()+dy);		
			cg1.addActor(wrap);
		}
		
		
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Но"));
			line1.add(label("ни"));
			line1.add(label("разу"));
			
			//Table line2 = line();
			line1.add(label("не"));
			line1.add(label("бывала"));			
			
			Table line3 = line();
			line3.add(label("Зебра"));
			line3.add(label("тёткой"));
			
			//Table line4 = line();
			line3.add(label("адмирала!"));
			
			block1.add(line1).row();
		//	block1.add(line2).row();
			block1.add(line3).row();
		//	block1.add(line4).row();
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg4");
			Actor dom1 = cg1.findActor("dom4");
			wrap.setPosition(dom1.getX()+dx, dom1.getY()+dy);		
			cg1.addActor(wrap);
		}
	}
	
	@Override
	public void populateLetters(){
		Map<Integer, List<Character>> variant1 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup11 = new ArrayList<Character>(3);				
		charGroup11.add(Character.valueOf('б'));
		charGroup11.add(Character.valueOf('а'));
		charGroup11.add(Character.valueOf('в'));
		variant1.put(Integer.valueOf(1),charGroup11);		
		
		List<Character> charGroup12 = new ArrayList<Character>(3);				
		charGroup12.add(Character.valueOf('з'));
		charGroup12.add(Character.valueOf('л'));
		charGroup12.add(Character.valueOf('и'));
		variant1.put(Integer.valueOf(2),charGroup12);	
		
		List<Character> charGroup13 = new ArrayList<Character>(3);				
		charGroup13.add(Character.valueOf('я'));
		charGroup13.add(Character.valueOf('т'));
		charGroup13.add(Character.valueOf('ь'));
		variant1.put(Integer.valueOf(3),charGroup13);	
		
		List<Character> charGroup14 = new ArrayList<Character>(3);				
		charGroup14.add(Character.valueOf('в'));
		charGroup14.add(Character.valueOf('м'));
		charGroup14.add(Character.valueOf('р'));
		variant1.put(Integer.valueOf(4),charGroup14);		
		letters.put(Integer.valueOf(1), variant1);
		
		
		Map<Integer, List<Character>> variant2 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup21 = new ArrayList<Character>(3);				
		charGroup21.add(Character.valueOf('г'));
		charGroup21.add(Character.valueOf('д'));
		charGroup21.add(Character.valueOf('е'));
		variant2.put(Integer.valueOf(1),charGroup21);		
		
		List<Character> charGroup22 = new ArrayList<Character>(3);				
		charGroup22.add(Character.valueOf('н'));
		charGroup22.add(Character.valueOf('и'));
		charGroup22.add(Character.valueOf('к'));
		variant2.put(Integer.valueOf(2),charGroup22);	
		
		List<Character> charGroup23 = new ArrayList<Character>(3);				
		charGroup23.add(Character.valueOf('п'));
		charGroup23.add(Character.valueOf('о'));
		charGroup23.add(Character.valueOf('с'));
		variant2.put(Integer.valueOf(3),charGroup23);	
		
		List<Character> charGroup24 = new ArrayList<Character>(3);				
		charGroup24.add(Character.valueOf('р'));
		charGroup24.add(Character.valueOf('ё'));
		charGroup24.add(Character.valueOf('м'));
		variant2.put(Integer.valueOf(4),charGroup24);		
		letters.put(Integer.valueOf(2), variant2);		

	}

	
	@Override
	public String getOrderLabelStyleDef() {
		// TODO Auto-generated method stub
		return "red";
	}
}
