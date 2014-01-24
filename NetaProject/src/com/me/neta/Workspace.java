package com.me.neta;



import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;



import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;

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


import com.me.neta.events.*;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.AbstractTool;
import com.me.neta.tools.BasketTool;
import com.me.neta.tools.ShopTool;
import com.me.neta.tools.DesktopsTool;
import com.me.neta.tools.FiguresTool;
import com.me.neta.tools.LetterTool;
import com.me.neta.tools.ExitTool;
import com.me.neta.tools.LyricsTool;
import com.me.neta.tools.ColorTool;
import com.me.neta.tools.Question;
import com.me.neta.tools.RotateTool;
import com.me.neta.tools.SaveTool;
import com.me.neta.tools.SettingsTool;
import com.me.neta.tools.ZIndexTool;
import com.me.neta.util.WorkHelper;
import com.me.neta.util.WorkspaceState;
import com.me.neta.util.WorkspaceStateListener;
import com.me.neta.worlds.AntWorld;
import com.me.neta.worlds.PitonWorld;
import com.me.neta.worlds.SpiderWorld;
import com.me.neta.worlds.TigerWorld;

public class Workspace extends Group{
	

	
	Label mousePositionLabel ;
	
	static final float pad = 15;

	
	final PanelToolGroup ptGroup;
	private List<WorkspaceStateListener> listeners = new LinkedList<WorkspaceStateListener>();
	public WorkspaceState state;
	private Pinch2ZoomListener2 pinch2Zoom;
	Passport passport;
	Map<Integer, WorldFactory> worldFactories = new HashMap<Integer, WorldFactory>();
	
	public Workspace(float x, float y, float width, float height){
		
		
		populateWorldFactories();
		passport = new Passport();
		pinch2Zoom = new Pinch2ZoomListener2();
		pinch2Zoom.setZMin(.125f);
		this.addCaptureListener(pinch2Zoom);
		

		
		setName("workspace");
		setBounds(x, y, width, height);


		Skin skin = TextureManager.get().getSkin();		
		mousePositionLabel = new Label("", skin);
	

		this.addActor(mousePositionLabel);


		mousePositionLabel.setPosition(10, 500);
		
		
		
		Image img = new Image(TextureManager.get().getBottomPanelTexture());

		
		final Table toolbarTable = new Table();
		

		toolbarTable.debug();
		toolbarTable.debugTable();
		final ExitTool linkTool = new ExitTool();
		toolbarTable.add(linkTool).padRight(pad).padLeft(pad);
		
//////////////////////////////////////////////////////////////////		
	////////////////// 	 TOOLS  /////////////////////////
/////////////////////////////////////////////////////////////////
		final BasketTool basketTool = new BasketTool(); 
		basketTool.setSize(68,78);
		toolbarTable.add(basketTool).padRight(pad).padLeft(pad);
						
		final DesktopsTool fieldsTool = new DesktopsTool();
		final DesktopsPanel fieldsPanel = new DesktopsPanel(600, 350);
		fieldsPanel.setPosition(35, 75);
		fieldsPanel.setVisible(false);
		Color c = fieldsPanel.getColor();
		fieldsPanel.setColor(c.r, c.g, c.b, 0);
		addActor(fieldsPanel);
		fieldsTool.setPanel(fieldsPanel);
		toolbarTable.add(fieldsTool).padRight(pad).padLeft(pad);
		
		
		final LyricsTool lyricsTool = new LyricsTool();
		final LyricsPanel lyricsPanel = new LyricsPanel(800, 400);
		lyricsPanel.setPosition(105, 75);
		lyricsPanel.setColor(c);
		lyricsPanel.setVisible(false);
		addActor(lyricsPanel);
		lyricsTool.setPanel(lyricsPanel);
				
		toolbarTable.add(lyricsTool).padRight(pad).padLeft(pad);
		final LetterTool letterTool = new LetterTool();
		final PassportForm form = new PassportForm();
		form.setPosition(490-133, 75);
		form.setColor(c);
		form.setVisible(false);
		addActor(form);
		//WorkHelper.center(form);
		letterTool.setPanel(form);	
		toolbarTable.add(letterTool).padRight(pad).padLeft(pad);
		
		
		final FiguresTool figuresTool = new FiguresTool();
		toolbarTable.add(figuresTool).padRight(pad).padLeft(pad);
		FiguresPanel figPanel = new FiguresPanel(this);
		figPanel.setColor(c);
		figPanel.setVisible(false);
		figPanel.setWidth(550);
		figPanel.setHeight(450);
		figPanel.setPosition(370, 75);
		figuresTool.setPanel(figPanel);
		this.addActor(figPanel);
		
		
		
		final ColorTool paletteTool = new ColorTool();
		toolbarTable.add(paletteTool).padRight(pad).padLeft(pad);
		ColorPanel palette= new ColorPanel();
		paletteTool.setPanel(palette);
		palette.setColor(c);
		palette.setVisible(false);
		palette.setWidth(560);
		palette.setHeight(460);
		palette.setPosition(340, 75);
		paletteTool.setPanel(palette);
		this.addActor(palette);
		
		final ShopTool shopTool = new ShopTool();
		final Image gameshopPanel = new Image(TextureManager.get().getMiscAtlas().findRegion("gameshop"));
		gameshopPanel.setVisible(false);
		gameshopPanel.setBounds(70, 75, 878, 358);
		this.addActor(gameshopPanel);
		shopTool.setPanel(gameshopPanel);
		toolbarTable.add(shopTool).padRight(pad).padLeft(pad);
		
		final SaveTool saveTool = new SaveTool();
		toolbarTable.add(saveTool).padRight(pad).padLeft(pad);
		SavePanel savePanel = new SavePanel();
		saveTool.setPanel(palette);
		savePanel.setColor(c);
		savePanel.setVisible(false);
		savePanel.setWidth(260);
		savePanel.setHeight(300);
		savePanel.setPosition(750, 75);
		saveTool.setPanel(savePanel);
		this.addActor(savePanel);
		
		final SettingsTool settingTool = new SettingsTool();
		toolbarTable.add(settingTool).padRight(pad).padLeft(pad);
		SettingsPanel settingsPanel = new SettingsPanel();
		settingTool.setPanel(palette);
		settingsPanel.setColor(c);
		settingsPanel.setVisible(false);
		settingsPanel.setWidth(300);
		settingsPanel.setHeight(250);
		settingsPanel.setPosition(730, 75);
		settingTool.setPanel(settingsPanel);
		this.addActor(settingsPanel);
				
		addActor(toolbarTable);
		addActor(img);
		toolbarTable.setZIndex(99);
		
		
		final Question q = new Question();
		q.setBounds(960, 700, 40, 40);
		addActor(q);

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
				//	world.setPinch2ZoomEnabled(false);
					pinch2Zoom.setCanPan(false);
				}
				if(event instanceof DragStopEvent){
					//world.setPinch2ZoomEnabled(true);
					pinch2Zoom.setCanPan(true);

				}
				
