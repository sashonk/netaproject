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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

import com.badlogic.gdx.scenes.scene2d.ui.Table;



import com.me.neta.Context.ContextProperty;
import com.me.neta.events.*;
import com.me.neta.figures.AbstractFigure;
import com.me.neta.tools.AbstractTool;
import com.me.neta.tools.BasketTool;
import com.me.neta.tools.BrushTool;
import com.me.neta.tools.FlowerTool;
import com.me.neta.tools.ShopTool;
import com.me.neta.tools.DesktopsTool;
import com.me.neta.tools.FiguresTool;
import com.me.neta.tools.LetterTool;
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

public class Workspace extends Group{
	

	ZIndexTool zTool;
	BrushTool bTool;
	RotateTool rTool;
	QuestionTool qTool;
	
	
	static final float pad = 15;
	PanelToolGroup ptGroup;
	private Pinch2ZoomListener2 pinch2Zoom;
	Passport passport;
	Map<Integer, WorldFactory> worldFactories = new HashMap<Integer, WorldFactory>();
	NetaGame ng;
	String bottomActorName = "bottomActor";
	Table toolbarTable;
	private Color selectedColor ;

	
	public Workspace(NetaGame ng, float x, float y, float width, float height){
		this.ng = ng;
		setBounds(x, y, width, height);
		setName("workspace");

	}
	
	public void initialize(){
		
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
		
		toolbarTable.debug();
		toolbarTable.debugTable();
		final ExitTool linkTool = new ExitTool(ng);
		toolbarTable.add(linkTool).padRight(pad).padLeft(pad);
		
//////////////////////////////////////////////////////////////////		
	////////////////// 	 TOOLS  /////////////////////////
/////////////////////////////////////////////////////////////////
		final BasketTool basketTool = new BasketTool(ng); 
		basketTool.setSize(68,78);
		toolbarTable.add(basketTool).padRight(pad).padLeft(pad);
						
		final DesktopsTool fieldsTool = new DesktopsTool(ng);
		final DesktopsPanel fieldsPanel = new DesktopsPanel(ng,600, 350);
		fieldsPanel.setPosition(35, 75);
		fieldsPanel.setVisible(false);
		Color c = fieldsPanel.getColor();
		fieldsPanel.setColor(c.r, c.g, c.b, 0);
		addActor(fieldsPanel);
		fieldsTool.setPanel(fieldsPanel);
		toolbarTable.add(fieldsTool).padRight(pad).padLeft(pad);
		
		
		final LyricsTool lyricsTool = new LyricsTool(ng);
		final LyricsPanel lyricsPanel = new LyricsPanel(ng,800, 400);
		lyricsPanel.setPosition(105, 75);
		lyricsPanel.setColor(c);
		lyricsPanel.setVisible(false);
		addActor(lyricsPanel);
		lyricsTool.setPanel(lyricsPanel);
				
		toolbarTable.add(lyricsTool).padRight(pad).padLeft(pad);
		final LetterTool letterTool = new LetterTool(ng);
		final PassportForm form = new PassportForm(ng);
		form.setPosition(490-133, 75);
		form.setColor(c);
		form.setVisible(false);
		addActor(form);
		letterTool.setPanel(form);	
		toolbarTable.add(letterTool).padRight(pad).padLeft(pad);
		
		
		final FiguresTool figuresTool = new FiguresTool(ng);
		toolbarTable.add(figuresTool).padRight(pad).padLeft(pad);
		FiguresPanel figPanel = new FiguresPanel(ng);
		figPanel.setColor(c);
		figPanel.setVisible(false);
		figPanel.setWidth(550);
		figPanel.setHeight(450);
		figPanel.setPosition(370, 75);
		figuresTool.setPanel(figPanel);
		this.addActor(figPanel);
		
		final FlowerTool flowerTool = new FlowerTool(ng);
		
		LettersPanel lettersPanel = new LettersPanel(ng);
		lettersPanel.setVisible(false);
		flowerTool.setPanel(lettersPanel);
		this.addActor(lettersPanel);
		toolbarTable.add(flowerTool).padRight(pad).padLeft(pad);
		Util.center(lettersPanel);

		
		final ColorTool paletteTool = new ColorTool(ng);
		toolbarTable.add(paletteTool).padRight(pad).padLeft(pad);
		ColorPanel palette= new ColorPanel(ng);
		paletteTool.setPanel(palette);
		palette.setColor(c);
		palette.setVisible(false);
		palette.setWidth(560);
		palette.setHeight(460);
		palette.setPosition(340, 75);
		paletteTool.setPanel(palette);
		this.addActor(palette);
		
		final ShopTool shopTool = new ShopTool(ng);
		final Image gameshopPanel = new Image(ng.getManager().getMiscAtlas().findRegion("gameshop"));
		gameshopPanel.setVisible(false);
		gameshopPanel.setBounds(70, 75, 878, 358);
		this.addActor(gameshopPanel);
		shopTool.setPanel(gameshopPanel);
		toolbarTable.add(shopTool).padRight(pad).padLeft(pad);
		
		final SaveTool saveTool = new SaveTool(ng);
		toolbarTable.add(saveTool).padRight(pad).padLeft(pad);
		SavePanel savePanel = new SavePanel(ng);
		saveTool.setPanel(palette);
		savePanel.setColor(c);
		savePanel.setVisible(false);
		savePanel.setWidth(260);
		savePanel.setHeight(300);
		savePanel.setPosition(750, 75);
		saveTool.setPanel(savePanel);
		this.addActor(savePanel);
		
		final SettingsTool settingTool = new SettingsTool(ng);
		toolbarTable.add(settingTool).padRight(pad).padLeft(pad);
		SettingsPanel settingsPanel = new SettingsPanel(ng);
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
		
		
		Table topButtons = new Table();
		 rTool = new RotateTool(ng);
		 zTool = new ZIndexTool(ng);
		 bTool = new BrushTool(ng);
		qTool = new QuestionTool(ng);
		
		float topPad = 2;
		float topPadRight ;
		float topPadLeft ;
		
		
		topButtons.add(rTool).padRight(topPad);
		topButtons.add(bTool).padRight(topPad);
		topButtons.add(zTool).padRight(topPad);
		topButtons.add(qTool).padRight(topPad);
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
				//	world.setPinch2ZoomEnabled(false);
					pinch2Zoom.setCanPan(false);
					event.setBubbles(false);

				}
				if(event instanceof DragStopEvent){
					//world.setPinch2ZoomEnabled(true);
					pinch2Zoom.setCanPan(true);
					event.setBubbles(false);

				}
				
