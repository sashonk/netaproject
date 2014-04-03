package com.me.neta.worlds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.neta.Ant;
import com.me.neta.CellarGroup;
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
	protected
	void createCellars(){
		Actor zactor = findActor("zactor");
		
		
		
		Image cellar1 = new Image(ng.getManager().getAtlas().findRegion("DOM1"));
		cellar1.setBounds(300, 300, 167, 137);
		cellar1.addListener(new MetricListener(){});
				
		addActorBefore(zactor, cellar1);
			
		StartButton start = new StartButton(ng);
		start.setPosition(100, 100);
		addActor(start);
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
		ant2.setBounds(800, 260, 55, 115);
		ant2.setZIndex(9);
		addActor(ant2);
		
		Ant ant = new Ant(ng);
		ant.setPosition(100, 250);
		ant.setZIndex(10);
		addActor(ant);		
	
		

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
