package com.me.neta;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.StringBuilder;
import com.me.neta.figures.Letter;

public class TextureManager {
	
	static String CHARS	= "\u0401\u0402\u0403\u0404\u0405\u0406\u0407\u0408\u0409\u040A\u040B\u040C" +
			"\u040D\u040E\u040F\u0410\u0411\u0412\u0413\u0414\u0415\u0416\u0417\u0418\u0419\u041A\u041B\u041C" +
			"\u041D\u041E\u041F\u0420\u0421\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042A\u042B\u042C" +
			"\u042D\u042E\u042F\u0430\u0431\u0432\u0433\u0434\u0435\u0436\u0437\u0438\u0439\u043A\u043B\u043C" +
			"\u043D\u043E\u043F\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044A\u044B\u044C" +
			"\u044D\u044E\u044F\u0450\u0451\u0452\u0453\u0454\u0455\u0456\u0457\u0458\u0459\u045A\u045B\u045C" +
			"\u045D\u045E\u045F\u0460\u0461\u0462\u0463\u0464\u0465\u0466\u0467\u0468\u0469\u046A\u046B\u046C" +
			"\u046D\u046E\u046F\u0470\u0471\u0472\u0473\u0474\u0475\u0476\u0477\u0478\u0479\u047A\u047B\u047C" +
			"\u047D\u047E\u047F\u0480\u0481\u0482\u0483\u0484\u0485\u0486\u0487\u0488\u0489\u048A\u048B\u048C" +
			"\u048D\u048E\u048F\u0490\u0491\u0492\u0493\u0494\u0495\u0496\u0497\u0498\u0499\u049A\u049B\u049C" + 
			"\u049D\u049E\u049F\u04A0\u04A1\u04A2\u04A3\u04A4\u04A5\u04A6\u04A7\u04A8\u04A9\u04AA\u04AB\u04AC" +
			"\u04AD\u04AE\u04AF\u04B0\u04B1\u04B2\u04B3\u04B4\u04B5\u04B6\u04B7\u04B8\u04B9\u04BA\u04BB\u04BC" +
			"\u04BD\u04BE\u04BF\u04C0\u04C1\u04C2\u04C3\u04C4\u04C5\u04C6\u04C7\u04C8\u04C9\u04CA\u04CB\u04CC" +
			"\u04CD\u04CE\u04CF\u04D0\u04D1\u04D2\u04D3\u04D4\u04D5\u04D6\u04D7\u04D8\u04D9\u04DA\u04DB\u04DC" +
			"\u04DD\u04DE\u04DF\u04E0\u04E1\u04E2\u04E3\u04E4\u04E5\u04E6\u04E7\u04E8\u04E9\u04EA\u04EB\u04EC" +
			"\u04ED\u04EE\u04EF\u04F0\u04F1\u04F2\u04F3\u04F4\u04F5\u04F6\u04F7\u04F8\u04F9\u04FA\u04FB\u04FC" +
			"\u04FD\u04FE\u04FF ";
	
	
	
