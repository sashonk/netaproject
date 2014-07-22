package com.me.neta.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.Hero;
import com.me.neta.MetricListener;
import com.me.neta.NetaGame;
import com.me.neta.Spider;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;
import com.me.neta.events.TextChanged;

public class SpiderWorld extends World{

	public SpiderWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
		setColor(new Color(186/255f, 179/255f, 213/255f, 1));
	}
	
	public String getAuthors(){
		return "Владимир Орлов";
	}
	public String getPassportColor(){
		return "spider";
	}
	
	 public Color letterColor(){
		 return Color.YELLOW;
	 }
	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("паучок", "title", new String[]{"red", "orange", "yellow", "green", "blue", "green"}, ng.getManager().getSkin());
	}

	@Override
	public String getTitle() {
		return "spider";
	}
	@Override
	public void populateBackground() {	
		Image PAUTINA = new Image(ng.getManager().getAtlas().findRegion("PAUTINA"));
		PAUTINA.setBounds(1024-304, 768-257,304, 257);
		addActor(PAUTINA);
	
		Image flower = new Image(ng.getManager().getAtlas().findRegion("ZVET3"));
		flower.setBounds(875, 340,57, 73);
		addActor(flower);
		
		
		//ng.getWorkspace().findActor("topButtons").addAction(Actions.moveBy(-350, 0, 0.5f, Interpolation.swingOut));
	}
	public void populateForeground() {
	
		Hero muha = new Hero(ng,"MUH");
		muha.setBounds(900, 160,53,31);
		muha.setZIndex(9);
		addActor(muha);
		
		
		Spider spider = new Spider(ng);
		spider.setPosition(100, 250);
		spider.setZIndex(10);
		addActor(spider);		
		spider.setName("hero");
	}

	@Override
	public boolean showPassport(){
		return false;
	}

	public void lyrics(){
		float dx = 55;
		float dy = 30;
		float scale = 0.25f;
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Паучок"));
			line1.add(label("приехал"));
			
			Table line2 = line();
			line2.add(label("На"));
			line2.add(label("базар:"));
			
			block1.add(line1).row();
			block1.add(line2).row();
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
			Table line3 = line();		
			line3.add(label("Мухам"));
			line3.add(label("паучок"));
			
			Table line4 =line();			
			line4.add(label("Привёз"));
			line4.add(label("товар."));
			
			block1.add(line3).row();
			block1.add(line4).row();
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
			line1.add(label("Он"));
			line1.add(label("его"));
			line1.add(label("развесил"));

			
			Table line2 = line();
			line2.add(label("на"));
			line2.add(label("осинке:"));
		
			
			block1.add(line1).row();
			block1.add(line2).row();
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
			line1.add(label("-Кто"));
			line1.add(label("желает"));
			
			Table line2 = line();
			line2.add(label("свежей"));
			line2.add(label("паутинки?"));

			
			block1.add(line1).row();
			block1.add(line2).row();
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
		charGroup11.add(Character.valueOf('а'));
		charGroup11.add(Character.valueOf('ч'));
		charGroup11.add(Character.valueOf('и'));
		variant1.put(Integer.valueOf(1),charGroup11);		
		
		List<Character> charGroup12 = new ArrayList<Character>(3);				
		charGroup12.add(Character.valueOf('М'));
		charGroup12.add(Character.valueOf('х'));
		charGroup12.add(Character.valueOf('к'));
		variant1.put(Integer.valueOf(2),charGroup12);	
		
		List<Character> charGroup13 = new ArrayList<Character>(3);				
		charGroup13.add(Character.valueOf('г'));
		charGroup13.add(Character.valueOf('р'));
		charGroup13.add(Character.valueOf('л'));
		variant1.put(Integer.valueOf(3),charGroup13);	
		
		List<Character> charGroup14 = new ArrayList<Character>(3);				
		charGroup14.add(Character.valueOf('ж'));
		charGroup14.add(Character.valueOf('т'));
		charGroup14.add(Character.valueOf('й'));
		variant1.put(Integer.valueOf(4),charGroup14);		
		letters.put(Integer.valueOf(1), variant1);
		
		
		Map<Integer, List<Character>> variant2 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup21 = new ArrayList<Character>(3);				
		charGroup21.add(Character.valueOf('е'));
		charGroup21.add(Character.valueOf('б'));
		charGroup21.add(Character.valueOf('а'));
		variant2.put(Integer.valueOf(1),charGroup21);		
		
		List<Character> charGroup22 = new ArrayList<Character>(3);				
		charGroup22.add(Character.valueOf('в'));
		charGroup22.add(Character.valueOf('ё'));
		charGroup22.add(Character.valueOf('о'));
		variant2.put(Integer.valueOf(2),charGroup22);	
		
		List<Character> charGroup23 = new ArrayList<Character>(3);				
		charGroup23.add(Character.valueOf('н'));
		charGroup23.add(Character.valueOf('с'));
		charGroup23.add(Character.valueOf('л'));
		variant2.put(Integer.valueOf(3),charGroup23);	
		
		List<Character> charGroup24 = new ArrayList<Character>(3);				
		charGroup24.add(Character.valueOf('п'));
		charGroup24.add(Character.valueOf('у'));
		charGroup24.add(Character.valueOf('т'));
		variant2.put(Integer.valueOf(4),charGroup24);		
		letters.put(Integer.valueOf(2), variant2);		

	}

	@Override
	public String getOrderLabelStyleDef() {
		// TODO Auto-generated method stub
		return "yellow";
	}
}
