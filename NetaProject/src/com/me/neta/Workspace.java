package com.me.neta;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;



import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Popup.PopupGroup;
import com.me.neta.Util.OnEventAction.Predicate;
import com.me.neta.events.*;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.AbstractTool;
import com.me.neta.tools.BasketTool;
import com.me.neta.tools.BrushTool;
import com.me.neta.tools.FlowerTool;
import com.me.neta.tools.KubikTool;
import com.me.neta.tools.PanelTool;
import com.me.neta.tools.PanelToolListener;
import com.me.neta.tools.SaveTool.SavePanelHideEvent;
import com.me.neta.tools.ShopTool;
import com.me.neta.tools.StartButton;
import com.me.neta.tools.UnfillTool;
import com.me.neta.tools.WorldsTool;
import com.me.neta.tools.FiguresTool;
import com.me.neta.tools.PassportTool;
import com.me.neta.tools.ExitTool;
import com.me.neta.tools.LyricsTool;
import com.me.neta.tools.ColorTool;
import com.me.neta.tools.QuestionTool;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.SaveTool;
import com.me.neta.tools.SettingsTool;
import com.me.neta.tools.ZIndexTool;
import com.me.neta.worlds.AntWorld;
import com.me.neta.worlds.PitonWorld;
import com.me.neta.worlds.SpiderWorld;
import com.me.neta.worlds.TigerWorld;

public class Workspace extends Group  {



	ZIndexTool zTool;
	BrushTool bTool;
	RotateTool rTool;
	QuestionTool qTool;
	 FlowerTool flowerTool;
	// UnfillTool uTool;	
	 KubikTool kTool;

	 
	static final float pad = 15;
	PanelToolGroup ptGroup;
	public PanelToolGroup getPtGroup(){
		return ptGroup;
	}
	
	public Pinch2ZoomListener2 getPinch2Zoom(){
		return pinch2Zoom;
	}
	
	private Pinch2ZoomListener2 pinch2Zoom;
	Passport passport;
	Map<Integer, WorldFactory> worldFactories = new HashMap<Integer, WorldFactory>();
	NetaGame ng;
	String bottomActorName = "bottomActor";
	Table toolbarTable;
	private Color selectedColor ;
	 WorldsTool worldsTool;	
	 PassportTool passportTool ;
	LyricsTool lyricsTool;
 FiguresTool figuresTool;
ColorTool paletteTool;
	SettingsTool settingTool	 ;
	 BasketTool basketTool;	
	 
	boolean startHasEverBeenClickedOnPopup;
	boolean checkBrushTool;
	boolean makeIllustrationTipShown;
	boolean makeRoamsTipShown;
	boolean gameStartedTip;
	int figureDropsTotal;
	boolean welcomeToPaintingTipShown;
	boolean notifiedOfColoredFigures;
	boolean firstTouchAfterColoredFiguresNotification;
		 
	public Workspace(NetaGame ng, float x, float y, float width, float height){
		this.ng = ng;
		setBounds(x, y, width, height);
		setName("workspace");

	}
	
