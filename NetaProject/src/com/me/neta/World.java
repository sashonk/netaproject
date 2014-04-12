package com.me.neta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.me.neta.Context.ContextProperty;
import com.me.neta.dummy.Dummy;
import com.me.neta.dummy.DummyContext;
import com.me.neta.dummy.DummyHelper;
//import com.me.neta.dummy.Dummy.DummyType;
import com.me.neta.dummy.DummyContext.DummyInfo;
import com.me.neta.dummy.DummyContext.GroupInfo;
import com.me.neta.events.LyricsIconEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.factories.LetterFactory;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.figures.Letter;
import com.me.neta.tools.BrushTool;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.StartButton;
import com.me.neta.tools.ZIndexTool;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public abstract class World extends Group{

	BrushTool btool;
	boolean lyricsAdded = false;
	private int id;
	TextureManager tm;
	private boolean colorize;
	private AbstractFigure selectedActor;
	protected NetaGame ng; 
	protected static final float FIELD_HEIGHT= 20;
	protected Map<Integer, Map<Integer, List<Character>>> letters = new HashMap<Integer, Map<Integer, List<Character>>>();
	DummyHelper dummyHelper;
	DummyContext baseDummyContext;
	
	public AbstractFigure getSelected(){
		return selectedActor;
	}
	
	public void setSelectedActor(AbstractFigure figure){
		selectedActor =figure;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setColorizing(boolean value){
		colorize = value;
	}
	
	public void addLyrics(int choice){
		//TODO
	}
	
	public boolean verticalTopPanel(){
		return false;
	}
	
	public boolean showPassport(){
		return false;
	}
	
	public  Map<Integer, Map<Integer, List<Character>>> getLetters(){
		return letters;
	}
	
	public abstract void populateLetters();
	
	
	
	public static Actor multiColorLabel(String text, String baseStyle, String[] colorNames, Skin skin){
		Table table = new Table();
		boolean styleSet = false;
		for(int i = 0; i<text.length(); i++){
			char c = text.charAt(i);
			
			
			String style =new StringBuilder(baseStyle).append("_").append(colorNames[i]).toString() ; 									
			Label label = new Label(new String(new char[]{c}), skin, style);
			if(!styleSet){
				table.defaults().padRight(label.getStyle().font.getBounds(" ").width);
				styleSet = true;
			}
			table.add(label);
		}
		table.pack();
		
		return table;
	}

	public World(final NetaGame ng, float width, float height){
		this.ng = ng;
		setName(getTitle());
		
		colorize = false;
		tm = ng.getManager();		
		this.setBounds(0, 0, width, height);		
		setOrigin(getWidth()/2, getHeight()/2);

		//this.addListener(new MetricListener());

		
		this.addListener(new InputListener(){
			Vector2 startPosition;
			Vector2 mouseXY;
			int pointer;
			Actor actor;
			
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Actor actor = hit(x, y, false);
				if(actor instanceof Moveable && Gdx.input.isKeyPressed(Keys.M)){
					if(actor instanceof AbstractFigure){
						if(selectedActor!=actor){
							return false;
						}
					}
					this.pointer = pointer;
					 startPosition = new Vector2(actor.getX(), actor.getY());
					 mouseXY = new Vector2(x, y);
					 //local.rotate(-actor.getRotation());
					 this.actor = actor;
					return true;
				}
				
				if(dummyHelper!=null){
					dummyHelper.handleInput(x, y);
				}
				

				return false;
			}
			
			public void touchDragged (InputEvent event, float x, float y, int pointer) {
				if( actor!=null){
					actor.setPosition(startPosition.x+x-mouseXY.x,  startPosition.y +y- mouseXY.y );
				}
				
			}
			
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if(pointer==this.pointer){
						actor = null;
					}
				}
				
	
		});
		

		populateLetters();
		
		
		dummyHelper = new DummyHelper(ng, this);
		

		ObjectInputStream ois = null;
		try{
		FileHandle dummyFile = Gdx.files.absolute("D:\\dummy.txt");// Gdx.files.internal("data/dummy/dummy-"+getTitle()+".ser");				
		ois = new ObjectInputStream(new ByteArrayInputStream(dummyFile.readBytes()));
		 baseDummyContext = (DummyContext) ois.readObject();
		}
		catch(Exception ex){
			System.err.println(ex);
		}
		finally{
			if(ois!=null)
			{
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	 
	 Action moveToSequence(int domId, float prevX, float prevY){
		Dummy dom =  (Dummy) findActor("dom"+domId);
		
		if(dom!=null){
			Size go = dom.getGroupOrigin();
			float zoom = dom.getZoom();	
			 
			List<Action> actions = new LinkedList<Action>();
			if(domId!=1){
				actions.add(Util.zoomTo(.6f, 1f, prevX, prevY, null));
				actions.add(delay(2));
				actions.add(Util.moveCameraTo(go.width, go.height, 1, null));
			}
			actions.addAll(Arrays.asList( Util.zoomTo(zoom, 1, go.width, go.height, null), delay(2), moveToSequence(domId+1, go.width, go.height)));
			return sequence(actions.toArray(new Action[]{}));
		}
		
		return Util.zoomTo(1, 0.5f, getWidth()/2, getHeight()/2, null);
		
		
	}
	 
	 public Color letterColor(){
		 return Color.GREEN;
	 }
	
	public  void start(){
		
		this.addAction(moveToSequence(1, 0, 0));
		
	}
	
	protected void letters(int variant){
		 Map<Integer, Map<Integer, List<Character>>> letters=  getLetters();
		 Map<Integer, List<Character>> variantLetters = letters.get(Integer.valueOf(variant));
		 
		 for(Integer groupId : variantLetters.keySet()){
			 	List<Character> chars = variantLetters.get(Integer.valueOf(groupId));			 	
			 	List<CellarGroup.LogicFlower> flowers = new LinkedList<CellarGroup.LogicFlower>();
			 	CellarGroup cg = (CellarGroup)findActor("cg"+groupId.intValue());
				for(Actor actor : cg.getChildren()){
					if(actor instanceof CellarGroup.LogicFlower ){						
						CellarGroup.LogicFlower  dummy  = (CellarGroup.LogicFlower)actor;
						flowers.add(dummy);
					}
				}		
				
				for(int i = 0; i<chars.size(); i++){
					Character ch = chars.get(i);
					CellarGroup.LogicFlower dummy = flowers.get(i);
					dummy.addListener(new MetricListener());
					
					Integer letterID = Letter.getCharId(ch);
					Letter letter = new Letter(ng, letterID.intValue());
					letter.setName("letter");
					letter.setPosition(20, 20);
					letter.setColor(letterColor());
					dummy.addActor(letter);
				}
				
				
		 }

		
		ng.getContext().setProperty(ContextProperty.LETTERS, new Object());
	}

	protected void createCellars(){

		ObjectInputStream ois = null;
		DummyContext dummyContext;
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos  = new ObjectOutputStream(baos);
			oos.writeObject(baseDummyContext);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);			
			dummyContext = (DummyContext) ois.readObject();
			
			List<Actor> rm = new ArrayList<Actor>(getChildren().size);
			Iterator<Actor> iter = getChildren().iterator();
			while(iter.hasNext()){
				Actor a = iter.next();
				if(a instanceof CellarGroup){
					rm.add(a);
				}
			}
			for(Actor a: rm){
				a.remove();
			}
			
			for(GroupInfo gInfo : dummyContext.getGroups()){
				CellarGroup cg = new CellarGroup(ng, gInfo.getOrder());
				cg.setName("cg"+gInfo.getOrder());
				addActor(cg);
				
				
				for(DummyInfo info : gInfo.getFlowers()){
					
					if("start".equals(info.getType())){
						StartButton btn = new StartButton(ng, this);
						ng.getContext().registerListener(btn);
						btn.setPosition(info.getX(), info.getY());
						
						cg.addActor(btn);
					}
					else if("barrier".equals(info.getType())){
						Barrier barrier = new Barrier(ng);
						barrier.setPosition(info.getX(), info.getY());
						cg.addActor(barrier);
					}
					else if(info.getType().contains("FLOWER")){
						CellarGroup.LogicFlower flower = new CellarGroup.LogicFlower(ng, info.getType());						
						flower.setPosition(info.getX(), info.getY());
						cg.addActor(flower);
					}
					else{
						
						Dummy dummy = new  Dummy(ng, ng.getManager().getAtlas().findRegion(info.getType()));
						if(info.getType().contains("DOM")){
							dummy.setGroupOrigin(gInfo.getOrigin());
							dummy.setZoom(gInfo.getZoom());
							dummy.addListener(new MetricListener());
						}
						dummy.setSize(info.getWidth(), info.getHeight());
						dummy.setPosition(info.getX(), info.getY());
						dummy.setName(info.getName());
						dummy.setGroup(gInfo.getOrder());
						dummy.setType(info.getType());
						
						cg.addActor(dummy);		
					}
				}
			
			}
			

		}
		catch(Exception ex){
			System.err.println(ex);
		}
		finally{
			if(ois!=null)
			{
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		lyrics();
	}

	public abstract void lyrics();
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		Color c = this.getColor();
		Texture tx = tm.getUnmanaged(c);
		
		
		batch.setColor(1, 1, 1, c.a* parentAlpha);		
		//batch.draw(tx, getX(), getY(), getWidth(), getHeight());
		batch.draw(tx, getX(), getY(),getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(),0,0,1,1 ,false, false);
		
		super.draw(batch, parentAlpha);
	}
	
	public void setFocus(){
		Group passport = (Group) findActor("passport");
		for(Actor actor : passport.getChildren()){
			if(actor instanceof TextField){
				getStage().setKeyboardFocus(actor);												
				break;
			}
		}
	}
	
	public boolean isColorizing(){
		return colorize;
	}
	

	public void drawPassport(Passport p){
		Group passport = (Group) findActor("passport");
		Label author =  (Label) passport.findActor("author");
		Label age =  (Label) passport.findActor("age");
		Label city =  (Label) passport.findActor("city");
		Label state =  (Label) passport.findActor("state");
		Label year =  (Label) passport.findActor("year");
		
		author.setText(p.name!=null ? p.name : "");
		age.setText(p.age!=null ? p.age.toString() : "");
		city.setText(p.city!=null ? p.city : "");
		state.setText(p.country!=null ? p.country : "");
		year.setText(p.year!=null ? p.year.toString() : "");
	}
	
	public abstract String getTitle();
	

	

	public abstract void populateBackground();
	
	public abstract void populateForeground();
	
	public abstract Actor createTitle();
	
	public abstract String getAuthors();
	
	public abstract String getPassportColor(); 
	
	public void createPassport() {
		Actor title = createTitle();//Util.multiColorLabel("ПАУЧОК", "title", new String[]{"red", "orange", "yellow", "green", "blue", "green"}, ng.getManager().getSkin());
	
		Label description = new Label("Шагалка - искалка", ng.getManager().getSkin(), "metal"); 
		Label authorLabel = new Label("Авторы стихов ", ng.getManager().getSkin(), "lightBlue"); 
		Label authorValue = new Label(getAuthors()/*"Вадим Левин и Рената Муха"*/, ng.getManager().getSkin(), "darkViolet"); 
		Label nameLabel = new Label("Автор иллюстратор ", ng.getManager().getSkin(), "lightBlue");
		
		Label nameValue0 = new Label(".", ng.getManager().getSkin(), getPassportColor());
		Label nameValue = new Label(".......................", ng.getManager().getSkin(), "lightBlue");
		nameValue0.setName("author");
		Group nameGroup = new Group();
		nameGroup.addActor(nameValue);
		nameValue0.setY(5);
		nameValue0.setWidth(nameValue.getWidth());
		nameGroup.addActor(nameValue0);
		nameGroup.setSize(nameValue.getWidth(), nameValue.getHeight());
		nameValue0.setAlignment(Align.center);

		Label ageLabel = new Label(", возраст", ng.getManager().getSkin(), "lightBlue");
		Label ageValue0 = new Label(".", ng.getManager().getSkin(), getPassportColor());
		Label ageValue = new Label(".....", ng.getManager().getSkin(), "lightBlue");
		ageValue0.setName("age");
		Group ageGroup = new Group();
		ageGroup.addActor(ageValue);
		ageValue0.setY(5);
		ageValue0.setWidth(ageValue.getWidth());
		ageGroup.addActor(ageValue0);
		ageGroup.setSize(ageValue.getWidth(), ageValue.getHeight());
		ageValue0.setAlignment(Align.center);		
						
		Label cityName = new Label("Город (село) ", ng.getManager().getSkin(), "lightBlue");		
		Label cityValue0 = new Label(".", ng.getManager().getSkin(), getPassportColor());		
		Label cityValue = new Label("......................", ng.getManager().getSkin(), "lightBlue");
		cityValue0.setName("city");
		Group cityGroup = new Group();
		cityGroup.addActor(cityValue);
		cityValue0.setY(5);
		cityValue0.setWidth(cityValue.getWidth());
		cityGroup.addActor(cityValue0);
		cityGroup.setSize(cityValue.getWidth(), cityValue.getHeight());
		cityValue0.setAlignment(Align.center);			
		
				
		Label countryName = new Label(", страна ", ng.getManager().getSkin(), "lightBlue");
		Label countryValue0 = new Label(".", ng.getManager().getSkin(), getPassportColor());		
		Label countryValue = new Label(".....................", ng.getManager().getSkin(), "lightBlue");
		countryValue0.setName("state");
		Group countryGroup = new Group();
		countryGroup.addActor(countryValue);
		countryValue0.setY(5);
		countryValue0.setWidth(countryValue.getWidth());
		countryGroup.addActor(countryValue0);
		countryGroup.setSize(countryValue.getWidth(), countryValue.getHeight());
		countryValue0.setAlignment(Align.center);				
		
		
		Label yearName = new Label(", год ", ng.getManager().getSkin(), "lightBlue");
		Label yearValue0 = new Label(".", ng.getManager().getSkin(), getPassportColor());				
		Label yearValue = new Label(".........", ng.getManager().getSkin(), "lightBlue");
		yearValue0.setName("year");
		Group yearGroup = new Group();
		yearGroup.addActor(yearValue);
		yearValue0.setY(5);
		yearValue0.setWidth(yearValue.getWidth());
		yearGroup.addActor(yearValue0);
		yearGroup.setSize(yearValue.getWidth(), yearValue.getHeight());
		yearValue0.setAlignment(Align.center);	
		
		Table table = new Table();
		table.defaults().align(Align.left);
		table.add(title).row();
		table.add(description).row().padBottom(10);
		
		Table authRow = new Table();
		authRow.add(authorLabel);
		authRow.add(authorValue);
		authRow.pack();
		table.add(authRow).row();
		
		Table nameRow = new Table();
		nameRow.add(nameLabel);
		nameRow.add(nameGroup);
		nameRow.add(ageLabel);
		nameRow.add(ageGroup);
		nameRow.pack();
		table.add(nameRow).row();
		
		Table geoRow = new Table();
		geoRow.add(cityName);
		geoRow.add(cityGroup);
		geoRow.add(countryName);
		geoRow.add(countryGroup);
		geoRow.add(yearName);
		geoRow.add(yearGroup);
		geoRow.pack();
		table.add(geoRow);
		table.pack();
		

		
		table.setPosition(20, ng.getWorkspace().getHeight()-table.getHeight()-20);
		addActor(table);
		
		table.addListener(new MetricListener());

		table.setName("passport");

	}
	
	public  void populate(){
		createPassport();
		populateBackground();
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		addActor(zactor);
		
		//createCellars();
		
		populateForeground();
	}
	
}
