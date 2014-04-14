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
import com.me.neta.Piton;
import com.me.neta.TextureManager;
import com.me.neta.Util;
import com.me.neta.World;

public class PitonWorld extends World{

	public PitonWorld(NetaGame ng, float width, float height) {
		super(ng, width, height);
		setColor(new Color(182/255f, 221/255f, 200/255f, 1));
	}
	
	public String getPassportColor(){
		return "piton";
	}
	
	public String getAuthors(){
		return "Владимир Орлов";
	}
	
	@Override
	public Actor createTitle() {
		return Util.multiColorLabel("питон", "title", new String[]{"red", "orange", "yellow", "green", "blue"}, ng.getManager().getSkin());
	}


	@Override
	public String getTitle() {
		return "piton";
	}

	@Override
	public void populateBackground() {
		Image flower1 = new Image(ng.getManager().getAtlas().findRegion("ZVET4"));
		flower1.setBounds(200, 115,57,57);
		addActor(flower1);
/*				
		Image bird = new Image(TextureManager.get().getAtlas().findRegion("POPUGAI"));
		bird.setBounds(10, 50,43,30);*/
	}
	
	public void populateForeground(){ 	
		Piton piton = new Piton(ng);
		piton.setPosition(460, 130);
		piton.setZIndex(10);
		addActor(piton);
		
		Hero bird = new Hero(ng,"POPUGAI");
		bird.setBounds(200+10, 115+50,43,30);
		bird.setZIndex(9);
		addActor(bird);		
	}


	public void lyrics(){
		float dx = 70;
		float dy = 33;
		float scale = 0.22f;
		{
			Table block1 = block();			
			
			Table line1 = line();
			line1.add(label("Плачет"));

			Table line2 = line();
			line2.add(label("маленький"));
			line2.add(label("питон:"));
			
			Table line3 = line();
			line3.add(label("Сам"));
			line3.add(label("себя"));
			
			Table line4 = line();
			line4.add(label("запутал"));
			line4.add(label("он."));
			
			block1.add(line1).row();
			block1.add(line2).row();
			block1.add(line3).row();
			block1.add(line4).row();
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
			line1.add(label("Сам"));
			line1.add(label("себя"));
			
			Table line2 =line();			
			line2.add(label("переползал"));
			
			Table line3 = line();		
			line3.add(label("И"));
			line3.add(label("себя"));
			line3.add(label("узлом"));
			
			Table line4 =line();			
			line4.add(label("связал."));
		
			block1.add(line1).row();
			block1.add(line2).row();
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
			line1.add(label("Кто"));
			line1.add(label("теперь"));


			
			Table line2 = line();
			line2.add(label("ему"));
			line2.add(label("поможет:"));
			
			
			Table line3 = line();
			line3.add(label("Он"));
			line3.add(label("себя"));
			line3.add(label("найти"));

			
			Table line4 = line();
			line4.add(label("не"));
			line4.add(label("может!"));
		
			
			block1.add(line1).row();
			block1.add(line2).row();
			block1.add(line3).row();
			block1.add(line4).row();
			block1.pack();
			
			Group wrap = new Group();
			wrap.addActor(block1);
			wrap.setScale(scale);
			Group cg1 = (Group) findActor("cg3");
			Actor dom1 = cg1.findActor("dom3");
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
		charGroup11.add(Character.valueOf('е'));
		variant1.put(Integer.valueOf(1),charGroup11);		
		
		List<Character> charGroup12 = new ArrayList<Character>(3);				
		charGroup12.add(Character.valueOf('м'));
		charGroup12.add(Character.valueOf('б'));
		charGroup12.add(Character.valueOf('п'));
		variant1.put(Integer.valueOf(2),charGroup12);	
		
		List<Character> charGroup13 = new ArrayList<Character>(3);				
		charGroup13.add(Character.valueOf('у'));
		charGroup13.add(Character.valueOf('ж'));
		charGroup13.add(Character.valueOf('с'));
		variant1.put(Integer.valueOf(3),charGroup13);			
		letters.put(Integer.valueOf(1), variant1);
		
		
		Map<Integer, List<Character>> variant2 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup21 = new ArrayList<Character>(3);				
		charGroup21.add(Character.valueOf('к'));
		charGroup21.add(Character.valueOf('и'));
		charGroup21.add(Character.valueOf('з'));
		variant2.put(Integer.valueOf(1),charGroup21);		
		
		List<Character> charGroup22 = new ArrayList<Character>(3);				
		charGroup22.add(Character.valueOf('р'));
		charGroup22.add(Character.valueOf('о'));
		charGroup22.add(Character.valueOf('л'));
		variant2.put(Integer.valueOf(2),charGroup22);	
		
		List<Character> charGroup23 = new ArrayList<Character>(3);				
		charGroup23.add(Character.valueOf('н'));
		charGroup23.add(Character.valueOf('й'));
		charGroup23.add(Character.valueOf('т'));
		variant2.put(Integer.valueOf(3),charGroup23);	
		
	
		letters.put(Integer.valueOf(2), variant2);		

	}

}