	public void initialize(){
		
		initContext(ng.getContext());
		checkBrushTool = false;
		makeIllustrationTipShown = false;
		makeRoamsTipShown = false;
		gameStartedTip= false;
		figureDropsTotal = 0;
		welcomeToPaintingTipShown = false;
		notifiedOfColoredFigures= false;
		firstTouchAfterColoredFiguresNotification = false;
		
		final boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
		startHasEverBeenClickedOnPopup= !showPopupPref;
		populateWorldFactories(ng);
		passport = new Passport();
		pinch2Zoom = new Pinch2ZoomListener2();
		pinch2Zoom.setZMin(.125f);
		this.addCaptureListener(pinch2Zoom);
			
		Actor bottom = new Actor();
		bottom.setName(bottomActorName);
		this.addActor(bottom);

		Image img = new Image(ng.getManager().getSkin().getPatch("panelNP"));
		img.setWidth(getWidth());
		toolbarTable = new Table();
		toolbarTable.setName("toolBar");
		
		//toolbarTable.debug();
	//	toolbarTable.debugTable();
		
		final ExitTool linkTool = new ExitTool(ng);
		toolbarTable.add(linkTool).padRight(pad).padLeft(pad);
		
//////////////////////////////////////////////////////////////////		
	////////////////// 	 TOOLS  /////////////////////////
/////////////////////////////////////////////////////////////////
		float panelHeight = 115;
		
		 basketTool = new BasketTool(ng); 
		basketTool.setSize(68,78);
		toolbarTable.add(basketTool).padRight(pad).padLeft(pad);
						
		 worldsTool = new WorldsTool(ng);
		final WorldsPanel worldsPanel = new WorldsPanel(ng,600, 350);
		worldsPanel.setPosition(35, 75);
		worldsPanel.setVisible(false);
		Color c = worldsPanel.getColor();
		worldsPanel.setColor(c.r, c.g, c.b, 0);
		addActor(worldsPanel);
		worldsTool.setPanel(worldsPanel);
		toolbarTable.add(worldsTool).padRight(pad).padLeft(pad);
		
		
		lyricsTool = new LyricsTool(ng);
		final LyricsPanel2 lyricsPanel = new LyricsPanel2(ng,800, 400);
		lyricsPanel.setPosition(105, panelHeight);
		lyricsPanel.setColor(c);
		lyricsPanel.setVisible(false);
		addActor(lyricsPanel);
		lyricsTool.setPanel(lyricsPanel);
				
		toolbarTable.add(lyricsTool).padRight(pad).padLeft(pad);
		passportTool = new PassportTool(ng);
		final PassportForm2 form = new PassportForm2(ng);
		form.setPosition(490-113, panelHeight);
		form.setColor(c);
		form.setVisible(false);
		addActor(form);
		passportTool.setPanel(form);	
		toolbarTable.add(passportTool).padRight(pad).padLeft(pad);
		
		
	figuresTool = new FiguresTool(ng);
	//figuresTool.registerListener(this);
	
		toolbarTable.add(figuresTool).padRight(pad).padLeft(pad);
		FiguresPanel figPanel = new FiguresPanel(ng);
		figPanel.setColor(c);
		figPanel.setVisible(false);
		figPanel.setWidth(550);
		figPanel.setHeight(450);
		figPanel.setPosition(340, 75);
		figuresTool.setPanel(figPanel);
		this.addActor(figPanel);
		
		  flowerTool = new FlowerTool(ng);
		
		final FlowerPanel lettersPanel = new FlowerPanel(ng);
		lettersPanel.setVisible(false);
		flowerTool.setPanel(lettersPanel);
		this.addActor(lettersPanel);
		toolbarTable.add(flowerTool).padRight(pad).padLeft(pad);
		Util.center(lettersPanel);

		
		 paletteTool = new ColorTool(ng);
		toolbarTable.add(paletteTool).padRight(pad).padLeft(pad);
		ColorPanel palette= new ColorPanel(ng);
		paletteTool.setPanel(palette);
		palette.setColor(c);
		palette.setVisible(false);
		palette.setWidth(560);
		palette.setHeight(460);
		palette.setPosition(360, 75);
		paletteTool.setPanel(palette);
		this.addActor(palette);
		
		final ShopTool shopTool = new ShopTool(ng);
		final ShopPanel gameshopPanel = new ShopPanel(ng);
		gameshopPanel.setVisible(false);
		gameshopPanel.setPosition(600, panelHeight);
		this.addActor(gameshopPanel);
		shopTool.setPanel(gameshopPanel);
		toolbarTable.add(shopTool).padRight(pad).padLeft(pad);
		
		final SaveTool saveTool = new SaveTool(ng);
		toolbarTable.add(saveTool).padRight(pad).padLeft(pad);
		SavePanel savePanel = new SavePanel(ng);
		saveTool.setPanel(palette);
		savePanel.setColor(c);
		savePanel.setVisible(false);
		savePanel.setPosition(700, panelHeight);
		saveTool.setPanel(savePanel);
		this.addActor(savePanel);
		
		settingTool = new SettingsTool(ng);
		toolbarTable.add(settingTool).padRight(pad).padLeft(pad);
		SettingsPanel settingsPanel = new SettingsPanel(ng);

		
		
		settingTool.setPanel(palette);
		settingsPanel.setColor(c);
		settingsPanel.setVisible(false);
		settingsPanel.setPosition(735, panelHeight);
		settingTool.setPanel(settingsPanel);
		this.addActor(settingsPanel);
				
		addActor(toolbarTable);
		addActor(img);
		toolbarTable.setZIndex(99);
		
		
		Table topButtons = new Table();
		topButtons.setName("topButtons");
		 rTool = new RotateTool(ng);
		 zTool = new ZIndexTool(ng);
		// uTool = new UnfillTool(ng);
		 bTool = new BrushTool(ng);
		 bTool.setChecked(false);
		qTool = new QuestionTool(ng);
		kTool = new KubikTool(ng);
		
		float topPad = 2;

		topButtons.defaults().padRight(topPad);
		topButtons.add(kTool);
		topButtons.add(rTool);
		topButtons.add(bTool);
		//topButtons.add(uTool);
		topButtons.add(qTool);
		topButtons.pack();
		topButtons.setPosition(800, 700);
		
		addActorAfter(this.findActor(bottomActorName), topButtons);

		toolbarTable.pack();


			
//////////////////////////////////////////////////////////////////////		
//////////////////////////////////////////////////////////////////////
///////////        HANDLE CHILD EVENTS		//////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
		
		this.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event instanceof DragStartEvent){
					pinch2Zoom.setCanPan(false);
					event.setBubbles(false);

				}
				if(event instanceof DragStopEvent){
					pinch2Zoom.setCanPan(true);
					event.setBubbles(false);

				}
				