				if(event instanceof LyricsIconEvent){
					event.setBubbles(false);

					
					LyricsIconEvent lyricsEvent = (LyricsIconEvent)event;

					world.addLyrics(lyricsEvent.getChoice());
					
					lyricsPanel.addAction(sequence(fadeOut(0.4f), visible(false)));
				}
				
				if(event instanceof DesktopIconEvent){
					event.setBubbles(false);

					System.out.println("DesktopChoseEvent::handle="+((DesktopIconEvent)event).getId());
					DesktopIconEvent desktopEvent = (DesktopIconEvent) event;
					
					int deskId = desktopEvent.getId();
					
					if(deskId>=0){
						Actor abandoningWorld = null;
						if(world!=null && world.getParent()!=null){
							 abandoningWorld = world;
							 //abandoningWorld.setOrigin(abandoningWorld.getWidth()/2, abandoningWorld.getHeight()/2);
							abandoningWorld.addAction(abandonWorldAction(abandoningWorld));
						}
						
						world = worldFactories.get(desktopEvent.getId()).create();
						world.populate();
						world.setZIndex(1);
						
		

						fieldsPanel.setVisible(false);
						addActorBefore(abandoningWorld!=null ? abandoningWorld : Workspace.this.findActor(bottomActorName), world);

						world.setId(desktopEvent.getId());
						//desktop.addAction(Actions.sequence(Actions.fadeIn(.2f)));
/*						world.addListener(new InputListener(){
							public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
								getStage().setKeyboardFocus(null);
								Workspace.this.setSelectedFigure(null);
								Workspace.this.ptGroup.onShow(null);
								Gdx.input.setOnscreenKeyboardVisible(false);
								

								
								return true;
								
								
							}
						});*/
						world.drawPassport(passport);
						ng.getContext().notifyListeners();
					}
					
					ng.getContext().setProperty(ContextProperty.WORKING, true) ;
					
				}
				
				
				
