package com.me.neta;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
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
import com.badlogic.gdx.Input.Keys;
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
import com.me.neta.ExitCloud.Option;
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
import com.me.neta.worlds.AntWorld;
import com.me.neta.worlds.PitonWorld;
import com.me.neta.worlds.SpiderWorld;
import com.me.neta.worlds.TigerWorld;

public class Workspace extends Group  {



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
	Map<String, WorldFactory> worldFactories = new HashMap<String, WorldFactory>();
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
	 
	 
	 static class Tips{
			boolean startHasEverBeenClickedOnPopup;
			boolean checkBrushTool;
			boolean makeIllustrationTipShown;
			boolean makeRoamsTipShown;
			boolean gameStartedTip;
			int figureDropsTotal;
			boolean welcomeToPaintingTipShown;
			boolean notifiedOfColoredFigures;
			boolean firstTouchAfterColoredFiguresNotification;
			boolean lyricsToolClicked;
			boolean whenIllustrationTipShown;

			boolean rotationTipShown;
			boolean sendToFacebookTipShown;
			boolean kubicTipShown;
			boolean playAgainTip;
	 }
	 
	  static class OneWayTips{
			public boolean readInstructionTip;
			boolean chooseWorldTip;
			boolean adultReadTip;
			boolean passportDone;
	 }
	 
	 Tips tips = new Tips();
	  OneWayTips owTips = new OneWayTips();
	 
	 public Tips getTips(){
		 return tips;
	 }

	 public OneWayTips getOneWayTips(){
		 return owTips;
	 }
		 
	public Workspace(NetaGame ng, float x, float y, float width, float height){
		this.ng = ng;
		setBounds(x, y, width, height);
		setName("workspace");

	}
	