				if(event instanceof CreateCellarsEvent){
					event.setBubbles(false);
					
					CreateCellarsEvent cEvent = (CreateCellarsEvent)event;
					int choice = cEvent.getChoice();

					if(choice==world.getId()){
						world.createCellars();
						//world.addLyrics(lyricsEvent.getChoice());
						
						//ng.getContext().setProperty(ContextProperty.LETTERS, new Object());
						ng.getContext().setProperty(ContextProperty.CELLARS, Boolean.TRUE);
						lyricsPanel.addAction(sequence(fadeOut(0.4f), visible(false)));
						
						
						if(showPopupPref&&!makeRoamsTipShown){
							makeRoamsTipShown = true;
							figuresTool.setPopup("Соедини СТАНЦИИ дорожкой из камешков.\nТолько не перепутай порядок СТАНЦИЙ!", 0, new PopupGroup(figuresTool,basketTool,rTool, lyricsTool), 0);
							
					
						}
					}
					else{
						ng.getManager().getSound("error").play();
					}
					

				}
				
				if(event instanceof LetterVariantEvent){
					event.setBubbles(false);

					//world.start();
					world.letters(((LetterVariantEvent)event).getLetterGroupID());
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
				if(!makeIllustrationTipShown&& showPopupPref){
					//popup("Ты можешь выбрать цвет и\n раскрасить фигуры", 550, 80, "popupPalette");
					figuresTool.setPopup("Сделай иллюстрацию к стихам", 00, new PopupGroup(figuresTool,basketTool, lyricsTool), 0);
					makeIllustrationTipShown = true;
				}
					

				}
				
				if(event instanceof WorldSelectionEvent){
					for(Actor act : getChildren()){
						if(act.getName()!=null && act.getName().contains("popup")){
							act.addAction(Actions.removeActor());
						}
					}
					
					event.setBubbles(false);

					WorldSelectionEvent desktopEvent = (WorldSelectionEvent) event;
					
					int deskId = desktopEvent.getId();
					
					if(deskId>=0){
						Actor abandoningWorld = null;
						if(world!=null && world.getParent()!=null){
							 abandoningWorld = world;
							 //abandoningWorld.setOrigin(abandoningWorld.getWidth()/2, abandoningWorld.getHeight()/2);
							abandoningWorld.addAction(abandonWorldAction(abandoningWorld));
							
						}
						
						
						world = worldFactories.get(desktopEvent.getId()).create();
						findActor("topButtons").setPosition(800, 700);		
						world.populate();
						world.setZIndex(1);
						
		

						worldsPanel.setVisible(false);
						addActorBefore(abandoningWorld!=null ? abandoningWorld : Workspace.this.findActor(bottomActorName), world);

						world.setId(desktopEvent.getId());
						world.drawPassport(passport);
	
						if(!passportTool.hasEverBeenClickedOnPopup()){
							passportTool.setPopup("Напиши своё имя, возраст\n и где ты живешь", 00, new PopupGroup(passportTool), 0);
						}


					}
					
					ng.getContext().setProperty(ContextProperty.WORKING, Boolean.TRUE) ;
					ng.getContext().setProperty(ContextProperty.LETTERS, null);
					ng.getContext().setProperty(ContextProperty.CELLARS, null);
					ng.getContext().setProperty(ContextProperty.INGAME, null);
					ng.getContext().setProperty(ContextProperty.GAME_END, null);

					lettersPanel.setVariants(world.getLetters());

				}
				
				
				