				if(event instanceof LyricsIconEvent){

					
					LyricsIconEvent lyricsEvent = (LyricsIconEvent)event;

					world.addLyrics(lyricsEvent.getChoice());
					
					lyricsPanel.addAction(sequence(fadeOut(0.4f), visible(false)));
				}
				
				if(event instanceof DesktopIconEvent){
					System.out.println("DesktopChoseEvent::handle="+((DesktopIconEvent)event).getId());
					DesktopIconEvent desktopEvent = (DesktopIconEvent) event;
					
					int deskId = desktopEvent.getId();
					
					if(deskId>=0){
						Actor abandoningWorld = null;
						if(world!=null && world.getParent()!=null){
							 abandoningWorld = world;
							 abandoningWorld.setOrigin(abandoningWorld.getWidth()/2, abandoningWorld.getHeight()/2);
							abandoningWorld.addAction(sequence(Actions.parallel(Actions.scaleTo(0, 0, 1f), Actions.rotateBy(-500, 1f), Actions.moveBy(1000, 0, 1f)) ,Actions.removeActor()));
						}
						
						world = worldFactories.get(desktopEvent.getId()).create();
						world.populate();
						world.setZIndex(1);
						
		

						fieldsPanel.setVisible(false);
						addActorBefore(abandoningWorld!=null ? abandoningWorld :mousePositionLabel, world);

						world.setId(desktopEvent.getId());
						//desktop.addAction(Actions.sequence(Actions.fadeIn(.2f)));
						world.addListener(new InputListener(){
							public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
								getStage().setKeyboardFocus(null);
								Workspace.this.setSelectedFigure(null);
								Workspace.this.ptGroup.onShow(null);
								Gdx.input.setOnscreenKeyboardVisible(false);
								

								
								return true;
								
								
							}
						});
						world.drawPassport(passport);				
					}
					
					fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
	
				}
				
				
				
				if(event instanceof LetterDropEvent){


				
					
					System.out.println("LetterDropEvent::handle");
					LetterDropEvent dropEvent = (LetterDropEvent) event;
					Actor letter= dropEvent.getActor();		
					
					Actor z = world.findActor("zactor");
					world.addActorBefore(z, letter);
							
					setSelectedFigure((AbstractFigure) letter);
				}
				
				
				if(event instanceof SelectFigureEvent){
					setSelectedFigure((AbstractFigure) event.getTarget());	
					if(world.isColorizing()){
						world.getSelected().setColor(world.getSelectedColor());
					}
				}
				
				
				if(event instanceof SelectColorEvent){
					System.out.println("SelectColorEvent::handle");
					SelectColorEvent colorEvent = (SelectColorEvent) event;
					world.setSelectedColor( colorEvent.getColor());
					if(world!=null){
						world.tintBrush(world.getSelectedColor());
					}
				}
				
				if(event instanceof TrashButtonEvent){
					System.out.println("TrashButtonEvent::handle");
					if(world.getSelected()!=null){
						final Actor actor = world.getSelected();
						actor.addAction(sequence(Actions.parallel(Actions.scaleTo(0, 0, .6f), Actions.rotateBy(-360, .6f)) ,Actions.removeActor(), Actions.run(new Runnable(){

							@Override
							public void run() {
								beingRemoved.remove(actor)	;							
							}
							
						})));
						beingRemoved.add(world.getSelected());
						
						AbstractFigure candidate = findLast();
						setSelectedFigure(candidate);	

					}
				}
				
				
				if(event instanceof RotationEvent){
					System.out.println("RotationEvent::handle");
					if(world.getSelected()!=null){
						world.getSelected().rotate(((RotationEvent)event).getDegrees());
					}
				}
				
				if(event instanceof RequestFocusEvent){
					if(world!=null){
						world.setFocus();
					}
				}
				 
				if(event instanceof WorkspaceStateEvent){
					
			
					
					WorkspaceStateEvent wsEvent = (WorkspaceStateEvent) event;
					 for(WorkspaceStateListener listener : listeners){
						 listener.stateChanged(state, wsEvent.getState());
					 }
					 
					 state =wsEvent.getState();
					 

				}
				
				if(event instanceof BrushToolChangeEvent){
					BrushToolChangeEvent btcEvent = (BrushToolChangeEvent)event;
					 world.setColorizing(btcEvent.isChecked()) ;
				}
				
				if(event instanceof PassportEvent){
					form.update(passport);
					world.drawPassport(passport);
					Workspace.this.ptGroup.onShow(null);

				}
				
				
				event.setBubbles(false);
				return true;
			}
		});
		
//////////////////////////////////////////////////
		//////// INSTRUCTION SCREEN //////////
/////////////////////////////////////////////////////
		
		final TextureRegion instructScreen = TextureManager.get().getMiscAtlas().findRegion("instruct");
		final Image instructActor = new Image(instructScreen);
		instructActor.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(x>817 && x<889 && y>101 && y<169){
					event.getTarget().setVisible(false);
					
					if(world==null){
						fire(new WorkspaceStateEvent(WorkspaceState.PREPARED));
					}
					else{
						fire(new WorkspaceStateEvent(WorkspaceState.WORKING));						
					}

					
				}
				return false;
			}
		});	

		instructActor.setBounds(20,150, 980, 500);		
		instructActor.setVisible(false);
		this.addActorAfter(mousePositionLabel, instructActor);
		q.setPanel(instructActor);
		
//////////////////////////////////////////////////
			//////// NIKOL LETTER //////////