	public static final String DEFAULT_CHARS = FreeTypeFontGenerator.DEFAULT_CHARS.concat(CHARS);
	

	
	//Map<Character, Sound> soundMap = new HashMap<Character, Sound>();
	//Map<String, Music> levinSound = new HashMap<String, Music>();
	//Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public Sound letterSound(Character c){
		return manager.get(new StringBuilder("data/sound/").append(Letter.translit.get(Character.valueOf(c))).append(".mp3").toString(), Sound.class);
	}
	
	public Music getMusic(String name){
		return manager.get(new StringBuilder("data/sound/").append(name).append(".ogg").toString());
	}
	
	public Sound getSound(String name){
		return manager.get(String.format("data/sound/%s.ogg", name));
	}
	
	AssetManager manager;
	
	public void finishLoading(){
		manager.finishLoading();
	}
	
	public boolean update(){
		return manager.update();
	}
	
	public boolean update(int millis){
		return manager.update(millis);
	}

	public String getProgressAsString(){	
		return "Загрузка...";
		//BigDecimal bd = BigDecimal.valueOf(100* manager.getProgress()).setScale(0, RoundingMode.HALF_UP);
		//return new StringBuilder("Загрузка ").append(bd.toString()).append("%").toString();
		
		
	}
	
	public AssetManager getAssetManager(){
		return manager;
	}
	
	public void loadResources(){
		Gdx.app.debug(this.getClass().getName(), "begin loading resources");
	
		manager = new AssetManager();

		manager.load("data/sound/error.ogg", Sound.class);

		for(Character c :  Letter.characterToIdMap.keySet()){
			manager.load(new StringBuilder("data/sound/").append(Letter.translit.get(Character.valueOf(c))).append(".mp3").toString(), Sound.class);
		}
		
		String[] worldNames = {"ant", "spider", "piton", "tiger"};
		
		for(String name : worldNames){
			manager.load((new StringBuilder("data/sound/").append(name).append(".ogg").toString()), Music.class);
		}
	
		manager.load("data/main.pack", TextureAtlas.class);
		manager.load("data/misc/misc.pack", TextureAtlas.class);

		manager.load(("data/field1.jpg"), Texture.class);
		manager.load(("data/field2.jpg"), Texture.class);
		manager.load(("data/field3.jpg"), Texture.class);
		manager.load(("data/field4.jpg"), Texture.class);
		
		
		manager.load("data/nikol.jpg", Texture.class);
		manager.load("data/instruct.jpg", Texture.class);
		manager.load("data/np.png", Texture.class);
		manager.load("data/errnp.png", Texture.class);
		

		
	}
	
	public void init(){
		Gdx.app.debug(this.getClass().getName(), "initializing");


		
		skin = new Skin();

///////////////////////////////////////////////
///////////	 WONDERLAND 	/////////////
//////////////////////////////////////////////
FreeTypeFontGenerator wondGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/wonderland.ttf"));
BitmapFont wond36 = wondGenerator.generateFont(36,DEFAULT_CHARS, false);

wondGenerator.dispose();

/*	skin.add("wond12", wond12);
	skin.add("wond14", wond14);
	skin.add("wond16", wond16);
	skin.add("wond18", wond18);
	skin.add("wond20", wond20);
	skin.add("wond28", wond28);
	skin.add("wond36", wond36);*/
	skin.add("title", wond36);

///////////////////////////////////////////////
///////////	 CALIBRI 	/////////////
//////////////////////////////////////////////	
        FreeTypeFontGenerator generatorCalibri = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/arialbd.ttf"));
		BitmapFont calibri12 = generatorCalibri.generateFont(12,DEFAULT_CHARS, false);
		BitmapFont calibri14 = generatorCalibri.generateFont(14,DEFAULT_CHARS, false);
		BitmapFont calibri36 = generatorCalibri.generateFont(36,DEFAULT_CHARS, false);
		BitmapFont letterFont = generatorCalibri.generateFont(68,DEFAULT_CHARS, false);


		generatorCalibri.dispose();
		
	//	skin.add("small", calibri6);
	//	skin.add("calibri12", calibri12);
		skin.add("calibri14", calibri14);
		skin.add("default", calibri14);
	//	skin.add("calibri16", calibri16);
	//	skin.add("calibri18", calibri18);
		skin.add("36", calibri36);
		skin.add("letter", letterFont);


		 Texture nickolLetter = manager.get("data/nikol.jpg");
		 nikolLetterRegion = new TextureRegion(nickolLetter, 0, 0, 925, 433);
		 Texture instructScreen = manager.get("data/instruct.jpg");
		 instructScreenReg =  new TextureRegion(instructScreen, 0, 0, 925, 433);
	
		

		 Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
         pixmap.setColor(Color.WHITE);
         pixmap.fill();
         skin.add("white", new Texture(pixmap));
         
		 Pixmap pixmap2 = new Pixmap(1, 1, Format.RGBA8888);
		 pixmap2.setColor(Color.BLACK);
		 pixmap2.fill();
         skin.add("black", new Texture(pixmap2));
              
		 Pixmap pixmap3 = new Pixmap(1, 1, Format.RGBA8888);
		 pixmap3.setColor(Color.GRAY);
		 pixmap3.fill();
         skin.add("gray", new Texture(pixmap3));
         
		 Pixmap pixmap4 = new Pixmap(1, 1, Format.RGBA8888);
		 pixmap4.setColor(new Color(112/255f, 207/255f, 238/255f, 1));
		 pixmap4.fill();
         skin.add("blue", new Texture(pixmap4));
                  
         Pixmap p2 = new Pixmap(4, 4, Format.RGBA8888);
         p2.setColor(Color.BLACK);
         p2.fill();
         skin.add("black4", new Texture(p2));
         
         Pixmap p8 = new Pixmap(8, 8, Format.RGBA8888);
         p8.setColor(Color.BLACK);
         p8.drawRectangle(0, 0, p8.getWidth(), p8.getHeight());
         skin.add("line8black", new Texture(p8));
         
         Pixmap debugTable = new Pixmap(4,4, Format.RGBA8888);
         debugTable.setColor(Color.BLACK);
         debugTable.drawRectangle(0, 0, 4, 4);
         skin.add("debugTable", new NinePatch(new Texture(debugTable), 1, 1, 1, 1));
         
         
         TextFieldStyle blueCalibri = new TextFieldStyle();
         blueCalibri.fontColor = new Color(.4f, .57f, .74f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         blueCalibri.font = skin.getFont("default");
       //  skin.add("blue_calibri", blueCalibri);         
         
         
         TextFieldStyle lightBlueCalibri = new TextFieldStyle();
         lightBlueCalibri.fontColor = new Color(.47f, .87f, .93f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         lightBlueCalibri.font = skin.getFont("default");
       //  skin.add("light_blue_calibri", lightBlueCalibri);   
         
         
         TextFieldStyle violetCalibri = new TextFieldStyle();
         violetCalibri.fontColor = new Color(.22f, .08f, .45f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         violetCalibri.font = skin.getFont("default");
      //   skin.add("violet_calibri", violetCalibri);   
         

         
         
         LabelStyle labelStyle = new LabelStyle();
         labelStyle.font = skin.getFont("default");
         labelStyle.fontColor = Color.BLACK;
         skin.add("default", labelStyle);
         
         
         TextureAtlas atlas = manager.get("data/main.pack");
         skin.add("panelNP", new NinePatch(atlas.findRegion("panel"), 52, 52 , 0,0));
         skin.add("system", new NinePatch(atlas.findRegion("npsystem"), 8, 8, 8, 8));
         skin.add("error", new NinePatch(atlas.findRegion("nperror"), 8, 8, 8, 8));

         
         NinePatchDrawable npd = new NinePatchDrawable(skin.getPatch("system"));
         skin.add("npd", npd, Drawable.class);
     
         NinePatchDrawable npdError = new NinePatchDrawable(skin.getPatch("error"));
         skin.add("npdError", npd, Drawable.class);
         
       
 		
 		
        TextButtonStyle tbStyleSystem = new TextButtonStyle();
        tbStyleSystem.font = calibri12;
        tbStyleSystem.fontColor = Color.BLACK;
        tbStyleSystem.up = npd;
        skin.add("system", tbStyleSystem);

        
        TextButtonStyle tbStyleError = new TextButtonStyle();
        tbStyleError.font = calibri12;
        tbStyleError.fontColor = Color.BLACK;
        tbStyleError.up = npdError;
        skin.add("error", tbStyleError);
        
 		
 		LabelStyle lStyleSystem = new LabelStyle();
 		lStyleSystem.font = calibri12;
 		lStyleSystem.fontColor = Color.BLACK;
 		//lStyleSystem.background = skin.getDrawable("npd");
 		skin.add("system", lStyleSystem);
 		
        TextFieldStyle tfStyleSystem = new TextFieldStyle();
        tfStyleSystem.font = calibri12;
        tfStyleSystem.fontColor = Color.BLACK;
       // tfStyleSystem.background = skin.getDrawable("gray");
        tfStyleSystem.selection = skin.getDrawable("blue");
        tfStyleSystem.cursor = skin.getDrawable("black");	 	
        tfStyleSystem.messageFont = calibri12;
        tfStyleSystem.messageFontColor = Color.GRAY;
        skin.add("system", tfStyleSystem);
 		
   	
 		
        TextFieldStyle tfStyleSystem2 = new TextFieldStyle();
        tfStyleSystem2.font = calibri14;
        tfStyleSystem2.fontColor = Color.BLACK;
       // tfStyleSystem.background = skin.getDrawable("gray");
        tfStyleSystem2.selection = skin.getDrawable("blue");
        tfStyleSystem2.cursor = skin.getDrawable("black");	 	
        tfStyleSystem2.messageFont = calibri14;
        tfStyleSystem2.messageFontColor = Color.GRAY;
        skin.add("system2", tfStyleSystem2);      
        skin.add("default", tfStyleSystem2);      

 		
       LabelStyle lStyleSystem2 = new LabelStyle();
        lStyleSystem2.font = calibri14;
        lStyleSystem2.fontColor = Color.BLACK;
        skin.add("system2", lStyleSystem2);   
        
        /* 		TextFieldStyle tfStyleTextAnt = new TextFieldStyle();
 		tfStyleTextAnt.font = calibri12;
 		tfStyleTextAnt.fontColor = new Color(215/255f, 100/255f, 40/255f, 1);
 		tfStyleTextAnt.cursor = skin.getDrawable("black");	 	
 		skin.add("ant", tfStyleTextAnt);
 		
 		TextFieldStyle tfStyleTextPiton = new TextFieldStyle();
 		tfStyleTextPiton.font = calibri12;
 		tfStyleTextPiton.fontColor =Color.RED;
 		tfStyleTextPiton.cursor = skin.getDrawable("black");	 	 		
 		skin.add("piton", tfStyleTextPiton);
 		
 		TextFieldStyle tfStyleTextTiger = new TextFieldStyle();
 		tfStyleTextTiger.font = calibri12;
 		tfStyleTextTiger.fontColor = Color.GREEN;
 		tfStyleTextTiger.cursor = skin.getDrawable("black");	 	 		 		
 		skin.add("tiger", tfStyleTextTiger);

 		TextFieldStyle tfStyleTextSpider = new TextFieldStyle();
 		tfStyleTextSpider.font = calibri12;
 		tfStyleTextSpider.fontColor = Color.YELLOW;
 		tfStyleTextSpider.cursor = skin.getDrawable("black");	 	 		 		 		
 		skin.add("spider", tfStyleTextSpider);*/
 		
 		LabelStyle lStyleTextAnt = new LabelStyle();
 		lStyleTextAnt.font = skin.getFont("default");
 		lStyleTextAnt.fontColor = new Color(215/255f, 100/255f, 40/255f, 1);
 		skin.add("ant", lStyleTextAnt);
 		
 		LabelStyle lStyleTextPiton = new LabelStyle();
 		lStyleTextPiton.font = skin.getFont("default");
 		lStyleTextPiton.fontColor =Color.RED;
 		skin.add("piton", lStyleTextPiton);
 		
 		LabelStyle lStyleTextTiger = new LabelStyle();
 		lStyleTextTiger.font =skin.getFont("default");
 		lStyleTextTiger.fontColor = Color.GREEN;
 		skin.add("tiger", lStyleTextTiger);

 		LabelStyle lStyleTextSpider = new LabelStyle();
 		lStyleTextSpider.font = skin.getFont("default");
 		lStyleTextSpider.fontColor = Color.YELLOW;
 		skin.add("spider", lStyleTextSpider);
 		
///////////////////////////////////////////////
///////////	 TITLES 	/////////////
////////////////////////////////////////////// 		
 		LabelStyle titleOrange = new LabelStyle();
 		titleOrange.font = skin.getFont("title");
 		titleOrange.fontColor = Color.ORANGE;
 		skin.add("title_orange", titleOrange);	
 		
 		LabelStyle titleRed = new LabelStyle();
 		titleRed.font = skin.getFont("title");
 		titleRed.fontColor = Color.RED;
 		skin.add("title_red", titleRed);	
 		
 		LabelStyle titleBlue = new LabelStyle();
 		titleBlue.font = skin.getFont("title");
 		titleBlue.fontColor = Color.BLUE;
 		skin.add("title_blue", titleBlue);	
 		
 		LabelStyle titleGreen = new LabelStyle();
 		titleGreen.font = skin.getFont("title");
 		titleGreen.fontColor = Color.GREEN;
 		skin.add("title_green", titleGreen);	
 		
 		LabelStyle titleYellow = new LabelStyle();
 		titleYellow.font = skin.getFont("title");
 		titleYellow.fontColor = Color.YELLOW;
 		skin.add("title_yellow", titleYellow);	
 		
 		LabelStyle titleGray = new LabelStyle();
 		titleGray.font = skin.getFont("title");
 		titleGray.fontColor = Color.GRAY;
 		skin.add("title_gray", titleGray);
 		
 		LabelStyle titleViolet = new LabelStyle();
 		titleViolet.font = skin.getFont("title");
 		titleViolet.fontColor = Colors.Violet;
 		skin.add("title_violet", titleViolet);	
 		
///////////////////////////////////////////////
///////////	 LABELs 	/////////////
//////////////////////////////////////////////
		LabelStyle orange = new LabelStyle();
		orange.font = skin.getFont("default");
		orange.fontColor = Color.ORANGE;
		skin.add("orange", orange);	
		
		LabelStyle red = new LabelStyle();
		red.font = skin.getFont("default");
		red.fontColor = Color.RED;
		skin.add("red", red);	
		
		LabelStyle blue = new LabelStyle();
		blue.font = skin.getFont("default");
		blue.fontColor = Color.BLUE;
		skin.add("blue", blue);	
		
		LabelStyle green = new LabelStyle();
		green.font = skin.getFont("default");
		green.fontColor = Color.GREEN;
		skin.add("green", green);	
		
		LabelStyle yellow = new LabelStyle();
		yellow.font = skin.getFont("default");
		yellow.fontColor = Color.YELLOW;
		skin.add("yellow", yellow);	
		
		LabelStyle gray = new LabelStyle();
		gray.font = skin.getFont("default");
		gray.fontColor = Color.GRAY;
		skin.add("gray", gray);	 	
		
		LabelStyle violet = new LabelStyle();
		violet.font = skin.getFont("default");
		violet.fontColor = Colors.DarkViolet;
		skin.add("darkViolet", violet);	 	
		
		LabelStyle dsb = new LabelStyle();
		dsb.font = skin.getFont("default");
		dsb.fontColor = Colors.DeepBlue;
		skin.add("deepBlue", dsb);	 	
		
		LabelStyle metal = new LabelStyle();
		metal.font = skin.getFont("default");
		metal.fontColor = Colors.MetalGray;
		skin.add("metal", metal);	 
		
		LabelStyle lightBlue = new LabelStyle();
		//lightBlue.background = skin.getDrawable("white");
		lightBlue.font = skin.getFont("default");
		lightBlue.fontColor = Colors.LightBlue;
		skin.add("lightBlue", lightBlue);	 
		
///////////////////////////////////////////////
       ///////////	 POPUP 	/////////////
//////////////////////////////////////////////
 		
 		skin.add("popup", new NinePatch(atlas.findRegion("bubble1"), 40, 40, 40, 40+53));
 		NinePatchDrawable lyricsNpd = new NinePatchDrawable(skin.getPatch("popup")); 		
 		skin.add("popup", lyricsNpd, Drawable.class); 		
 		LabelStyle lStylePopup = new LabelStyle();
 		lStylePopup.background =skin.getDrawable("popup");
 		lStylePopup.font = calibri14;
 		lStylePopup.fontColor = Color.BLACK.cpy();
 		skin.add("popup", lStylePopup);

 		
 		skin.add("popup2", new NinePatch(atlas.findRegion("bubble2"), 40, 40, 40, 40));
 		NinePatchDrawable popup2NPD = new NinePatchDrawable(skin.getPatch("popup2")); 		
 		skin.add("popup2", popup2NPD, Drawable.class); 		
 		LabelStyle lStylePopup2 = new LabelStyle();
 		lStylePopup2.background =skin.getDrawable("popup2");
 		lStylePopup2.font = calibri14;
 		lStylePopup2.fontColor = Color.BLACK.cpy();
 		skin.add("popup2", lStylePopup2);   
///////////////////////////////////////////////
///////////	 INSTRUCTIONS / ADULTS 	/////////////
//////////////////////////////////////////////
		LabelStyle lStyleMarker = new LabelStyle();
		lStyleMarker.font = calibri14;
		lStyleMarker.fontColor = Color.BLUE;
		skin.add("marker", lStyleMarker);	

		LabelStyle lStyleInsruction = new LabelStyle();
		lStyleInsruction.font = calibri14;
		lStyleInsruction.fontColor = Color.BLACK;
		skin.add("instruction", lStyleInsruction);	
				
		LabelStyle lStyleInstructionTitle = new LabelStyle();
		lStyleInstructionTitle.font = calibri14;
		lStyleInstructionTitle.fontColor = Color.RED;
		skin.add("instructionTitle", lStyleInstructionTitle);	
		
		LabelStyle lStyleInstruction2 = new LabelStyle();
		lStyleInstruction2.font = calibri14;
		lStyleInstruction2.fontColor = new Color(105 /255f , 160 /255f, 200 /255f, 1);
		skin.add("instruction2", lStyleInstruction2);	

		
 		NinePatch vscrollNP = new NinePatch(atlas.findRegion("vscroll"), 0, 0, 20, 20);
 		skin.add("vscroll_", vscrollNP);

 		NinePatch vknobNP = new NinePatch(atlas.findRegion("vknob3"), 0, 0, 10, 10);
 		skin.add("vknob", vknobNP);
 		
 		NinePatch vscroll2NP = new NinePatch(atlas.findRegion("vscroll3"), 0, 0, 10, 10);
 		skin.add("vscroll", vscroll2NP);

 		TextureRegion frameTex = atlas.findRegion("frame");
 		NinePatch frameNP = new NinePatch(frameTex, 28, 30, 27, 33);
 		skin.add("frame", frameNP);
 		
 		TextureRegion menuitem = atlas.findRegion("menuitem");
 		NinePatch menuitemNP = new NinePatch(menuitem, 10, 15, 10, 15);
 		skin.add("menuitem", menuitemNP);
 		
 		TextButtonStyle tbsMenuItem = new TextButtonStyle();
 		tbsMenuItem.up = skin.getDrawable("menuitem");
 		tbsMenuItem.font = skin.getFont("default");
 		tbsMenuItem.fontColor = Color.BLACK;
 		tbsMenuItem.pressedOffsetX = 2;
 		tbsMenuItem.pressedOffsetY = -2;
 		skin.add("menuitem", tbsMenuItem);

/* 		TextureRegion frameTail = atlas.findRegion("frame-tail");
 		skin.add("frameTail", frameTail);*/
 		
 		TextureRegion fillTex = atlas.findRegion("fill");
 		NinePatch fillNP = new NinePatch(fillTex, 3, 3, 3, 3);		
 		skin.add("fill", fillNP);
 		
 		ScrollPaneStyle spStyle = new ScrollPaneStyle();
 		//spStyle.background = skin.getDrawable("white");
 		spStyle.corner = skin.getDrawable("debugTable");
 		spStyle.vScroll =  skin.getDrawable("vscroll");
 		spStyle.vScrollKnob = skin.getDrawable("vknob");
  		
 		skin.add("default", spStyle);
 		
 		
 		
 		
 		ListStyle listStyle = new ListStyle();
 		listStyle.font = calibri14;
 		listStyle.selection = skin.getDrawable("white"); 		
 		listStyle.fontColorSelected = Color.RED;
 		listStyle.fontColorUnselected = Color.BLUE;
 		//listStyle.
 		skin.add("default", listStyle);
 		
 		
 		
 		WindowStyle wStyle = new WindowStyle();
 	
 		wStyle.background = skin.getDrawable("frame");
 		wStyle.titleFont = skin.getFont("default");
 		skin.add("default", wStyle);


        TextButtonStyle tbStyleSystem2 = new TextButtonStyle();
        tbStyleSystem2.font = skin.getFont("default");
        tbStyleSystem2.fontColor = Color.BLACK;
        tbStyleSystem2.up = skin.getDrawable("vknob");
        skin.add("default", tbStyleSystem2);
        
       
		LabelStyle lStyleSmallAntHit = new LabelStyle();
		lStyleSmallAntHit.font = skin.getFont("36");
		lStyleSmallAntHit.fontColor = Color.MAGENTA;
		lStyleSmallAntHit.background = skin.getDrawable("white");
		skin.add("small-ant-hit", lStyleSmallAntHit);	
		
		LabelStyle lStyleSmallPitonHit = new LabelStyle();
		lStyleSmallPitonHit.font = skin.getFont("36");
		lStyleSmallPitonHit.fontColor = Color.MAGENTA;
		lStyleSmallPitonHit.background = skin.getDrawable("white");
		skin.add("small-piton-hit", lStyleSmallPitonHit);	
		
		LabelStyle lStyleSmallTigerHit = new LabelStyle();
		lStyleSmallTigerHit.font = skin.getFont("36");
		lStyleSmallTigerHit.fontColor = Colors.Violet;
		lStyleSmallTigerHit.background = skin.getDrawable("white");
		skin.add("small-tiger-hit", lStyleSmallTigerHit);	
		
		LabelStyle lStyleSmallSpiderHit = new LabelStyle();
		lStyleSmallSpiderHit.font = skin.getFont("36");
		lStyleSmallSpiderHit.fontColor = Color.MAGENTA;
		lStyleSmallSpiderHit.background = skin.getDrawable("white");
		skin.add("small-spider-hit", lStyleSmallSpiderHit);	
        
		LabelStyle lStyleSmallAnt = new LabelStyle();
		lStyleSmallAnt.font = skin.getFont("36");
		lStyleSmallAnt.fontColor = Color.BLUE;
		skin.add("small-ant", lStyleSmallAnt);	
		
		
		LabelStyle lStyleSmallSpider = new LabelStyle();
		lStyleSmallSpider.font = skin.getFont("36");
		lStyleSmallSpider.fontColor = Color.BLACK;
		skin.add("small-spider", lStyleSmallSpider);	
		
		
		LabelStyle lStyleSmallPiton = new LabelStyle();
		lStyleSmallPiton.font = skin.getFont("36");
		lStyleSmallPiton.fontColor = Color.GREEN;
		skin.add("small-piton", lStyleSmallPiton);	
		
		
		LabelStyle lStyleSmallTiger = new LabelStyle();
		lStyleSmallTiger.font = skin.getFont("36");
		lStyleSmallTiger.fontColor = Color.RED;
		skin.add("small-tiger", lStyleSmallTiger);	
		
		
		TextureRegion trCircle = atlas.findRegion("circle");
		NinePatch npCircle = new NinePatch(trCircle, 31, 31, 31, 31);
		skin.add("circle", npCircle);
		
		LabelStyle lStyleLetter = new LabelStyle();
		lStyleLetter.font = skin.getFont("letter");
		lStyleLetter.fontColor = Color.BLACK;
	//	lStyleLetter.background = skin.getDrawable("circle");
		skin.add("letter", lStyleLetter);		
		
		TextureRegion checkedRegion =  atlas.findRegion("checked2");
		TextureRegion uncheckedRegion = atlas.findRegion("unchecked2");
		NinePatch checkedNP = new NinePatch(checkedRegion, 7, 7, 7, 7);
		NinePatch uncheckedNP = new NinePatch(uncheckedRegion, 7, 7, 7, 7);
		skin.add("checked", checkedNP);
		skin.add("unchecked", uncheckedNP);

		
		CheckBoxStyle cbs = new CheckBoxStyle();
	
		
		cbs.checkboxOn = skin.getDrawable("checked") ;
		cbs.checkboxOff= skin.getDrawable("unchecked") ;
		cbs.font = skin.getFont("default");
		cbs.fontColor = Color.BLACK;
		skin.add("default", cbs);
		
		TextureRegion buttonRegion =  atlas.findRegion("button");
		NinePatch buttonNP = new NinePatch(buttonRegion, 10, 17, 1, 8);
		skin.add("button", buttonNP);
	
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.pressedOffsetY = -1;
		tbs.pressedOffsetX = 1;
		tbs.font = skin.getFont("default");
		tbs.fontColor = Color.BLACK;
		tbs.up = skin.getDrawable("button");
		skin.add("button", tbs);
	}
	

	
	
	public Skin getSkin(){
		return skin;
	}
	
	public TextureAtlas getAtlas(){
		return manager.get("data/main.pack");
	}
	

	
	public TextureAtlas getMiscAtlas(){
		return manager.get("data/misc/misc.pack");
	}
	
	public Texture getGameField(int i){
		
		return manager.get(String.format("data/field%d.jpg", i));
	}
	
	public TextureRegion getNikoleLetter(){
		return nikolLetterRegion;
	}
	public TextureRegion getInstructionScreen(){
		return instructScreenReg;
	}
	
	public Texture getEmptyCircle(Color c, float D){
		int txsize = 2;
		int pow = 1;
		while(txsize<D){
			txsize = (int) Math.pow(2, pow++);
		}
		
		Map<Color, Texture> colorMap =data.get(txsize);
		if(colorMap == null){
			colorMap = new HashMap<Color, Texture>();
			data.put(Integer.valueOf(txsize), colorMap);
		}
		
		Texture tx = colorMap.get(c);
		if(tx==null){
			Pixmap pmap = new Pixmap(txsize, txsize, Format.RGBA8888);
			pmap.setColor(c);
			pmap.drawCircle(txsize/2-1, txsize/2-1, txsize/2-1);
			tx = new Texture(pmap);
			colorMap.put(c, tx);
		}
		
		return tx;

	}
	
	public Texture getCircle(Color c, float D){
		int txsize = 2;
		int pow = 1;
		while(txsize<D){
			txsize = (int) Math.pow(2, pow++);
		}
		
		Map<Color, Texture> colorMap =data.get(txsize);
		if(colorMap == null){
			colorMap = new HashMap<Color, Texture>();
			data.put(Integer.valueOf(txsize), colorMap);
		}
		
		Texture tx = colorMap.get(c);
		if(tx==null){
			Pixmap pmap = new Pixmap(txsize, txsize, Format.RGBA8888);
			pmap.setColor(c);
			pmap.fillCircle(txsize/2-1, txsize/2-1, txsize/2-1);
			tx = new Texture(pmap);
			colorMap.put(c, tx);
		}
		
		return tx;

	}
	
	public Texture getUnmanaged(Color c){
		Texture tex = unmanaged.get(c);
		if(tex == null){
			Pixmap pmap = new Pixmap(1, 1, Format.RGBA8888);
			pmap.setColor(c);
			pmap.fill();
			tex = new Texture(pmap);
			unmanaged.put(c, tex);
		}
		
		return tex;
	}
	
	public   void manage(){

		

		Iterator<Map<Color, Texture>> rootIter =data.values().iterator();
		while(rootIter.hasNext()){
			Map<Color, Texture> map = rootIter.next();
			Iterator<Texture> texIter = map.values().iterator();
			while(texIter.hasNext()){
				Texture tx = texIter.next();
				tx.dispose();
				texIter.remove();
			}
			
			rootIter.remove();
		}

		Iterator<Texture> texIter = unmanaged.values().iterator();
		while(texIter.hasNext()){
			Texture tx = texIter.next();
			tx.dispose();
			texIter.remove();
		}

	}
	
	
	private Map<Color, Texture> unmanaged = new HashMap<Color, Texture>();
	private Map<Integer, Map<Color, Texture>> data = new HashMap<Integer, Map<Color,Texture>>();
	
	////////////// FONTS /////////////////
	

	

	
	public void showMessage(String msg){
		System.out.println(msg);
	}
	

	//private List<Texture> fields = new ArrayList<Texture>(4); 
	
	//private TextureAtlas atlas;
	
	
	//private TextureAtlas miscAtlas;
	
	
	
	
	
	private Skin skin;

	//private Texture nickolLetter;
	
	private TextureRegion nikolLetterRegion;
	
	//private Texture instructScreen;
	
	private TextureRegion instructScreenReg;
	
	private void disposeInternal(){
		

		

		
		skin.dispose();
		//nickolLetter.dispose();
		//instructScreen.dispose();
		
		Iterator<Map<Color, Texture>> rootIter = data.values().iterator();
		while(rootIter.hasNext()){
			Map<Color, Texture> map = rootIter.next();
			Iterator<Texture> texIter = map.values().iterator();
			while(texIter.hasNext()){
				Texture tx = texIter.next();
				tx.dispose();
				texIter.remove();
			}
			
			rootIter.remove();
		}

		Iterator<Texture> texIter = unmanaged.values().iterator();
		while(texIter.hasNext()){
			Texture tx = texIter.next();
			tx.dispose();
			texIter.remove();
		}
		
/*		for(Sound snd : sounds.values()){
			snd.dispose();
		}*/

		manager.dispose();
	}
	

	public void dispose(){

		disposeInternal();
	}
}