				if(event instanceof FigureDropEvent){
					event.setBubbles(false);
					figureDropsTotal++;

								
					FigureDropEvent dropEvent = (FigureDropEvent) event; 
					Actor letter= dropEvent.getActor();		
					
					Actor z = world.findActor("zactor");
					world.addActorBefore(z, letter);
							
					setSelectedFigure((AbstractFigure) letter);
					
					if(figureDropsTotal>4 && !rTool.hasEverBeenClickedOnPopup() && showPopupPref){
						ptGroup.onShow(null);
						rTool.setPopup("Обрати внимание:\nС помощью этой кнопки\n ты можешь вращать фигуры", 30, new PopupGroup(rTool), 0, true);
					}
					
					if(makeIllustrationTipShown && !welcomeToPaintingTipShown){
						
						addAction(sequence(delay(10), Actions.run(new Runnable() {
							
							@Override
							public void run() {


								Cloud cloud = new Cloud("Когда будешь готов, нажми на стрелку", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										
										
										paletteTool.setPopup("Выбери цвета  и раскрась  свою картину", 0, new PopupGroup(figuresTool,basketTool, lyricsTool, paletteTool,bTool, rTool ), 0);
										ptGroup.onShow(null);
										
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							
								
							
							}
						})));
						
						welcomeToPaintingTipShown = true;
					}
				}
				
				if(event instanceof GameBeginEvent){
					event.setBubbles(false);
					
					Actor cp = Workspace.this.findActor("gameStartedCloud");
					if(cp!=null){
						cp.remove();
					}
				}
				
				if(event instanceof MovedToStartEvent){
					event.setBubbles(false);					
					if(!gameStartedTip){
						
				
						Actor cp = Workspace.this.findActor("canPlay");
						if(cp!=null){
							cp.remove();
						}
						
						Cloud cl = new Cloud("Нажми на кнопку СТАРТ. Игра началась!", ng);
						cl.setName("gameStartedCloud");
						cl.setPosition(400, getHeight()-cl.getHeight());
						Workspace.this.addActor(cl);	
						
						ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(world.getStartButton(), lyricsTool));
						gameStartedTip = true;
					}
				}
				
				
				if(event instanceof SelectFigureEvent){
					setSelectedFigure((AbstractFigure) event.getTarget());	
					if(bTool.isChecked() && ng.getContext().getProperty(ContextProperty.HALT)==null){
						world.getSelected().fill(selectedColor);
						
						
						
						if(!startHasEverBeenClickedOnPopup && showPopupPref){
							final StartButton sb = world.getStartButton();
							boolean allFilled = true;
							int filledCount = 0;
							for(AbstractFigure af : world.getFigures()){
								if(!af.isFilled()){
									allFilled = false;									
								}
								else{
									filledCount++;
								}
							}
							
							if(filledCount>1 &&  !notifiedOfColoredFigures){
								//bTool.setPopup("Обрати внимание:\nкнопка \"кисточка\" полезна для раскрашивания фигур.\nКоснись фигуры ", tailPadX, pg, hideTimeout, upsideDown);
								ptGroup.onShow(null);
							//	bTool.setPopup("Обрати внимание:\nПока фигуры не раскрашены, их можно двигать.\nРаскрашенные фигуры прилипают к рабочему полю\nЕсли коснуться фигуры, когда \"кисточка\" окрашена,\n тогда фигура тоже окрасится", 30, new PopupGroup(bTool), 0, true);
								
								Cloud payAtt = new Cloud("Обрати внимание:\nПока фигуры не раскрашены, их можно двигать.\nРаскрашенные фигуры прилипают к рабочему полю\nФигуру можно удалить, нажав кнопку \"корзина\""
										, ng, new Cloud.Callback(){

											@Override
											public void run(Cloud cloud) {
												ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool));
												ptGroup.onShow(null);	
												cloud.remove();
												
												Workspace.this.addAction(sequence(delay(1), Actions.run(new Runnable() {
													
													@Override
													public void run() {
														Cloud rest = new Cloud("Раскрась оставшиеся фигуры", ng);
														rest.setName("rest");
														rest.setPosition(400, Workspace.this.getHeight()- rest.getHeight());
														Workspace.this.addActor(rest);
														rest.addAction(sequence(delay(10), Actions.removeActor()));
													}
												})));

											}
											
										});
								payAtt.setPosition(400, getHeight()-payAtt.getHeight());
								addActor(payAtt);
								ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(basketTool, rTool, bTool));
							
								
								notifiedOfColoredFigures = true;
							
							}
							
