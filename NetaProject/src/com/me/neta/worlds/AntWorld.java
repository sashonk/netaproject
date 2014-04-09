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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.me.neta.Ant;
import com.me.neta.CellarGroup;
import com.me.neta.CellarGroup.LogicFlower;
import com.me.neta.CellarGroup.LogicLabel;
import com.me.neta.Barrier;
import com.me.neta.CoorListener;
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
		
		
		/////////// G1////////////
		final CellarGroup group1 = new CellarGroup(ng);
		group1.setOrigin(100, -25);
		group1.setPosition(190, 303);
		
		group1.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				group1.addAction(Util.zoomTo(0.25f, 0, null));
				return false;
			}
		});
		
		Table textTable = new Table();
		LogicLabel label11 = new LogicLabel(ng, "Жил");
		LogicLabel label12 = new LogicLabel(ng, "на");
		LogicLabel label13 = new LogicLabel(ng, "свете");
		LogicLabel label14 = new LogicLabel(ng, "муравей");
		LogicLabel label15 = new LogicLabel(ng, "Без");
		LogicLabel label16 = new LogicLabel(ng, "ресниц");
		LogicLabel label17 = new LogicLabel(ng, "и");
		LogicLabel label18 = new LogicLabel(ng, "без");
		LogicLabel label19 = new LogicLabel(ng, "бровей");
		
		LogicFlower flower11 = new LogicFlower(ng);
		flower11.setPosition(3, -60);
		group1.addFlower(flower11);
		
		LogicFlower flower12 = new LogicFlower(ng);
		flower12.setPosition(70, -60);
		group1.addFlower(flower12);
		
		LogicFlower flower13 = new LogicFlower(ng);
		flower13.setPosition(35, -115);
		group1.addFlower(flower13);
				
		final Barrier barrier1 = new Barrier(ng);
		barrier1.addListener(new CoorListener());
		group1.addActor(barrier1);
		barrier1.setPosition(167, -10);		
		addActorBefore(zactor, group1);
		
		
		/////////// G2////////////
		CellarGroup group2 = new CellarGroup(ng);
		group2.setPosition(440, 502);
		
		LogicFlower flower21 = new LogicFlower(ng);
		flower21.setPosition(-40, -20);
		group2.addFlower(flower21);
		
		LogicFlower flower22 = new LogicFlower(ng);
		flower22.setPosition(-60, -85);
		group2.addFlower(flower22);
		
		LogicFlower flower23 = new LogicFlower(ng);
		flower23.setPosition(0, -70);
		group2.addFlower(flower23);
				
		final Barrier barrier2 = new Barrier(ng);
		barrier2.addListener(new CoorListener());
		group2.addActor(barrier2);
		barrier2.setPosition(167, -10);		
		addActorBefore(zactor, group2);
		
		
		/////////// G3////////////
		CellarGroup group3 = new CellarGroup(ng);
		group3.setPosition(736, 543);
		
		LogicFlower flower31 = new LogicFlower(ng);
		flower31.setPosition(3, -60);
		group3.addFlower(flower31);
		
		LogicFlower flower32 = new LogicFlower(ng);
		flower32.setPosition(70, -60);
		group3.addFlower(flower32);
		
		LogicFlower flower33 = new LogicFlower(ng);
		flower33.setPosition(35, -115);
		group3.addFlower(flower33);
				
		final Barrier barrier3 = new Barrier(ng);
		barrier3.addListener(new CoorListener());
		group3.addActor(barrier3);
		barrier3.setPosition(167, -10);		
		addActorBefore(zactor, group3);
		
		
		/////////// G4////////////
		CellarGroup group4 = new CellarGroup(ng);
		group4.setPosition(623, 260);
		
		LogicFlower flower41 = new LogicFlower(ng);
		flower41.setPosition(3, -60);
		group4.addFlower(flower41);
		
		LogicFlower flower42 = new LogicFlower(ng);
		flower42.setPosition(70, -60);
		group4.addFlower(flower42);
		
		LogicFlower flower43 = new LogicFlower(ng);
		flower43.setPosition(35, -115);
		group4.addFlower(flower43);
				
		final Barrier barrier4 = new Barrier(ng);
		barrier4.addListener(new CoorListener());
		group4.addActor(barrier4);
		barrier4.setPosition(167, -10);		
		addActorBefore(zactor, group4);


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
	

		
		/*		ImageModel model2 = new ImageModel(ng, ng.getManager().getAtlas().findRegion("DOM1"));
		model2.setPosition(350,300);
		addActor(model2);
		ImageModel model3 = new ImageModel(ng, ng.getManager().getAtlas().findRegion("DOM1"));
		model3.setPosition(400,300);
		addActor(model3);
		ImageModel model4 = new ImageModel(ng, ng.getManager().getAtlas().findRegion("DOM1"));
		model4.setPosition(450,300);
		addActor(model4);*/
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
