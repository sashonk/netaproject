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
		zebra.setBounds(420, 170, 93, 72);
		zebra.setZIndex(9);
		addActor(zebra);
	//420 170
				
		Tiger tiger = new Tiger(ng);
		tiger.setPosition(120, 320);
		tiger.setZIndex(10);
		addActor(tiger);		
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