							if(sb!=null &&allFilled && filledCount > 7){
								//sb.setPopup("Молодец! Когда сделаешь иллюстрацию,\n жми СТАРТ и игра начнётся!", -100, new PopupGroup(figuresTool, basketTool,  paletteTool, rTool, bTool/*, uTool*/,world.getStartButton()), 5);
								ptGroup.onShow(null);
								addAction(sequence(Actions.run(new Runnable() {
									
									@Override
									public void run() {
										Actor rest = Workspace.this.findActor("rest");
										if(rest!=null){
											rest.remove();
										}
										Cloud g4u = new Cloud("Отлично! Игра-шагалка готова! Ты молодчина!  ", ng);
										g4u.setName("goodForU");
										g4u.setPosition(400, getHeight()-g4u.getHeight());
										Workspace.this.addActor(g4u);									
									}
									
								}), delay(5), Actions.run(new Runnable() {
									
									@Override
									public void run() {
										Actor g4u =  Workspace.this.findActor("goodForU");
										g4u.remove();
										
										Cloud cloud = new Cloud("ВСЁ! Теперь в шагалку можно играть.\n Выбери себе на игровом поле\n героя и передвинь его на СТАРТ", ng, new Cloud.Callback() {
											
											@Override
											public void run(Cloud cloud) {
												fire(new MovedToStartEvent());
											}
										});
										cloud.setName("canPlay");
										cloud.setPosition(390, getHeight()-cloud.getHeight());
										
	
										
										Workspace.this.addActor(cloud);		
										
										ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool));
										ng.getContext().setProperty(ContextProperty.MOVE_TO_START, Boolean.TRUE);

									}
									
								})));
	
								
								
								startHasEverBeenClickedOnPopup = true;
							}
						}
					}
					else{
						//world.getSelected().unfill();

					}
					event.setBubbles(false);

				}
				if(event instanceof LogicLabelClickEvent){
					event.setBubbles(false);
					
					LogicLabelClickEvent lEvent = (LogicLabelClickEvent) event;
					String c = lEvent.getChar();
					
					Character activeLetter = (Character) ng.getContext().getProperty(ContextProperty.ACTIVE_LETTER);
					
					if(c.contains(activeLetter.toString()) && ng.getContext().getProperty(ContextProperty.BETWEEN_CELLARS)==null){
						lEvent.getContext().get(activeLetter).setStyle(ng.getManager().getSkin().get("small-"+world.getTitle()+"-hit", LabelStyle.class));
						world.step();
					}
					else{
						errorSnd.play();
					}
				}		
				
				if(event instanceof SelectColorEvent){
					SelectColorEvent colorEvent = (SelectColorEvent) event;
					
					//if(ng.getContext().getProperty(ContextProperty.HALT)!=null){
					setSelectedColor(colorEvent.getColor());
					//}
					event.setBubbles(false);


				}
				
				if(event instanceof GameEndEvent){
					event.setBubbles(false);
					
					ng.getContext().setProperty(ContextProperty.GAME_END, Boolean.TRUE);
					ng.getContext().setProperty(ContextProperty.HALT, null);
					ng.getContext().setProperty(ContextProperty.INGAME, null);
					
					
					
					Actor sb = world.getStartButton();					
					Image refresh = new Image(ng.getManager().getAtlas().findRegion("refresh"));
					refresh.setName("refresh");
					refresh.setSize(130, 143);
					refresh.setPosition(sb.getX(), sb.getY());
					sb.remove();
					
					world.addActor(refresh);
					refresh.addListener(new InputListener(){
						public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
							world.refresh();
							ng.getContext().setProperty(ContextProperty.POPUP, null);		
							ng.getContext().setProperty(ContextProperty.GAME_END, null);
							
							return true;
						}
					});
					
					if(!saveTool.hasEverBeenClickedOnPopup()&&showPopupPref){
						saveTool.setPopup("Можешь прислать  сохраненные игровые поля на\n страничку НИКОЛЬ И ЕЁ ДРУЗЬЯ  в  фейсбук.", 100, new PopupGroup(saveTool), 0);
						world.findActor("refresh").setVisible(false);
						//ng.getContext().setProperty(ContextProperty.POPUP, null);
						
						//Preferences prefs = Gdx.app.getPreferences(NetaGame.class.getName());
						//prefs.putBoolean("showPopup", false);
						//prefs.flush();
					}
					

					
				}
				
				if(event instanceof TrashButtonEvent){
					event.setBubbles(false);

					if(world.getSelected()!=null && ng.getContext().getProperty(ContextProperty.HALT)==null){
						final Actor actor = world.getSelected();
						actor.addAction(deleteFigureAction(actor));
						beingRemoved.add(world.getSelected());
						
						AbstractFigure candidate = findLast();
						setSelectedFigure(candidate);	

					}
				}
				
				
				if(event instanceof RotationEvent){
					event.setBubbles(false);
					RotationEvent re = (RotationEvent)event;

					if(world.getSelected()!=null&&ng.getContext().getProperty(ContextProperty.HALT)==null){
						world.getSelected().rotate(((RotationEvent)event).getDegrees());
					}
					
					if(showPopupPref && re.getId()==1){
						ng.getContext().setProperty(ContextProperty.POPUP,new PopupGroup(figuresTool,basketTool,rTool, lyricsTool));
						addAction(sequence(delay(10), Actions.run(new Runnable() {
							
							@Override
							public void run() {

								Cloud cloud = new Cloud("Когда будешь готов, нажми на стрелку", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										
										
										flowerTool.setPopup("Выбери буквы для игры", 0, new PopupGroup(flowerTool), 0);
										ptGroup.onShow(null);//bTool.setChecked(true);
										
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							
								
							}
						})));
					}
				}
				
				if(event instanceof UnfillEvent && ng.getContext().getProperty(ContextProperty.HALT)==null){
					event.setBubbles(false);
					if(world.getSelected()!=null){
						world.getSelected().unfill();
					}
				}
				
				if(event instanceof RequestFocusEvent){
					event.setBubbles(false);

					if(world!=null){
						world.setFocus();
					}
				}
				
				if(event instanceof InstructionCloseEvent){
					if(!settingTool.hasEverBeenClickedOnPopup()){
						settingTool.setPopup("Спроси взрослого, который будет тебе помогать,\nвнимательно ли он прочитал короткую записку\nдля взрослых.",160,new PopupGroup(settingTool), 0);
					}

				}
				
				if(event instanceof AdultsCloseEvent){
					if(!worldsTool.hasEverBeenClickedOnPopup()){
						worldsTool.setPopup("Выбери игровое поле",0 ,new PopupGroup(worldsTool), 0);
					}
				}
				 
				if(event instanceof QuestionEvent){													
					ptGroup.onShow(null);					 					 
				}
				
				if(event instanceof BrushToolChangeEvent){
				//	BrushToolChangeEvent btcEvent = (BrushToolChangeEvent)event;
				//	 world.setColorizing(btcEvent.isChecked()) ;
					 boolean fls = false;
					 if(notifiedOfColoredFigures &&!firstTouchAfterColoredFiguresNotification&&showPopupPref &&fls){
						 
						 ng.getContext().setProperty(ContextProperty.POPUP,  new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool));
						 firstTouchAfterColoredFiguresNotification= true;
					 }
					 
				}
				
				if(event instanceof PassportEvent){
					event.setBubbles(false);

					form.update(passport);
					world.drawPassport(passport);
					Workspace.this.ptGroup.onShow(null);

					if(!lyricsTool.hasEverBeenClickedOnPopup() && findActor("popupLyrics")==null){
						lyricsTool.setPopup("Выбери подходящие стихи ", 0, new PopupGroup(lyricsTool), 0);
					}
				}
				
				
				if(event instanceof ZIndexEvent && ng.getContext().getProperty(ContextProperty.HALT)==null){
					event.setBubbles(false);

					ZIndexEvent zevent = (ZIndexEvent)event;
					world.removeActor(zevent.getAbove());
					world.addActorAfter(zevent.getBelow(),zevent.getAbove());
					
					return true;
				}
				
				if(event instanceof FiguresShowEvent){
					event.setBubbles(false);
					FiguresShowEvent fEvent = (FiguresShowEvent)event;

					boolean no = false;
					if(no ){						
						if(fEvent.getTimesFired()==1){
							Cloud cloud = new Cloud("Когда будешь готов, нажми на стрелку", ng, new Cloud.Callback() {
								
								@Override
								public void run(Cloud cloud) {
									
									
									flowerTool.setPopup("Выбери буквы для игры", 0, new PopupGroup(flowerTool), 0);
									ptGroup.onShow(null);
									//bTool.setChecked(true);
									
									cloud.remove();
								}
							});
							cloud.setName("whenReady");
							cloud.setPosition(395, getHeight()-cloud.getHeight());
							addActor(cloud);
						}
						else if(fEvent.getTimesFired()==2){
							Actor wr = findActor("whenReady");
							if(wr==null){
								Cloud cloud = new Cloud("Когда будешь готов, нажми на стрелку", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										paletteTool.setPopup("Выбери цвета  и раскрась  свою картину", 0, new PopupGroup(figuresTool,basketTool, lyricsTool, paletteTool,bTool, rTool ), 0);
										ptGroup.onShow(null);
	
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							}
						}

/*						if( fEvent.getTimesFired()==1){
							flowerTool.setPopup("Выбери буквы для игры", 0, new PopupGroup(flowerTool), 0);
						}
						else if( fEvent.getTimesFired()==2){
							paletteTool.setPopup("Выбери цвета  и раскрась  свою картину", 0, new PopupGroup(figuresTool,basketTool, lyricsTool, paletteTool,bTool, rTool ), 0);
							bTool.setChecked(true);
						}*/
					}
					
					
					
/*					if(!paletteTool.hasEverBeenClickedOnPopup()&& findActor("popupPalette")==null){
						//popup("Ты можешь выбрать цвет и\n раскрасить фигуры", 550, 80, "popupPalette");
						paletteTool.setPopup("Ты можешь выбрать цвет и\n раскрасить фигуры", 00, new PopupGroup(figuresTool,basketTool, paletteTool, rTool, bTool, uTool), 0);
					}*/
				}
				
				if(event instanceof PaletteShowEvent){
					event.setBubbles(false);
					
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& !checkBrushTool){
						bTool.setChecked(true);
						checkBrushTool = true;
						ng.getContext().notifyListeners();
					}
				}
				
				if(event instanceof SavePanelHideEvent){
					event.setBubbles(false);
					SavePanelHideEvent sEvent = (SavePanelHideEvent)event;
					
					
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& sEvent.getTimesFired()==1){
						Cloud c = new Cloud("А теперь ты можешь сделать\n игру с другими полями\nили поиграть ещё раз на этом поле\n(для этого нажми кнопку \"заново\" и выбери полоску с буквами)", ng);
						c.setName("restartCloud");
						Actor rf = world.findActor("refresh");
						if(rf!=null)
						rf.setVisible(true);
						ng.getContext().setProperty(ContextProperty.POPUP, null);		

						c.setPosition(390, getHeight()-c.getHeight());
						world.addActor(c);
					}
				}
				
				if(event instanceof KubikClickedEvent){
					event.setBubbles(false);
					KubikClickedEvent sEvent = (KubikClickedEvent)event;
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& sEvent.getTimesFired()==1){/*
						Cloud c = new Cloud("К", ng);
						c.setName("restartCloud");
						Actor rf = world.findActor("refresh");
						if(rf!=null)
						rf.setVisible(true);
						ng.getContext().setProperty(ContextProperty.POPUP, null);		

						c.setPosition(390, getHeight()-c.getHeight());
						world.addActor(c);
					*/}
				}
				
				
				return true;
			}
		});
		