				if(event instanceof LetterDropEvent){
					event.setBubbles(false);


				
					
					System.out.println("LetterDropEvent::handle");
					LetterDropEvent dropEvent = (LetterDropEvent) event;
					Actor letter= dropEvent.getActor();		
					
					Actor z = world.findActor("zactor");
					world.addActorBefore(z, letter);
							
					setSelectedFigure((AbstractFigure) letter);
				}
				
				
				if(event instanceof SelectFigureEvent){
					setSelectedFigure((AbstractFigure) event.getTarget());	
					if(bTool.checked()){
						world.getSelected().setColor(selectedColor);
					}
					event.setBubbles(false);

				}
				
				
				if(event instanceof SelectColorEvent){
					SelectColorEvent colorEvent = (SelectColorEvent) event;
					setSelectedColor(colorEvent.getColor());
					event.setBubbles(false);

				}
				
				if(event instanceof TrashButtonEvent){
					event.setBubbles(false);

					System.out.println("TrashButtonEvent::handle");
					if(world.getSelected()!=null){
						final Actor actor = world.getSelected();
						actor.addAction(deleteFigureAction(actor));
						beingRemoved.add(world.getSelected());
						
						AbstractFigure candidate = findLast();
						setSelectedFigure(candidate);	

					}
				}
				
				
				if(event instanceof RotationEvent){
					event.setBubbles(false);

					System.out.println("RotationEvent::handle");
					if(world.getSelected()!=null){
						world.getSelected().rotate(((RotationEvent)event).getDegrees());
					}
				}
				
				if(event instanceof RequestFocusEvent){
					event.setBubbles(false);

					if(world!=null){
						world.setFocus();
					}
				}
				 
				if(event instanceof QuestionEvent){													
					ptGroup.onShow(null);					 					 
				}
/*				
				if(event instanceof BrushToolChangeEvent){
					BrushToolChangeEvent btcEvent = (BrushToolChangeEvent)event;
					 world.setColorizing(btcEvent.isChecked()) ;
				}*/
				
				if(event instanceof PassportEvent){
					event.setBubbles(false);

					form.update(passport);
					world.drawPassport(passport);
					Workspace.this.ptGroup.onShow(null);

				}
				
				
				if(event instanceof ZIndexEvent){
					event.setBubbles(false);

					ZIndexEvent zevent = (ZIndexEvent)event;
					world.removeActor(zevent.getAbove());
					world.addActorAfter(zevent.getBelow(),zevent.getAbove());
					
					return true;
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
/*		final TextureRegion instructScreen = ng.getManager().getMiscAtlas().findRegion("instruct");
		final Image instructActor = new Image(instructScreen);
		instructActor.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(x>817 && x<889 && y>101 && y<169){
					event.getTarget().setVisible(false);
					
					ng.getContext().setProperty(ContextProperty.PREPARED, true) ;
					ng.getContext().setProperty(ContextProperty.HALT, false) ;
					
					fire(new ContextChangeEvent());

					
				}
				return false;
			}
		});	

		instructActor.setBounds(20,150, 980, 500);		
		instructActor.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), instructActor);
		qTool.setPanel(instructActor);*/
		
//////////////////////////////////////////////////
			//////// NIKOL LETTER //////////
/////////////////////////////////////////////////////		
		final TextureRegion nikolTexture = ng.getManager().getMiscAtlas().findRegion("nikol");
		Image nikolActor = new Image(nikolTexture);
		nikolActor.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(x>445 && x<518 && y>162 && y<231){
					event.getTarget().remove();
					instructionPanel.setVisible(true);
				}
				return false;
			}
		});

		nikolActor.setBounds(20,150, 980, 500);		
		this.addActorAfter(Workspace.this.findActor(bottomActorName), nikolActor);
		

		
		///////////////////////////////
		//////// AUTHORS //////////
		//////////////////////////////
		final Group athorsPanel = new AuthorsPanel(ng);
		athorsPanel.setPosition(20,150);		
		athorsPanel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), athorsPanel);
		settingsPanel.setAuthorsPanel(athorsPanel);;	
		
