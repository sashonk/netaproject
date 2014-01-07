package com.me.neta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

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
			"\u04FD\u04FE\u04FF";
	
	private static TextureManager instance;
	
	public static final String DEFAULT_CHARS = FreeTypeFontGenerator.DEFAULT_CHARS.concat(CHARS);
	
	public static TextureManager get(){
		if(instance==null){
			instance = new TextureManager();
			
		}
		
		return instance;
	}
	
	
	private TextureManager(){
		atlas = new TextureAtlas(Gdx.files.internal("data/main.pack"));
		//fieldsAtlas = new TextureAtlas(Gdx.files.internal("data/fields/fields.pack"));
		miscAtlas = new TextureAtlas(Gdx.files.internal("data/misc/misc.pack"));

		fields.add(new Texture(Gdx.files.internal("data/field1.jpg")));
		fields.add(new Texture(Gdx.files.internal("data/field2.jpg")));
		fields.add(new Texture(Gdx.files.internal("data/field3.jpg")));
		fields.add(new Texture(Gdx.files.internal("data/field4.jpg")));

		wonderlandFont = new BitmapFont(Gdx.files.internal("data/fonts/wond.fnt"), false);
		
		calibriFont = new BitmapFont(Gdx.files.internal("data/fonts/calibri.fnt"), false);
		
		calibri14Font = new BitmapFont(Gdx.files.internal("data/fonts/calibri14.fnt"), false);

		
		bottomPanelTexture = new Texture(Gdx.files.internal("data/panel.png"));
		
		nickolLetter = new Texture(Gdx.files.internal("data/nikol.jpg"));
		
		 nikolLetterRegion = new TextureRegion(nickolLetter, 0, 0, 925, 433);
		
		 instructScreen = new Texture(Gdx.files.internal("data/instruct.jpg"));
		 
		 instructScreenReg =  new TextureRegion(instructScreen, 0, 0, 925, 433);
		
		skin = new Skin();
		
		skin.add("default", calibriFont);
		skin.add("violet_calibri14", calibri14Font);
		
		 Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
         pixmap.setColor(Color.WHITE);
         pixmap.fill();
         skin.add("white", new Texture(pixmap));
         
         pixmap.setColor(Color.BLACK);
         pixmap.fill();
         skin.add("black", new Texture(pixmap));
         
         TextFieldStyle blueCalibri = new TextFieldStyle();
         blueCalibri.fontColor = new Color(.4f, .57f, .74f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         blueCalibri.font = skin.getFont("default");
         skin.add("blue_calibri", blueCalibri);         
         
         
         TextFieldStyle lightBlueCalibri = new TextFieldStyle();
         lightBlueCalibri.fontColor = new Color(.47f, .87f, .93f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         lightBlueCalibri.font = skin.getFont("default");
         skin.add("light_blue_calibri", lightBlueCalibri);   
         
         
         TextFieldStyle violetCalibri = new TextFieldStyle();
         violetCalibri.fontColor = new Color(.22f, .08f, .45f,1);
        //textFieldStyle.background = skin.newDrawable("white", new Color(.4f, .57f, .74f,1));               
         violetCalibri.font = skin.getFont("default");
         skin.add("violet_calibri", violetCalibri);   
         

         
         
         LabelStyle labelStyle = new LabelStyle();
         labelStyle.font = skin.getFont("default");
         labelStyle.fontColor = Color.BLACK;
         skin.add("default", labelStyle);
         
         LabelStyle labelStyleCalibri14Violet = new LabelStyle();
         labelStyleCalibri14Violet.font = calibri14Font;
         labelStyleCalibri14Violet.fontColor = new Color(.22f, .08f, .45f,1);
         skin.add("violet_calibri14", labelStyleCalibri14Violet);
         
    
         LabelStyle labelStyleLyricTitle = new LabelStyle();
         labelStyleLyricTitle.font = skin.getFont("violet_calibri14");                 
         labelStyleLyricTitle.fontColor = new Color(.22f, .08f, .45f,1);
         skin.add("lyrics", labelStyleLyricTitle);
         
         
         FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/arialbd.ttf"));
 		 BitmapFont cyrillicFont = generator.generateFont(12,DEFAULT_CHARS, false);
 		generator.dispose();
 		TextFieldStyle tfStyleTextAnt = new TextFieldStyle();
 		tfStyleTextAnt.font = cyrillicFont;
 		tfStyleTextAnt.fontColor = new Color(215/255f, 100/255f, 40/255f, 1);
 		tfStyleTextAnt.cursor = skin.getDrawable("black");	 	
 		skin.add("ant", tfStyleTextAnt);
 		
 		TextFieldStyle tfStyleTextPiton = new TextFieldStyle();
 		tfStyleTextPiton.font = cyrillicFont;
 		tfStyleTextPiton.fontColor =Color.RED;
 		tfStyleTextPiton.cursor = skin.getDrawable("black");	 	 		
 		skin.add("piton", tfStyleTextPiton);
 		
 		TextFieldStyle tfStyleTextTiger = new TextFieldStyle();
 		tfStyleTextTiger.font = cyrillicFont;
 		tfStyleTextTiger.fontColor = Color.GREEN;
 		tfStyleTextTiger.cursor = skin.getDrawable("black");	 	 		 		
 		skin.add("tiger", tfStyleTextTiger);

 		TextFieldStyle tfStyleTextSpider = new TextFieldStyle();
 		tfStyleTextSpider.font = cyrillicFont;
 		tfStyleTextSpider.fontColor = Color.YELLOW;
 		tfStyleTextSpider.cursor = skin.getDrawable("black");	 	 		 		 		
 		skin.add("spider", tfStyleTextSpider);
 		
 
	

///////////////////////////////////////////////
       ///////////	 MISC 	/////////////
//////////////////////////////////////////////
         
         
         circles = new HashMap<Color, Texture>();
	}
	
	public Skin getSkin(){
		return skin;
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}
	

	
	public TextureAtlas getMiscAtlas(){
		return miscAtlas;
	}
	
	public Texture getGameField(int i){
		return fields.get(i);
	}
	
	public TextureRegion getNikoleLetter(){
		return nikolLetterRegion;
	}
	public TextureRegion getInstructionScreen(){
		return instructScreenReg;
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
	
	
	private Map<Color, Texture> circles;
	
	
	
	private Map<Integer, Map<Color, Texture>> data = new HashMap<Integer, Map<Color,Texture>>();
	
	////////////// FONTS /////////////////
	
	public BitmapFont getWonderlandFont(){
		return wonderlandFont;
	}
	
	public BitmapFont getCalibriFont(){
		return calibriFont;
	}
	
	
	
	public Texture getBottomPanelTexture(){
		return bottomPanelTexture;
	}
	
	private List<Texture> fields = new ArrayList<Texture>(4); 
	
	private TextureAtlas atlas;
	
	
	private TextureAtlas miscAtlas;
	
	private BitmapFont wonderlandFont ;
	
	private BitmapFont calibriFont;
	
	private BitmapFont calibri14Font;
	
	private Texture bottomPanelTexture;
	
	private Skin skin;

	private Texture nickolLetter;
	
	private TextureRegion nikolLetterRegion;
	
	private Texture instructScreen;
	
	private TextureRegion instructScreenReg;
	
	private void disposeInternal(){
		atlas.dispose();
		//fieldsAtlas.dispose();
		miscAtlas.dispose();
		
		for(Texture tex : fields){
			tex.dispose();
		}

		
		bottomPanelTexture.dispose();
		wonderlandFont.dispose();
		calibriFont.dispose();
		skin.dispose();
		calibri14Font.dispose();
		nickolLetter.dispose();
		instructScreen.dispose();
		

		

		for(Map<Color, Texture> mm : data.values()){
			for(Texture tx : mm.values()){
				tx.dispose();
			}
		}
	}
	
	public static void dispose(){
		if(instance==null){
			return;
		}
		
		instance.disposeInternal();
	}
}
