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
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.me.neta.CellarGroup.LogicFlower;
import com.me.neta.CellarGroup.LogicLabel;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Popup.PopupGroup;
import com.me.neta.Util.OnEventAction.Predicate;
import com.me.neta.dummy.Dummy;
import com.me.neta.dummy.DummyContext;
import com.me.neta.dummy.DummyHelper;
//import com.me.neta.dummy.Dummy.DummyType;
import com.me.neta.dummy.DummyContext.DummyInfo;
import com.me.neta.dummy.DummyContext.GroupInfo;
import com.me.neta.events.CreateCellarsEvent;
import com.me.neta.events.GameEndEvent;
import com.me.neta.events.MovedToStartEvent;
import com.me.neta.events.ZIndexEvent;
import com.me.neta.factories.LetterFactory;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.figures.Letter;
import com.me.neta.tools.AbstractTool;
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
	StartButton startButton;
	

	public List<AbstractFigure> getFigures(){
		List<AbstractFigure> result = new LinkedList<AbstractFigure>();
		for(Actor a : getChildren()){
			if(a instanceof AbstractFigure){
				AbstractFigure f = (AbstractFigure)a;
				result.add(f);
			}
		}
		
		return result;
	}
	
	
	public StartButton getStartButton(){
		return startButton;
	}
	
	public AbstractFigure getSelected(){
		return selectedActor;
	}
	
	public void setSelectedActor(AbstractFigure figure){
		selectedActor =figure;
	}
	
	public int getId(){
		return id;
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
	
	private LogicFlower activeFlower;
	
	public void blinkOrders(){
		for(Actor actor : getChildren()){
			if(actor.getName()!=null && actor.getName().startsWith("order")){
				actor.addAction(sequence(delay(.3f), repeat(3, sequence(visible(false), delay(0.3f), visible(true), delay(0.3f)))));
			}
		}
	}
	
	
	public void removeOrders(){
		//List<Actor> rm = new LinkedList<Actor>();
		for(Actor actor : getChildren()){
			if(actor.getName()!=null && actor.getName().startsWith("order")){
				actor.addAction(Actions.removeActor());
			}
		}
		
		/*for(Actor a : rm){
			a.remove();
		}*/
	}
	public void step(){
		
		

		Sound snd = activeFlower.getLetter().getSound(); //activeFlower must not be null
		snd.play();
		
		ng.getContext().setProperty(ContextProperty.HALT, Boolean.TRUE);
		
		addAction(sequence(delay(1), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
							
			
						activeFlower.setDone(true);
						activeFlower.getLetter().animateVacant();
						final CellarGroup activeCG =activeFlower.getGroup();		
						for(Actor child : activeCG.getChildren()){
							if(child instanceof LogicFlower){
								LogicFlower flower = (LogicFlower)child;
								if(!flower.isDone() && flower!=activeFlower){
									activeFlower = activeFlower.isDone()? flower : ((activeFlower.getOrder() > flower.getOrder() ? flower : activeFlower));
								}
							}
						}
					
					
					if(activeFlower.isDone()){
						ng.getContext().setProperty(ContextProperty.PLAY,null);
			
						//TODO no more flowers! go to next group;
						activeCG.setDone(true);
						Actor actor = findActor("cg"+(activeCG.getOrder()+1));
						if(actor!=null){
							final CellarGroup cg = (CellarGroup)actor;
							activeFlower = (LogicFlower) cg.findActor("firstFlower");
							
							World.this.addAction(sequence(delay(0.5f),Util.zoomTo(0.7f,1, activeCG.getGroupOrigin().x, activeCG.getGroupOrigin().y,  null), delay(0.5f),
									Actions.run(new Runnable() {
								
								@Override
								public void run() {
									Barrier barrier = (Barrier) activeCG.findActor("barrier");
									barrier.open(0.35f);	
								
								}
							}), delay(1), Util.moveCameraTo(activeCG.getGroupOrigin().x, activeCG.getGroupOrigin().y, 0, null), Util.zoomTo(1, 1,activeCG.getGroupOrigin().x, activeCG.getGroupOrigin().y, null) , Actions.run(new Runnable() {
								
								@Override
								public void run() {
									cg.setEnabled(true);	
									ng.getContext().setProperty(ContextProperty.BETWEEN_CELLARS, Boolean.TRUE);
									cg.addListener(new InputListener(){
										public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
											Group topBtns = (Group)ng.getWorkspace().findActor("topButtons");
											AbstractTool kTool =  (AbstractTool) topBtns.findActor("kTool");
											if(kTool.findActor("popup")!=null){
												return true;
											}
											
											
											CellarGroup cellarGroup = (CellarGroup) event.getListenerActor();
											
										if(!cellarGroup.isDone() && ng.getContext().getProperty(ContextProperty.BETWEEN_CELLARS)!=null){
			
											activeFlower = (LogicFlower) cellarGroup.findActor("firstFlower");
											ng.getContext().setProperty(ContextProperty.ACTIVE_LETTER, Character.valueOf(activeFlower.getLetter().getCharacter()));
										
											
											addAction(sequence(Util.zoomTo(cellarGroup.getZoom(), 1, cellarGroup.getGroupOrigin().x, cellarGroup.getGroupOrigin().y, null), Actions.run(new Runnable() {			
												@Override
												public void run() {
													activeFlower.getLetter().animateSelected();
													ng.getContext().setProperty(ContextProperty.PLAY, Boolean.TRUE);
												
												}
											})));
			
											ng.getContext().setProperty(ContextProperty.BETWEEN_CELLARS, null);
											ng.getWorkspace().getPtGroup().onShow(null);
										}
											return true;
										}
									});
									
									final boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);

									Group topBtns = (Group)ng.getWorkspace().findActor("topButtons");
									AbstractTool kTool =  (AbstractTool) topBtns.findActor("kTool");
									if(!ng.getWorkspace().getTips().kubicTipShown && showPopupPref){
										kTool.setPopup("Прикоснись к кубику и передвигай  своего героя\n по дорожке  на столько камешков,  сколько\n цифр выпало на кубике. Если вы играете\n вдвоем, то шагать  надо по очереди-каждому\n со своим героем", 50, new PopupGroup(kTool), 0, true);
										ng.getWorkspace().getTips().kubicTipShown = true;
									}
									
									
								}
							})
									));
							
						}
						else{
							//total WIN!!
							System.out.println("WIN!");	
			
			
							
							Action seq = sequence(delay(0.5f),Util.zoomTo(0.7f,1, activeCG.getGroupOrigin().x, activeCG.getGroupOrigin().y,  null),delay(0.5f),Actions.run(new Runnable() {
								
								@Override
								public void run() {
									Barrier barrier = (Barrier) activeCG.findActor("barrier");
									barrier.open(0.35f);							
								}
							}), delay(1), Util.zoomTo(1, 1,activeCG.getGroupOrigin().x, activeCG.getGroupOrigin().y, null), Actions.run(new Runnable() {
								
								@Override
								public void run() {

									
									Cloud g4u = new Cloud("Молодец! Хорошо играл! А теперь - сюрприз!", ng);
									g4u.setPosition(370, getHeight()-g4u.getHeight()-50);
									World.this.addActor(g4u);
									g4u.addAction(sequence(delay(3), Actions.run(new Runnable(){

										@Override
										public void run() {
											final Cloud levin = new Cloud("Для тебя читает стихи\nпоэт Вадим Левин", ng);
											levin.setPosition(380, World.this.getHeight()-levin.getHeight());
											addActor(levin);
											 final Music speech = World.this.playLevin();
												World.this.addAction(Util.onEvent(Actions.run(new Runnable() {
													
													@Override
													public void run() {
														/*ng.getContext().setProperty(ContextProperty.GAME_END, new Object());
														ng.getContext().setProperty(ContextProperty.HALT, null);	*/
														levin.remove();
														fire(new GameEndEvent());
													}
												}), new Predicate() {
													
													@Override
													public boolean accept() {
														return !speech.isPlaying();
													}
												}));
											
										}
										
									}), Actions.removeActor()));
									
			
									

								}
							}));
							addAction(seq);
							
			
						}
						
			
					}
					else{
						//go to next flower
						
						activeFlower.getLetter().animateSelected();
						Letter letter =  activeFlower.getLetter();
						
						char ch = letter.getCharacter();
						ng.getContext().setProperty(ContextProperty.ACTIVE_LETTER, Character.valueOf(ch));
					}
					
					
					ng.getContext().setProperty(ContextProperty.HALT, null);
					
						}
			
			
			
		})));
	}
	
	
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
				Actor actor = hit(x, y, true);
				if(actor instanceof Moveable && (ng.getContext().getProperty(ContextProperty.HALT)==null || ng.getContext().getProperty(ContextProperty.BETWEEN_CELLARS)!=null)){
					if(actor instanceof AbstractFigure){
						if(selectedActor!=actor || selectedActor.isFilled()){
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
					List<Rectangle> rects = new LinkedList<Rectangle>();
					Actor passportActor = findActor("passport");
					Rectangle passportRect= new Rectangle(passportActor.getX(), passportActor.getY(), passportActor.getWidth(), passportActor.getHeight());
					rects.add(passportRect);
					
					Actor toolBar = ng.getWorkspace().findActor("toolBar");
					Rectangle toolBarRect= new Rectangle(toolBar.getX(), toolBar.getY(), toolBar.getWidth(), toolBar.getHeight());
					rects.add(toolBarRect);
					
					Actor toolButtons = ng.getWorkspace().findActor("topButtons");
					Rectangle toolButtonsRect= new Rectangle(toolButtons.getX(), toolButtons.getY(), toolButtons.getWidth(), toolButtons.getHeight());
					rects.add(toolButtonsRect);


					Rectangle worldRect = new Rectangle(0,0, World.this.getWidth(), World.this.getHeight());
/*					int c = 1;
					while(true){
						Actor cg = findActor("cg"+c++);
						if(cg==null){
							break;
						}						
						rects.add(new Rectangle(cg.getX(), cg.getY(), cg.getWidth(), cg.getHeight()));
					}*/
					
					Rectangle selfX = new Rectangle(startPosition.x+x-mouseXY.x,actor.getY(), actor.getWidth(), actor.getHeight());
					Rectangle selfY = new Rectangle(actor.getX(), startPosition.y +y- mouseXY.y, actor.getWidth(), actor.getHeight());

					boolean overlapsX = false;
					boolean overlapsY = false;

					for(Rectangle r : rects){
						if(selfX.overlaps(r)){
							overlapsX = true;
						}
						if(selfY.overlaps(r)){
							overlapsY = true;
						}						
					}
					if(!worldRect.contains(selfX)){
						overlapsX = true;
					}
					if(!worldRect.contains(selfY)){
						overlapsY = true;
					}
					
					
					if(!overlapsX)
					actor.setPosition(startPosition.x+x-mouseXY.x,  actor.getY());
					if(!overlapsY)
					actor.setPosition(actor.getX(), startPosition.y +y- mouseXY.y);

/*					if(startButton!=null){
						Rectangle startBtnRect = new Rectangle(startButton.getX(), startButton.getY(), startButton.getWidth(), startButton.getHeight());
						boolean over = false;
						if(selfX.overlaps(startBtnRect)){
							over = true;
						}
						if(selfY.overlaps(startBtnRect)){
							over = true;
						}	
						
						if(over){
							fire(new MovedToStartEvent());
						}
					}*/

					if(ng.getContext().getProperty(ContextProperty.MOVE_TO_START)!=null){
						
						ng.getContext().getProperty(ContextProperty.MOVE_TO_START);
					}
				}
				
			}
			
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if(pointer==this.pointer){
						actor = null;
					}
				}
				
	
		});
		

		populateLetters();
		
		
		//dummyHelper = new DummyHelper(ng, this);
		

		ObjectInputStream ois = null;
		try{
		FileHandle dummyFile = Gdx.files.internal("data/dummy/dummy-"+getTitle()+".ser");// Gdx.files.internal("data/dummy/dummy-"+getTitle()+".ser");				
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
			Vector2 go = dom.getGroupOrigin();
			float zoom = dom.getZoom();	
			 
			List<Action> actions = new LinkedList<Action>();
			if(domId!=1){
				actions.add(Util.zoomTo(.6f, 1f, prevX, prevY, null));
				actions.add(delay(2));
				actions.add(Util.moveCameraTo(go.x, go.y, 1, null));
			}
			actions.addAll(Arrays.asList( Util.zoomTo(zoom, 1, go.x, go.y, null), delay(2), moveToSequence(domId+1, go.x, go.y)));
			return sequence(actions.toArray(new Action[]{}));
		}
		
		return Util.zoomTo(1, 0.5f, getWidth()/2, getHeight()/2, null);
		
		
	}
	 
	 public Color letterColor(){
		 return Color.GREEN;
	 }
	 
	 public void refresh(){
			Actor cloud = findActor("restartCloud");
			if(cloud!=null){
				cloud.remove();
			}
			
			Actor btn = findActor("refresh");
			if(btn!=null){
				btn.remove();
			}
	 
		 createCellars();
	 }
	
	public  void start(){
		CellarGroup cg1 = (CellarGroup) findActor("cg1");
		cg1.setEnabled(true);
		activeFlower = (LogicFlower) cg1.findActor("firstFlower");
		ng.getContext().setProperty(ContextProperty.ACTIVE_LETTER, Character.valueOf(activeFlower.getLetter().getCharacter()));
		ng.getContext().setProperty(ContextProperty.POPUP, null);
	
		
		addAction(sequence(Util.zoomTo(cg1.getZoom(), 1, cg1.getGroupOrigin().x, cg1.getGroupOrigin().y, null), run(new Runnable() {			
			@Override
			public void run() {
				activeFlower.getLetter().animateSelected();
				ng.getContext().setProperty(ContextProperty.PLAY, Boolean.TRUE);
			}
		})));

		removeOrders();
		ng.getWorkspace().getPtGroup().onShow(null);
		
		for(Actor a : getChildren()){
			if(a.getName()!=null && a.getName().contains("hero")){
				a.toFront();
			}
		}
		
		Actor cloud = findActor("restartCloud");
		if(cloud!=null){
			cloud.remove();
		}
		
		Actor btn = findActor("refresh");
		if(btn!=null){
			btn.remove();
		}
	

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
				
				Random rnd = new Random(System.currentTimeMillis());
				for(int i = 0; i<chars.size(); i++){
					Character ch = chars.get(i);
					CellarGroup.LogicFlower dummy = flowers.get(i);
					dummy.addListener(new MetricListener());
					
					Letter letter = new Letter(ng, ch);
					letter.setName("letter");
					if(dummy.getInfo().contains("1")){
						letter.setPosition(20, 20);
					}
					else if(dummy.getInfo().contains("2")){
						letter.setPosition(15, 12);
					}
					else if(dummy.getInfo().contains("3")){
						letter.setPosition(18, 18);
					}

					letter.fill(letterColor());
					dummy.setLetter(letter);
					
					letter.setScale(0.1f);
					
					float dur =  rnd.nextFloat()*.5f;
					letter.addAction(sequence(scaleTo(1,1, dur)));
				}
				
				
		 }

		
			ng.getContext().setProperty(ContextProperty.LETTERS, new Object());

	}

	protected void createCellars(){

		Actor zactor = findActor("zactor");
		Actor lower = null;
		for(Actor a : getChildren()){
			if(a instanceof AbstractFigure){
				lower = lower == null ? a : (lower.getZIndex() > a.getZIndex() ? a : lower);
			}
		}
		
		if(lower == null){
			lower = zactor;
		}
		
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
			
			for(final GroupInfo gInfo : dummyContext.getGroups()){
				final CellarGroup cg = new CellarGroup(ng, gInfo.getOrder());				
				cg.setGroupOrigin(gInfo.getOrigin());
				//cg.setOrigin(gInfo.getOrigin().x, gInfo.getOrigin().y);
				cg.setZoom(gInfo.getZoom());
				cg.setDone(false);
				cg.setEnabled(false);
				cg.setName("cg"+gInfo.getOrder());
				

				cg.setPosition(gInfo.getOrigin().x, gInfo.getOrigin().y);
				
				
				cg.setScale(0.1f);
				//cg.setRotation(180);
				cg.addAction(sequence(scaleTo(1, 1, 1)/*, run(new Runnable() {
					
					@Override
					public void run() {
						Label orderLabel = new Label(Integer.toString(gInfo.getOrder()), ng.getManager().getSkin(), "order_"+getOrderLabelStyleDef());
						orderLabel.setName("order"+cg.getOrder());
						orderLabel.translate(0+cg.getX(), 100+cg.getY());
						addActor(orderLabel);
					}
				})*/));
				//addActor(cg);
				addActorBefore(lower, cg);
				
				Rectangle bounds = new Rectangle(gInfo.getOrigin().x, gInfo.getOrigin().y, 1, 1);

				//Map<Integer, LogicFlower> flowers = new HashMap<Integer, CellarGroup.LogicFlower>();
				for(DummyInfo info : gInfo.getFlowers()){
					
					if("start".equals(info.getType())){
						
						if(findActor("startButton")==null){
							startButton = new StartButton(ng, this);
							startButton.setName("startButton");
							ng.getContext().registerListener(startButton);
							startButton.setPosition(info.getX(), info.getY());
							
							//addActor(startButton);
							addActorBefore(lower, startButton);
						}

					}
					else if("barrier".equals(info.getType())){
						Barrier barrier = new Barrier(ng);
						barrier.setName("barrier");
						barrier.setPosition(info.getX()-cg.getX(), info.getY()-cg.getY());
						cg.addActor(barrier);
					}
					else if(info.getType().contains("FLOWER")){
						CellarGroup.LogicFlower flower = new CellarGroup.LogicFlower(ng, info.getType(), cg);				
						flower.setDone(false);
						flower.setPosition(info.getX()-cg.getX(), info.getY()-cg.getY());
						flower.setOrder(Integer.valueOf(info.getName()));
						if(flower.getOrder()==1){
							flower.setName("firstFlower");
						}
						cg.addActor(flower);
	
						//flowers.put(Integer.valueOf(info.getName()), flower);
					}
					else{
						
						Dummy dummy = new  Dummy(ng, ng.getManager().getAtlas().findRegion(info.getType()));
						if(info.getType().contains("DOM")){
							dummy.setGroupOrigin(gInfo.getOrigin());
							dummy.setZoom(gInfo.getZoom());
							//dummy.addListener(new MetricListener());
							//cg.setSize(info.getWidth(), info.getHeight());
							
						}
						dummy.setSize(info.getWidth(), info.getHeight());
						bounds.merge(new Rectangle(info.getX(), info.getY(), info.getWidth(), info.getHeight()));
						dummy.setPosition(info.getX()-cg.getX(), info.getY()-cg.getY());
						dummy.setName(info.getName());
						dummy.setGroup(gInfo.getOrder());
						dummy.setType(info.getType());
						
						//dummy.setScale(0);
						cg.addActor(dummy);
						//dummy.addAction(Actions.scaleTo(1, 1, 1));
					}
				}
			
/*				cg.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
				for(Actor a : cg.getChildren()){
					a.translate(gInfo.getOrigin().x - bounds.x, gInfo.getOrigin().y - bounds.y);
				}*/
/*				Label orderLabel = new Label(Integer.toString(gInfo.getOrder()), ng.getManager().getSkin(), "title_"+getOrderLabelStyleDef());
				orderLabel.setName("order");
				orderLabel.translate(0+cg.getX(), 50+cg.getY());
				addActor(orderLabel);*/
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
		ng.getContext().setProperty(ContextProperty.LETTERS, null);

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
		

		
		table.setPosition(20, this.getHeight()-table.getHeight()-20);
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
	
	public LogicLabel label(String txt){
		LogicLabel l1 = new LogicLabel(ng, txt, getTitle());
		return l1;
	}
	
	public Table line(){
		Table table = new Table();
		table.defaults().padRight(16);
		return table;
	}
	
	public Table block(){
		Table block1 = new Table();
		float pad = 2;
		block1.defaults().align(Align.left).padRight(pad);
		return block1;
	}
	
	
	Music playLevin(){
		 Music snd =  tm.getMusic(getTitle());
		snd.play();
		return snd;
	 }
	
	//public abstract String getLyricsAsString();
	
	public Actor lyricsForFrame(){
		Table table = new Table();
		//table.add
		return table;
	}
	
	public abstract String getOrderLabelStyleDef();
	
	public void showLyrics(){
		//LyricsFrame frame= new LyricsFrame(ng,lyricsForFrame());
		//addActor(frame);
	}
}