//////////////////////////////////////////////////
		//////// INSTRUCTION  //////////
/////////////////////////////////////////////////////
		final Group instructionPanel = new InstructionPanel(ng);
		instructionPanel.setPosition(20,150);		
		instructionPanel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), instructionPanel);
		qTool.setPanel(instructionPanel);	

		
//////////////////////////////////////////////////
			//////// NIKOL LETTER //////////
/////////////////////////////////////////////////////		
		
		boolean showNikolLetter= Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showNikole", true);
		if(showNikolLetter){
			final TextureRegion nikolTexture = ng.getManager().getMiscAtlas().findRegion("nikol");
			final TextureRegion fbTex = ng.getManager().getAtlas().findRegion("FB");
			Window nikWin = new Window("", ng.getManager().getSkin());
			nikWin.setClip(false);
			Image fb= new Image(fbTex);
			fb.setPosition(857, 213);
			fb.setSize(30, 30);				
			
			fb.addListener(new ClickListener(){
				public void clicked (InputEvent event, float x, float y) {
					ng.getNative().openWebPage("https://www.facebook.com/groups/nikoldruzya/");
				}
			});
			
			nikWin.setBackground(new TextureRegionDrawable(nikolTexture));
		//	Image nikolActor = new Image(nikolTexture);
		//	nikWin.addActor(nikolActor);
			nikWin.addActor(fb);
			nikWin.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					if(x>445 && x<518 && y>162 && y<231){
						event.getTarget().remove();
						//instructionPanel.setVisible(true);
						ng.getContext().setProperty(ContextProperty.HALT, null);
						ng.getContext().setProperty(ContextProperty.PREPARED, Boolean.TRUE);
						if(!qTool.hasEverBeenClickedOnPopup()){
							qTool.setPopup("Сначала изучи инструкцию", 50, new PopupGroup(qTool), 00, true);
						}
					}
					return false;
				}
			});
			
			nikWin.pack();
			//1360, 490-64
			nikWin.setBounds(20,150, 980, 500);		
			this.addActorAfter(Workspace.this.findActor(bottomActorName), nikWin);
		}


		///////////////////////////////
		//////// SETTINGS EDITOR //////////
		//////////////////////////////
		final SettingsEditor settingsEditorPanel = new SettingsEditor(ng);		
		settingsEditorPanel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), settingsEditorPanel);
		settingsPanel.setSettingsEditor(settingsEditorPanel);
		Util.center(settingsEditorPanel);
	
		///////////////////////////////
		//////// AUTHORS //////////
		//////////////////////////////
		final Group athorsPanel = new AuthorsPanel(ng);
		athorsPanel.setPosition(20,150);		
		athorsPanel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), athorsPanel);
		settingsPanel.setAuthorsPanel(athorsPanel);;	
		

		final Group adults2Panel = new AdultsPanel(ng);
		adults2Panel.setPosition(20,150);		
		adults2Panel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), adults2Panel);
		settingsPanel.setAdultsPanel(adults2Panel);			
		
		///////////////////////////////
		//////// HINT PANEL //////////
		//////////////////////////////		
		final TextureRegion hintPanel =ng.getManager().getMiscAtlas().findRegion("hintPanel2");
		Image hintPanelAct = new Image(hintPanel);
		hintPanelAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				
				
				ng.getContext().setProperty(ContextProperty.PREPARED, Boolean.TRUE) ;
				ng.getContext().setProperty(ContextProperty.HALT, null) ;
				

			}
			return false;
			}
		});						
		hintPanelAct.setBounds(20,150, 980, 500);		
		hintPanelAct.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), hintPanelAct);
		settingsPanel.setHintPanel(hintPanelAct);
		

		
		ptGroup = new PanelToolGroup();
		ptGroup.addTool(worldsTool);
		ptGroup.addTool(lyricsTool);
		ptGroup.addTool(figuresTool);
		ptGroup.addTool(paletteTool);
		ptGroup.addTool(flowerTool);	
		ptGroup.addTool(saveTool);
		ptGroup.addTool(settingTool);
		ptGroup.addTool(shopTool);
		ptGroup.addTool(passportTool);
	
		
		
		registerStateListener(linkTool);
		registerStateListener(basketTool);
		registerStateListener(worldsTool);
		registerStateListener(flowerTool);
		registerStateListener(passportTool);
		registerStateListener(lyricsTool);
		registerStateListener(figuresTool);
		registerStateListener(paletteTool);
		registerStateListener(shopTool);
		registerStateListener(saveTool);
		registerStateListener(settingTool);		
		registerStateListener(qTool);
		registerStateListener(bTool);
		//registerStateListener(uTool);
		registerStateListener(rTool);
		registerStateListener(zTool);
		registerStateListener(kTool);
		registerStateListener(pinch2Zoom);

		
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(event.getTarget()==world){
					getStage().setKeyboardFocus(null);
					Workspace.this.setSelectedFigure(null);
					//Workspace.this.ptGroup.onShow(null);
					Gdx.input.setOnscreenKeyboardVisible(false);
				}

				
				return true;
				
				
			}
		});
		setSelectedColor(Color.WHITE);
		
