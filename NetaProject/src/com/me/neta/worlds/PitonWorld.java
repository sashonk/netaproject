package com.me.neta.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

	
	@Override
	public void populateLetters(){
		Map<Integer, List<Character>> variant1 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup11 = new ArrayList<Character>(3);				
		charGroup11.add(Character.valueOf('ж'));
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
		charGroup14.add(Character.valueOf('с'));
		charGroup14.add(Character.valueOf('ы'));
		charGroup14.add(Character.valueOf('й'));
		variant1.put(Integer.valueOf(4),charGroup14);		
		letters.put(Integer.valueOf(1), variant1);
		
		
		Map<Integer, List<Character>> variant2 = new HashMap<Integer, List<Character>>();
		List<Character> charGroup21 = new ArrayList<Character>(3);				
		charGroup21.add(Character.valueOf('б'));
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

	}

}
