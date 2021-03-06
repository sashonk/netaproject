package com.me.neta.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Ant;
import com.me.neta.CellarGroup;
import com.me.neta.CellarGroup.LogicFlower;
import com.me.neta.CellarGroup.LogicLabel;
import com.me.neta.Barrier;
import com.me.neta.Hero;
import com.me.neta.MetricListener;
import com.me.neta.NetaGame;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;
import com.me.neta.tools.StartButton;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class AntWorld extends World{

	public AntWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
		// TODO Auto-generated constructor stub
		setColor(new Color(.72f, .86f, .48f, 1));
	}
	
	public String getPassportColor(){
		return "ant";
	}
	
	public String getAuthors(){
		return "Вадим Левин и Рената Муха";
	}
	
	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("муравей", "title", new String[]{"red", "orange", "yellow", "green", "blue", "green", "orange"}, ng.getManager().getSkin());
	}


	@Override
	public String getTitle() {
		return "ant";
	}

	@Override
	public void populateBackground() {
		Image flower1 = new Image(ng.getManager().getAtlas().findRegion("ZVET3"));
		flower1.setBounds(162, 80, 57,73);
		addActor(flower1);
		
		Image flower2 = new Image(ng.getManager().getAtlas().findRegion("ZVET4"));
		flower2.setBounds(900, 80,57,57);
		addActor(flower2);	
	}
		
	public void populateForeground() {
		Hero ant2 = new Hero(ng, "ant2");
		ant2.setBounds(900, 260, 55, 115);
		ant2.setZIndex(9);
		addActor(ant2);
		
		Ant ant = new Ant(ng);
		ant.setPosition(50, 250);
		ant.setZIndex(10);
		addActor(ant);		
		ant.setName("hero");

	}
	

	
	public void lyrics(){
		float scale = 0.11f;
		int dx = 55;
		int dy = 32;
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Жил"));
			line1.add(label("на"));
			line1.add(label("свете"));
			
			//Table line2 = line();
			line1.add(label("муравей -"));
			
			Table line2 = line();		
			line2.add(label("Без"));
			line2.add(label("ресниц"));
			
			//Table line2 =line();			
			line2.add(label("и"));
			line2.add(label("без"));
			line2.add(label("бровей."));
			
			block1.add(line1).row();
			//block1.add(line2).row();
			block1.add(line2).row();
			//block1.add(line4).row();
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
			line1.add(label("Он"));
			line1.add(label("терпеть"));
			
			//Table line2 = line();
			line1.add(label("не"));
			line1.add(label("мог"));
			line1.add(label("девиц -"));
			
			Table line2 = line();		
			line2.add(label("Без"));
			line2.add(label("бровей"));
			
			//Table line4 =line();			
			line2.add(label("и"));
			line2.add(label("без"));
			line2.add(label("ресниц."));
			
			block1.add(line1).row();
			block1.add(line2).row();
			//block1.add(line3).row();
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
			line1.add(label("Потому"));
			line1.add(label("у"));
			
			//Table line2 = line();
			line1.add(label("муравья"));
			
			Table line2 = line();		
			line2.add(label("Были"));
			line2.add(label("только"));
			
			//Table line4 =line();			
			line2.add(label("сыновья:"));
			
			block1.add(line1).row();
			block1.add(line2).row();
			//block1.add(line3).row();
			//block1.add(line4).row();
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg3");
			Actor dom1 = cg1.findActor("dom3");
			wrap.setPosition(dom1.getX()+dx, dom1.getY()+dy);		
			cg1.addActor(wrap);
		}
		
		
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Сорок"));
			line1.add(label("восемь"));
			
			//Table line2 = line();
			line1.add(label("сыновей -"));
			
			Table line2 = line();		
			line2.add(label("Без"));
			line2.add(label("ресниц"));
			
			//Table line4 =line();			
			line2.add(label("и"));
			line2.add(label("без"));
			line2.add(label("бровей."));
			
			block1.add(line1).row();
			block1.add(line2).row();
			//block1.add(line3).row();
			//block1.add(line4).row();
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
		charGroup11.add(Character.valueOf('Ж'));
		charGroup11.add(Character.valueOf('а'));
		charGroup11.add(Character.valueOf('е'));
		variant1.put(Integer.valueOf(1),charGroup11);		
		
		List<Character> charGroup12 = new ArrayList<Character>(3);				
		charGroup12.add(Character.valueOf('т'));
		charGroup12.add(Character.valueOf('п'));
		charGroup12.add(Character.valueOf('м'));
		variant1.put(Integer.valueOf(2),charGroup12);	
		
		List<Character> charGroup13 = new ArrayList<Character>(3);				
		charGroup13.add(Character.valueOf('о'));
		charGroup13.add(Character.valueOf('у'));
		charGroup13.add(Character.valueOf('я'));
		variant1.put(Integer.valueOf(3),charGroup13);	
		
		List<Character> charGroup14 = new ArrayList<Character>(3);				
		charGroup14.add(Character.valueOf('С'));
		charGroup14.add(Character.valueOf('ы'));
		charGroup14.add(Character.valueOf('й'));
		variant1.put(Integer.valueOf(4),charGroup14);		
		letters.put(Integer.valueOf(1), variant1);
		
		
		Map<Integer, List<Character>> variant2 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup21 = new ArrayList<Character>(3);				
		charGroup21.add(Character.valueOf('Б'));
		charGroup21.add(Character.valueOf('з'));
		charGroup21.add(Character.valueOf('в'));
		variant2.put(Integer.valueOf(1),charGroup21);		
		
		List<Character> charGroup22 = new ArrayList<Character>(3);				
		charGroup22.add(Character.valueOf('г'));
		charGroup22.add(Character.valueOf('д'));
		charGroup22.add(Character.valueOf('н'));
		variant2.put(Integer.valueOf(2),charGroup22);	
		
		List<Character> charGroup23 = new ArrayList<Character>(3);				
		charGroup23.add(Character.valueOf('л'));
		charGroup23.add(Character.valueOf('ь'));
		charGroup23.add(Character.valueOf('к'));
		variant2.put(Integer.valueOf(3),charGroup23);	
		
		List<Character> charGroup24 = new ArrayList<Character>(3);				
		charGroup24.add(Character.valueOf('р'));
		charGroup24.add(Character.valueOf('и'));
		charGroup24.add(Character.valueOf('ц'));
		variant2.put(Integer.valueOf(4),charGroup24);		
		letters.put(Integer.valueOf(2), variant2);		
//
	}
	
	

	//@Override
	public String getLyricsAsString() {
	//	String text = "Жил на свете муравей\nБез ресниц и без бровей\n Он терпеть не мог девиц\nБез"
		return null;
	}

	@Override
	public String getOrderLabelStyleDef() {
		// TODO Auto-generated method stub
		return "violet";
	}


	
	
}