/*		
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
			//	popup("Hello\nI'm popup!",x,y).addAction(sequence(delay(4), Actions.removeActor()));
				return false;
			}
		});*/
		
		
		errorSnd = ng.getManager().getSound("error");
		
		//popup("Выбери игровое поле", 100, 80, "popupChooseWorld").setVisible(false);
		
		if(!showNikolLetter){
			ng.getContext().setProperty(ContextProperty.HALT, null);
			ng.getContext().setProperty(ContextProperty.PREPARED, Boolean.TRUE);
			if(!qTool.hasEverBeenClickedOnPopup()){
				qTool.setPopup("Прочитай инструкцию", 50, new PopupGroup(qTool), 00, true);
			}
		}
		
		
/*		Cloud cl = new Cloud("Привет, морриконе", ng);
		addActor(cl);
		cl.setPosition(300, 300);*/
		
		this.addAction(Actions.forever(Actions.sequence(new TemporalAction(5) {
			
			@Override
			protected void update(float percent) {
				// TODO Auto-generated method stub
				var = percent;
			}
		}, new TemporalAction(5) {
			
			@Override
			protected void update(float percent) {
				// TODO Auto-generated method stub
				var = 1- percent;
			}
		})));
		

		

	}

	public float getVar(){
		return var;
	}
	
	float var;
	
	Sound errorSnd;
	
	public World getWorld(){
		return world;
	}

	void setSelectedColor(Color clr){
		selectedColor = clr.cpy();
		bTool.setColor(selectedColor);
	}
	
	private void registerStateListener(ContextListener listener){
		ng.getContext().registerListener(listener);
	}
	
	
	private World world;

	
	void setSelectedFigure(AbstractFigure figure){
		if(world.getSelected()==figure){
			return;
		}
		
		if(world.getSelected()!=null){
			world.getSelected().animateVacant();
		}
		

		
		world.setSelectedActor(figure);
		zTool.setSelectedFigure(figure);
		
		if(world.getSelected()!=null){
			world.getSelected().animateSelected();
		}
		
		
			
	}
	
	AbstractFigure findLast(){
		AbstractFigure figure = null;
		for(Actor actor : world.getChildren()){
			if(actor instanceof AbstractFigure && !beingRemoved.contains(actor)){
				figure = (AbstractFigure) actor;
			}
		}
		return figure;
	}
	
	private List<AbstractFigure> beingRemoved = new LinkedList<AbstractFigure>();
	
    public void saveScreenshot2(FileHandle fh) {
        Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        PixmapIO.writePNG(fh, pixmap);

 
}
	


    
    public Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
            Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1);
            
            final Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
            ByteBuffer pixels = pixmap.getPixels();
            Gdx.gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);
            
            final int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            if (flipY) {
                    final int numBytesPerLine = w * 4;
                    for (int i = 0; i < h; i++) {
                            pixels.position((h - i - 1) * numBytesPerLine);
                            pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
                    }
                    pixels.clear();
                    pixels.put(lines);
            } else {
                    pixels.clear();
                    pixels.get(lines);
            }
            
            return pixmap;
    }
    
    
    public void populateWorldFactories(final NetaGame ng){
    	worldFactories.put(Integer.valueOf(1), new WorldFactory() {
			
			@Override
			public World create() {
				return new AntWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(2), new WorldFactory() {
			
			@Override
			public World create() {
				return new SpiderWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(3), new WorldFactory() {
			
			@Override
			public World create() {
				return new PitonWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(4), new WorldFactory() {
			
			@Override
			public World create() {
				return new TigerWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    }
    
    
    
     Action deleteFigureAction(final Actor figure){
    	return sequence(Actions.parallel(Actions.scaleTo(0, 0, .6f), Actions.rotateBy(-360, .6f)) ,Actions.removeActor(), Actions.run(new Runnable(){

			@Override
			public void run() {
				beingRemoved.remove(figure)	;							
			}
			
		}));
    }
     
     
    static float abandonTime = 1;
     static Action  abandonWorldAction(Actor world){
    	return sequence(Actions.parallel(Actions.scaleTo(0, 0, abandonTime), Actions.rotateBy(-500, abandonTime), Actions.moveBy(1000, 0, abandonTime)) ,Actions.removeActor());
     }
    

 
 	
 	void initContext(Context context){
 		context.setProperty(ContextProperty.HALT, Boolean.TRUE);
 	}


}