/*		final Group authorsPanel = new Group();
		authorsPanel.setBounds(20,150, 980, 500);		
		authorsPanel.setVisible(false);

		final TextureRegion authors = ng.getManager().getMiscAtlas().findRegion("authorsPanel");
		Image authorsAct = new Image(authors);
		authorsPanel.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getListenerActor().setVisible(false);
				
				
				ng.getContext().setProperty(ContextProperty.PREPARED, true) ;
				ng.getContext().setProperty(ContextProperty.HALT, false) ;
				fire(new ContextChangeEvent());
			}
				return false;
			}
		});						
		authorsAct.setBounds(0, 0, authorsPanel.getWidth(), authorsPanel.getHeight());		
		authorsPanel.addActor(authorsAct);		
		authorsPanel.addListener(new MetricListener());
		this.addActorAfter(Workspace.this.findActor(bottomActorName), authorsPanel);
		settingsPanel.setAuthorsPanel(authorsPanel);*/



		
		///////////////////////////////
		//////// ADULTS PANEL //////////
		//////////////////////////////		
/*		final TextureRegion adultsPanel = ng.getManager().getMiscAtlas().findRegion("adultsPanel");
		Image adultsAct = new Image(adultsPanel);
		adultsAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				
				
				ng.getContext().setProperty(ContextProperty.PREPARED, true) ;
				ng.getContext().setProperty(ContextProperty.HALT, false) ;
				fire(new ContextChangeEvent());
			}
			return false;
			}
		});						
		adultsAct.setBounds(20,150, 980, 500);		
		adultsAct.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), adultsAct);
		settingsPanel.setAdultsPanel(adultsAct);*/
		final Group adults2Panel = new AdultsPanel(ng);
		adults2Panel.setPosition(20,150);		
		adults2Panel.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), adults2Panel);
		settingsPanel.setAdultsPanel(adults2Panel);			
		
		///////////////////////////////
		//////// HINT PANEL //////////
		//////////////////////////////		
		final TextureRegion hintPanel =ng.getManager().getMiscAtlas().findRegion("hintPanel");
		Image hintPanelAct = new Image(hintPanel);
		hintPanelAct.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			if(x>817 && x<889 && y>101 && y<169){
				event.getTarget().setVisible(false);
				
				
				ng.getContext().setProperty(ContextProperty.PREPARED, true) ;
				ng.getContext().setProperty(ContextProperty.HALT, false) ;
			}
			return false;
			}
		});						
		hintPanelAct.setBounds(20,150, 980, 500);		
		hintPanelAct.setVisible(false);
		this.addActorAfter(Workspace.this.findActor(bottomActorName), hintPanelAct);
		settingsPanel.setHintPanel(hintPanelAct);
		

		
		ptGroup = new PanelToolGroup();
		ptGroup.addTool(fieldsTool);
		ptGroup.addTool(lyricsTool);
		ptGroup.addTool(figuresTool);
		ptGroup.addTool(paletteTool);
		ptGroup.addTool(flowerTool);	
		ptGroup.addTool(saveTool);
		ptGroup.addTool(settingTool);
		ptGroup.addTool(shopTool);
		ptGroup.addTool(letterTool);
	
		
		
		registerStateListener(linkTool);
		registerStateListener(basketTool);
		registerStateListener(fieldsTool);
		registerStateListener(flowerTool);
		registerStateListener(letterTool);
		registerStateListener(lyricsTool);
		registerStateListener(figuresTool);
		registerStateListener(paletteTool);
		registerStateListener(shopTool);
		registerStateListener(saveTool);
		registerStateListener(settingTool);		
		registerStateListener(qTool);
		registerStateListener(bTool);
		registerStateListener(rTool);
		registerStateListener(zTool);
		
		

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
		
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(event.getTarget()==world){
					getStage().setKeyboardFocus(null);
					Workspace.this.setSelectedFigure(null);
					Workspace.this.ptGroup.onShow(null);
					Gdx.input.setOnscreenKeyboardVisible(false);
				}

				
				return true;
				
				
			}
		});
		setSelectedColor(Color.WHITE);
	}
	
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
    
}