/////////////////////////////////////////////////////		
		final TextureRegion nikolTexture = TextureManager.get().getMiscAtlas().findRegion("nikol");
		Image nikolActor = new Image(nikolTexture);
		nikolActor.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(x>445 && x<518 && y>162 && y<231){
					event.getTarget().remove();
					instructActor.setVisible(true);
				}
				return false;
			}
		});

		nikolActor.setBounds(20,150, 980, 500);		
		this.addActorAfter(mousePositionLabel, nikolActor);
		

		
		///////////////////////////////
		//////// AUTHORS //////////
		//////////////////////////////		
		final TextureRegion authors = TextureManager.get().getMiscAtlas().findRegion("authorsPanel");
		Image authorsAct = new Image(authors);
		authorsAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				if(world==null){
				fire(new WorkspaceStateEvent(WorkspaceState.PREPARED));
				}
				else{
					fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
				}
			}
			return false;
			}
		});						
		authorsAct.setBounds(20,150, 980, 500);		
		authorsAct.setVisible(false);
		this.addActorAfter(mousePositionLabel, authorsAct);
		settingsPanel.setAuthorsPanel(authorsAct);


		
		///////////////////////////////
		//////// ADULTS PANEL //////////
		//////////////////////////////		
		final TextureRegion adultsPanel = TextureManager.get().getMiscAtlas().findRegion("adultsPanel");
		Image adultsAct = new Image(adultsPanel);
		adultsAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				if(world==null){
					fire(new WorkspaceStateEvent(WorkspaceState.PREPARED));
				}
				else{
					fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
				}
			}
			return false;
			}
		});						
		adultsAct.setBounds(20,150, 980, 500);		
		adultsAct.setVisible(false);
		this.addActorAfter(mousePositionLabel, adultsAct);
		settingsPanel.setAdultsPanel(adultsAct);
		
		
		
		
		///////////////////////////////
		//////// HINT PANEL //////////
		//////////////////////////////		
		final TextureRegion hintPanel = TextureManager.get().getMiscAtlas().findRegion("hintPanel");
		Image hintPanelAct = new Image(hintPanel);
		hintPanelAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				if(world==null){
					fire(new WorkspaceStateEvent(WorkspaceState.PREPARED));
				}
				else{
					fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
				}
			}
			return false;
			}
		});						
		hintPanelAct.setBounds(20,150, 980, 500);		
		hintPanelAct.setVisible(false);
		this.addActorAfter(mousePositionLabel, hintPanelAct);
		settingsPanel.setHintPanel(hintPanelAct);
		

		
		ptGroup = new PanelToolGroup();
		ptGroup.addTool(fieldsTool);
		ptGroup.addTool(lyricsTool);
		ptGroup.addTool(figuresTool);
		ptGroup.addTool(paletteTool);
		ptGroup.addTool(saveTool);
		ptGroup.addTool(settingTool);
		ptGroup.addTool(shopTool);
		ptGroup.addTool(letterTool);
		
		
		registerStateListener(linkTool);
		registerStateListener(basketTool);
		registerStateListener(fieldsTool);
		registerStateListener(letterTool);
		registerStateListener(lyricsTool);
		registerStateListener(figuresTool);
		registerStateListener(paletteTool);
		registerStateListener(shopTool);
		registerStateListener(saveTool);
		registerStateListener(settingTool);		
		registerStateListener(q);
		
		fire(new WorkspaceStateEvent(WorkspaceState.INTRO));
		Image splash = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/zastavka.jpg")), 0,0,1024, 600));
		splash.setBounds(0,0,1024, 768);
		splash.addAction(sequence(delay(3),alpha(0, 3), visible(false), Actions.removeActor()));
		splash.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.getTarget().remove();
				return false;
			}
		});
		addActor(splash);
		

		
	}

	private void registerStateListener(WorkspaceStateListener listener){
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}
	
	
	private World world;


	
	void createTigerWorld(){
	

		
		world.setName("tiger");
	}
	
	void createPitonWorld(){
	

		
		Image flower1 = new Image(TextureManager.get().getAtlas().findRegion("ZVET4"));
		flower1.setBounds(200, 115,57,57);
		world.addActor(flower1);
/*				
		Image bird = new Image(TextureManager.get().getAtlas().findRegion("POPUGAI"));
		bird.setBounds(10, 50,43,30);*/
		
		Actor zactor = new Actor();
		zactor.setName("zactor");
		world.addActor(zactor);
		
		Piton piton = new Piton();
		piton.setPosition(460, 130);
		piton.setZIndex(10);
		world.addActor(piton);
		
		Hero bird = new Hero("POPUGAI");
		bird.setBounds(200+10, 115+50,43,30);
		bird.setZIndex(9);
		world.addActor(bird);
		
		world.setName("piton");
	}
	

	public AbstractFigure getSelectedFigure(){
		return world.getSelected();
	}
	
	
	
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
      //  BufferedI
        
        //PixmapIO.writePNG(file, pixmap);
       
/*      try {
       
   
               BufferedImage image = new BufferedImage(pixmap.getWidth(), pixmap.getHeight(), BufferedImage.TYPE_INT_ARGB);
               for(int i = 0; i < pixmap.getHeight(); i++){
                for(int j = 0; j<pixmap.getWidth(); j++){
               int c= pixmap.getPixel(j, i);
               
               int r = (c >> 24) & 0xff;
               int g = (c >> 16) & 0xff;
               int b = (c >> 8) & 0xff;
               int a = c & 0xff;
               
               int argb =0;
               argb |= (a << 24) & 0xff000000;
               argb |= (r << 16) & 0x00ff0000;
               argb |= (g << 8)  & 0x0000ff00;
               argb |= b  & 0x000000ff;
               
               image.setRGB(j, i, argb);
                }
               }
               
         
               
               if(file.exists()){
                file.delete();
               }
               
               
               
               
               ImageIO.write(image, "png", file);
               
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }*/
       
      
     // manager.get
      System.out.println("format 2 done");
 
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
    
    
    public void populateWorldFactories(){
    	worldFactories.put(Integer.valueOf(1), new WorldFactory() {
			
			@Override
			public World create() {
				return new AntWorld(Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(2), new WorldFactory() {
			
			@Override
			public World create() {
				return new SpiderWorld(Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(3), new WorldFactory() {
			
			@Override
			public World create() {
				return new PitonWorld(Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    	
    	worldFactories.put(Integer.valueOf(4), new WorldFactory() {
			
			@Override
			public World create() {
				return new TigerWorld(Workspace.this.getWidth(), Workspace.this.getHeight());
			}
		});
    }
}