	public void initialize(){
		
		initContext(ng.getContext());
		
		
		final boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
		tips.startHasEverBeenClickedOnPopup= !showPopupPref;
		populateWorldFactories(ng);
		passport = new Passport();
		pinch2Zoom = new Pinch2ZoomListener2();
		pinch2Zoom.setZMin(.125f);
		pinch2Zoom.setCanPan(true);
		pinch2Zoom.setCanZoom(true);
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
		gameshopPanel.setPosition(500, panelHeight);
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
		topButtons.setBackground(ng.getManager().getSkin().getDrawable("frameTight"));
		topButtons.setName("topButtons");
		 rTool = new RotateTool(ng);
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
					String choice = cEvent.getChoice();

					if(world.getTitle().equals(choice)){
						world.createCellars();
						//world.addLyrics(lyricsEvent.getChoice());
						
						//ng.getContext().setProperty(ContextProperty.LETTERS, new Object());
						ng.getContext().setProperty(ContextProperty.CELLARS, Boolean.TRUE);
						lyricsPanel.addAction(sequence(fadeOut(0.4f), visible(false)));
						
						
						if(showPopupPref&&!tips.makeRoamsTipShown){
					
							figuresTool.setPopup("?????????????? ?????????????? ???????????????? ???? ????????????????.\n???????????? ???? ?????????????????? ?????????????? ??????????????!", 0, new PopupGroup(figuresTool,basketTool,rTool, lyricsTool, settingTool), 0);
							
							tips.makeRoamsTipShown = true;
							
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
				if(!tips.makeIllustrationTipShown&& showPopupPref){
					//popup("???? ???????????? ?????????????? ???????? ??\n ???????????????????? ????????????", 550, 80, "popupPalette");
					figuresTool.setPopup("???????????? ?????????????????????? ?? ????????????", 00, new PopupGroup(figuresTool,basketTool, lyricsTool, settingTool,rTool), 0);
					tips.makeIllustrationTipShown = true;
					
				}
					

				}
				
				if(event instanceof ForbiddenEvent){
					event.setBubbles(false);
					ForbiddenEvent fe = (ForbiddenEvent)event;
					addActor( new ExitCloud(fe.getMessage(), ng, 0, 0, getWidth(), getHeight(), new ExitCloud.Callback() {
						
						@Override
						public void doAction(ExitCloud instance, Option o) {
							if(o == Option.YES){
								ng.getNative().openWebPage(NetaGame.PAYED_RELEASE_URL);
							}
							instance.remove();
						}
					}));
					
				}
			
				
				if(event instanceof WorldSelectionEvent){
					for(Actor act : getChildren()){
						if(act.getName()!=null && act.getName().contains("popup")){
							act.addAction(Actions.removeActor());
						}
					}
					
					event.setBubbles(false);

					WorldSelectionEvent desktopEvent = (WorldSelectionEvent) event;
					
					String deskId = desktopEvent.getId();
					
					if(deskId!=null){
						Actor abandoningWorld = null;
						if(world!=null && world.getParent()!=null){
							 abandoningWorld = world;
							abandoningWorld.addAction(abandonWorldAction(abandoningWorld));
							
						}
						
						
						world = worldFactories.get(deskId).create();
						world.addListener(new InputListener(){
							public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
								if(Gdx.input.isKeyPressed(Keys.S)){
									saveAction();
								}
								return true;
							}

						});
						
						findActor("topButtons").setPosition(800, 700);		
						world.populate();
						world.setZIndex(1);
						
		

						worldsPanel.setVisible(false);
						addActorBefore(abandoningWorld!=null ? abandoningWorld : Workspace.this.findActor(bottomActorName), world);

						world.drawPassport(passport);
	
						if(showPopupPref){
							if(!owTips.passportDone){
								passportTool.setPopup("???????????? ???????? ??????, ??????????????\n ?? ?????? ???? ????????????", 00, new PopupGroup(passportTool), 0);
								owTips.passportDone = true;
							}else{
								lyricsTool.setPopup("???????????? ???????????????????? ?????????? ", 0, new PopupGroup(lyricsTool), 0);
							}
						}


					}
					
					ng.getContext().setProperty(ContextProperty.WORKING, Boolean.TRUE) ;
					ng.getContext().setProperty(ContextProperty.LETTERS, null);
					ng.getContext().setProperty(ContextProperty.CELLARS, null);
					ng.getContext().setProperty(ContextProperty.INGAME, null);
					ng.getContext().setProperty(ContextProperty.GAME_END, null);
					
					ng.getWorkspace().getPinch2Zoom().setCanPan(true);
					ng.getWorkspace().getPinch2Zoom().setCanZoom(true);

					lettersPanel.setVariants(world.getLetters());

					/*tips.makeRoamsTipShown = false;
					tips.whenIllustrationTipShown = false;
					tips.welcomeToPaintingTipShown = false;
					tips.lyricsToolClicked = false;*/
					tips = new Tips();
					
					
				}
				
				
				
				if(event instanceof FigureDropEvent){
					event.setBubbles(false);
					tips.figureDropsTotal++;

								
					FigureDropEvent dropEvent = (FigureDropEvent) event; 
					Actor letter= dropEvent.getActor();		
					
					Actor z = world.findActor("zactor");
					world.addActorBefore(z, letter);
							
					setSelectedFigure((AbstractFigure) letter);
					
					if(tips.figureDropsTotal>4 && !tips.rotationTipShown && showPopupPref){
						ptGroup.onShow(null);
						rTool.setPopup("???????????? ????????????????:\n?? ?????????????? ???????? ????????????\n ???? ???????????? ?????????????? ????????????", 30, new PopupGroup(rTool), 0, true);
						tips.rotationTipShown  = true;
					}
					
					if(tips.makeIllustrationTipShown && !tips.welcomeToPaintingTipShown){
						
						addAction(sequence(delay(10), Actions.run(new Runnable() {
							
							@Override
							public void run() {


								Cloud cloud = new Cloud("?????????? ?????????????????????? ?????????? ????????????, ?????????? ???? ??????????????", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										
										
										paletteTool.setPopup("???????????? ??????????  ?? ????????????????  ???????? ??????????????", 0, new PopupGroup(figuresTool,basketTool, lyricsTool, paletteTool,bTool, rTool , settingTool), 0);
										ptGroup.onShow(null);
										
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							
								
							
							}
						})));
						
						tips.welcomeToPaintingTipShown = true;
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
					if(!tips.gameStartedTip){
						
				
						Actor cp = Workspace.this.findActor("canPlay");
						if(cp!=null){
							cp.remove();
						}
						
						Cloud cl = new Cloud("?????????? ???? ???????????? ??????????. ???????? ????????????????!", ng);
						cl.setName("gameStartedCloud");
						cl.setPosition(400, getHeight()-cl.getHeight());
						Workspace.this.addActor(cl);	
						
						ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(world.getStartButton(), lyricsTool));
						tips.gameStartedTip = true;
					}
				}
				
				
				if(event instanceof SelectFigureEvent){
					setSelectedFigure((AbstractFigure) event.getTarget());	
					if(bTool.isChecked() && ng.getContext().getProperty(ContextProperty.HALT)==null){
						world.getSelected().fill(selectedColor);
						
						
						
						if(!tips.startHasEverBeenClickedOnPopup && showPopupPref){
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
							
							if(filledCount>1 &&  !tips.notifiedOfColoredFigures){
								ptGroup.onShow(null);
								
								
								Cloud payAtt = new Cloud("???????????? ????????????????:\n???????? ???????????? ???? ????????????????????, ???? ?????????? ??????????????.\n ???????????????????????? ???????????? ??????????, ?????????? ???????????? \"????????????????\" ???????????????? ????????????.\n???????????????????????? ???????????? ?????????????????? ?? ???????????????? ????????\n???????????? ?????????? ??????????????, ?????????? ???????????? \"??????????????\""
										, ng, new Cloud.Callback(){

											@Override
											public void run(Cloud cloud) {
												ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool, settingTool));
												ptGroup.onShow(null);	
												cloud.remove();
												
												Workspace.this.addAction(sequence(delay(1), Actions.run(new Runnable() {
													
													@Override
													public void run() {
														Cloud rest = new Cloud("???????????????? ???????????????????? ????????????.\n???? ?????????????? ???????? ????????????\n ???????? ???? ???????????? 8 ??????????!", ng);
														rest.setName("rest");
														rest.setPosition(400, Workspace.this.getHeight()- rest.getHeight());
														Workspace.this.addActor(rest);
														rest.addAction(sequence(delay(10), Actions.removeActor()));
													}
												})));

											}
											
										}, 40, 55, 30, 45);
								payAtt.setPosition(200, getHeight()-payAtt.getHeight());
								addActor(payAtt);
								payAtt.setName("payatt");
								payAtt.pack();
								ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(basketTool, rTool, bTool, settingTool));
							
								
								tips.notifiedOfColoredFigures = true;
							
							}
							
							if(sb!=null &&allFilled && filledCount > 7){
								Actor patt = findActor("payatt");
								if(patt!=null){
									patt.remove();
								}
								
								
								ptGroup.onShow(null);
								addAction(sequence(Actions.run(new Runnable() {
									
									@Override
									public void run() {
										Actor rest = Workspace.this.findActor("rest");
										if(rest!=null){
											rest.remove();
										}
										Cloud g4u = new Cloud("??????????????! ????????-?????????????? ????????????! ???? ??????????????????!  ", ng);
										g4u.setName("goodForU");
										g4u.setPosition(400, getHeight()-g4u.getHeight());
										Workspace.this.addActor(g4u);									
									}
									
								}), delay(5), Actions.run(new Runnable() {
									
									@Override
									public void run() {
										Actor g4u =  Workspace.this.findActor("goodForU");
										g4u.remove();
										
										Cloud cloud = new Cloud("??????! ???????????? ?? ?????????????? ?????????? ????????????.\n ???????????? ???????? ???? ?????????????? ????????\n ?????????? ?? ?????????????????? ?????? ???? ??????????", ng, new Cloud.Callback() {
											
											@Override
											public void run(Cloud cloud) {
												fire(new MovedToStartEvent());
											}
										});
										cloud.setName("canPlay");
										cloud.setPosition(390, getHeight()-cloud.getHeight());
										bTool.setChecked(false);
	
										
										Workspace.this.addActor(cloud);		
										
										ng.getContext().setProperty(ContextProperty.POPUP, new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool, settingTool));
										ng.getContext().setProperty(ContextProperty.MOVE_TO_START, Boolean.TRUE);

									}
									
								})));
	
								
								
								tips.startHasEverBeenClickedOnPopup = true;
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
					ng.getWorkspace().getPinch2Zoom().setCanPan(true);
					ng.getWorkspace().getPinch2Zoom().setCanZoom(true);
					
					
					
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

							
							if(showPopupPref){
							flowerTool.setPopup("???????????? ?????????? ?????? ????????", 0, new PopupGroup(flowerTool), 0);
							}
							ptGroup.onShow(null);//bTool.setChecked(true);
							tips = new Tips();
							tips.makeRoamsTipShown = true;
							tips.whenIllustrationTipShown = true;
							
							return true;
						}
					});
					
					if(!tips.sendToFacebookTipShown&&showPopupPref){
						saveTool.setPopup("???????????? ????????????????  ?????????????????????? ?????????????? ???????? ????\n ?????????????????? ???????????? ?? ???? ????????????  ??  ??????????????.", 100, new PopupGroup(saveTool), 0);
						world.findActor("refresh").setVisible(false);

						tips.sendToFacebookTipShown = true;
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
					
					if(showPopupPref && !tips.whenIllustrationTipShown ){
						ng.getContext().setProperty(ContextProperty.POPUP,new PopupGroup(figuresTool,basketTool,rTool, lyricsTool, settingTool));
						addAction(sequence(delay(10), Actions.run(new Runnable() {
							
							@Override
							public void run() {

								Cloud cloud = new Cloud("?????????? ?????????????? ?????????? ????????????, ?????????? ???? ??????????????", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										
										
										flowerTool.setPopup("???????????? ?????????? ?????? ????????", 0, new PopupGroup(flowerTool), 0);
										ptGroup.onShow(null);//bTool.setChecked(true);
										
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							
								
							}
						})));
						
						tips.whenIllustrationTipShown = true;
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
					if(!owTips.adultReadTip && showPopupPref){
						settingTool.setPopup("???????????? ??????????????????, ?????????????? ?????????? ???????? ????????????????,\n?????????????????????? ???? ???? ???????????????? ???????????????? ??????????????\n?????? ????????????????.",160,new PopupGroup(settingTool), 0);
						owTips.adultReadTip = true;
					}

				}
				
				if(event instanceof AdultsCloseEvent){
					if(!owTips.chooseWorldTip && showPopupPref){
						worldsTool.setPopup("???????????? ?????????????? ????????",0 ,new PopupGroup(worldsTool), 0);
						owTips.chooseWorldTip = true;
					}
				}
				 
				if(event instanceof QuestionEvent){													
					ptGroup.onShow(null);					 					 
				}
				
				if(event instanceof BrushToolChangeEvent){

					 boolean fls = false;
					 if(tips.notifiedOfColoredFigures &&!tips.firstTouchAfterColoredFiguresNotification&&showPopupPref &&fls){
						 
						 ng.getContext().setProperty(ContextProperty.POPUP,  new PopupGroup(figuresTool, basketTool,  paletteTool, lyricsTool, rTool, bTool));
						 tips.firstTouchAfterColoredFiguresNotification= true;
					 }
					 
				}
				
				if(event instanceof PassportEvent){
					event.setBubbles(false);

					form.update(passport);
					world.drawPassport(passport);
					Workspace.this.ptGroup.onShow(null);

					if(!tips.lyricsToolClicked && findActor("popupLyrics")==null && showPopupPref){
						lyricsTool.setPopup("???????????? ???????????????????? ?????????? ", 0, new PopupGroup(lyricsTool), 0);
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
							Cloud cloud = new Cloud("?????????? ???????????? ??????????, ?????????? ???? ??????????????", ng, new Cloud.Callback() {
								
								@Override
								public void run(Cloud cloud) {
									
									
									flowerTool.setPopup("???????????? ?????????? ?????? ????????", 0, new PopupGroup(flowerTool), 0);
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
								Cloud cloud = new Cloud("?????????? ???????????? ??????????, ?????????? ???? ??????????????", ng, new Cloud.Callback() {
									
									@Override
									public void run(Cloud cloud) {
										paletteTool.setPopup("???????????? ??????????  ?? ????????????????  ???????? ??????????????", 0, new PopupGroup(figuresTool,basketTool, lyricsTool, paletteTool,bTool, rTool, settingTool ), 0);
										ptGroup.onShow(null);
	
										cloud.remove();
									}
								});
								cloud.setName("whenReady");
								cloud.setPosition(395, getHeight()-cloud.getHeight());
								addActor(cloud);
							}
						}

					}
					
					

				}
				
				if(event instanceof PaletteShowEvent){
					event.setBubbles(false);
					
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& !tips.checkBrushTool){
						bTool.setChecked(true);
						tips.checkBrushTool = true;
						ng.getContext().notifyListeners();
					}
				}
				
				if(event instanceof SavePanelHideEvent){
					event.setBubbles(false);
					
					
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& !tips.playAgainTip){
						Cloud c = new Cloud("?? ???????????? ???? ???????????? ??????????????\n ???????? ?? ?????????????? ????????????\n?????? ???????????????? ?????? ?????? ???? ???????? ????????\n(?????? ?????????? ?????????? ???????????? \"????????????\" ?? ???????????? ?????????????? ?? ??????????????)", ng,  null, 40, 44, 40, 44);
						c.setName("restartCloud");
						Actor rf = world.findActor("refresh");
						if(rf!=null)
						rf.setVisible(true);
						ng.getContext().setProperty(ContextProperty.POPUP, null);		

						c.setPosition(390, getHeight()-c.getHeight());
						world.addActor(c);
						tips.playAgainTip = true;
					}
				}
				
				if(event instanceof KubikClickedEvent){
					event.setBubbles(false);
					KubikClickedEvent sEvent = (KubikClickedEvent)event;
					
					boolean showPopupPref = Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
					if(showPopupPref&& sEvent.getTimesFired()==1){/*
						Cloud c = new Cloud("??", ng);
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
						if(!owTips.readInstructionTip && showPopupPref){
							qTool.setPopup("?????????????? ?????????? ????????????????????", 50, new PopupGroup(qTool), 00, true);
							owTips.readInstructionTip = true;
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
		

		
		errorSnd = ng.getManager().getSound("error");
		
		
		if(!showNikolLetter){
			ng.getContext().setProperty(ContextProperty.HALT, null);
			ng.getContext().setProperty(ContextProperty.PREPARED, Boolean.TRUE);
			if(!owTips.readInstructionTip && showPopupPref){
				qTool.setPopup("???????????????? ????????????????????", 50, new PopupGroup(qTool), 00, true);
				owTips.readInstructionTip = true;
			}
		}
		
		
	
		
		
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
				var = 1 - percent;
			}
		})));
		
		getStage().addListener(new InputListener(){
			/** Called when a key goes down. When true is returned, the event is {@link Event#handle() handled}. */
			public boolean keyDown (InputEvent event, int keycode) {
				 if(keycode == Keys.BACK){
					 	Workspace.this.sureWantLeave();
					 	return true;
			        }
				
				return false;
			}
		});
		
		linkTool.setEnabled(true);
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
    	worldFactories.put("ant", new WorldFactory() {
			
			@Override
			public World create() {
				return new AntWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put("spider", new WorldFactory() {
			
			@Override
			public World create() {
				return new SpiderWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put("piton", new WorldFactory() {
			
			@Override
			public World create() {
				return new PitonWorld(ng,Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put("tiger", new WorldFactory() {
			
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


 	public void sureWantLeave(){

 			final Actor actor = findActor("exitCloud");
 			if(actor!=null){
 				return;
 			}
 		
 		
	 		final ExitCloud ex = new ExitCloud("?????????? ???????????? ???????????",ng, 0, 0, getWidth(), getHeight(), new ExitCloud.Callback() {
				
				@Override
				public void doAction(ExitCloud instance, Option o) {
					if(o == Option.YES){
						Gdx.app.exit();
					}
					else{
						instance.remove();
					}
				}
			});
	 		addActor(ex);
	 		ex.setName("exitCloud");
 		
/*	 		
	 		Cloud c = new Cloud("?????????? ?????? ???? Google Play!", ng, new Cloud.Callback() {
				
				@Override
				public void run(Cloud cloud) {
					ng.getNative().openWebPage("https://www.google.com/");
					cloud.remove();
				}
			});
	 		ex.addActor(c);
	 		Util.center(c);	 		
	 		c.setPosition(c.getX()+300, c.getY()+300);*/
 	}
 	
 	
    void saveAction(){


		addAction(delay(.1f,run(new Runnable(){

			@Override
			public void run() {				
				if (Gdx.files.isExternalStorageAvailable()) {
				try {
					final Pixmap pixmap = getScreenshot(0, 0,
							Gdx.graphics.getWidth(),
							Gdx.graphics.getHeight(), true);
					MessageHelper.message(ng, "????????????????????, ??????????????????...");	
					
					addAction(delay(.1f, Actions.run(new Runnable(){public void run() {	
					FileHandle handle = Gdx.files.external(String
							.format("picture%d.png",
									System.currentTimeMillis()));

					
					//for(int i = 0; i<50; i++){
						PixmapIO.writePNG(handle, pixmap);
				//	}
					
					String text ="?????????????????????? ?????????????????? "+  (NetaGame.debug ? new StringBuilder("[????????:").append(handle.file().getAbsolutePath()).append(']').toString() : "");  
					MessageHelper.notify(ng, text);
					
					}})));
				} catch (Exception ex) {
					MessageHelper.error(ng, "????????????! ?????????????????????? ???? ??????????????????!", ex);
				}
			} else {
				MessageHelper.error(ng, "???????????????? ?????????????????? ????????????????????!", null);

			}	}})));
		
					
		

    }
}

