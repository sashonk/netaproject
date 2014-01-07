package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.me.neta.events.DragEvent;
import com.me.neta.events.DragStartEvent;
import com.me.neta.events.DragStopEvent;
import com.me.neta.factories.CircleFactory;
import com.me.neta.factories.LetterFactory;
import com.me.neta.factories.PolygonFactory;
import com.me.neta.factories.SemicircleFactory;


public class FiguresPanel extends Group{
	TextureRegion treg;
	static float dx = 40;
	static float dy = 40;
	
	public FiguresPanel(){

		treg= TextureManager.get().getMiscAtlas().findRegion("figuresPanel2");
		this.addListener(new MetricListener());
		

		float d =40;
		for(int i =1 ; i<=5; i++){
			for(int j=1; j<=6; j++){
				if(i*j==30){
					break;
				}
				
				LetterFactory factory = new LetterFactory(j+(i-1)*6);
				factory.setPosition(220+j*d, 435-i*d);
				addActor(factory);
			}
		}

		
		{
			 ///////////// ///////////// /////////////
			////////////  CIRCLES  //////////////
			 ///////////// ///////////// /////////////
		
			CircleFactory bcf = new CircleFactory(42, 41, "FIGURA3W");
			bcf.setPosition(131, 376);
			addActor(bcf);
			
			CircleFactory mcf = new CircleFactory(30, 29, "FIGURA2W");
			mcf.setPosition(76, 382);
			addActor(mcf);
			
			CircleFactory scf = new CircleFactory(18, 18, "FIGURA1W");
			scf.setPosition(35, 386);
			addActor(scf);	
			
			
			CircleFactory bof = new CircleFactory(32, 65, "FIGURA20W");
			bof.setPosition(198, 346);
			addActor(bof);
			
			
			CircleFactory mof = new CircleFactory(23, 46, "FIGURA21W");
			mof.setPosition(206, 287);
			addActor(mof);
			
			
			CircleFactory sof = new CircleFactory(16, 32, "FIGURA22W");
			sof.setPosition(213, 239);
			addActor(sof);
		}	
		
		
		{
			 ///////////// ///////////// /////////////
			////////////  SEMICIRCLES  //////////////
			 ///////////// ///////////// /////////////
		
			SemicircleFactory bscf = new SemicircleFactory(44, 23, "FIGURA12W");
			bscf.setPosition(140, 236);
			addActor(bscf);
			
			SemicircleFactory mscf = new SemicircleFactory(32, 16, "FIGURA11W");
			mscf.setPosition(80, 245);
			addActor(mscf);
			
			SemicircleFactory sscf = new SemicircleFactory(21, 11, "FIGURA10W");
			sscf.setPosition(40, 250);
			addActor(sscf);	

		}	
	
		
		{
			 ///////////// ///////////// /////////////
			////////////  SQUARES  //////////////
			 ///////////// ///////////// /////////////
		
			PolygonFactory sfb = new PolygonFactory(new float[]{0f,0f, 0,41, 42,41, 42f,0f}, "FIGURA6W", new Size(42, 41));
			sfb.setPosition(134, 324);
			addActor(sfb);
			
			PolygonFactory sfm = new PolygonFactory(new float[]{0f,0f, 0,29, 30,29, 30,0f}, "FIGURA5W", new Size(30, 29));
			sfm.setPosition(80, 333);
			addActor(sfm);
			
			PolygonFactory sfs = new PolygonFactory(new float[]{0f,0f, 0,18, 18,18, 18,0f}, "FIGURA4W", new Size(18, 18));
			sfs.setPosition(38, 340);
			addActor(sfs);
		}	
		
		{
			 ///////////// ///////////// /////////////
			////////////  RECTANGLES  //////////////
			 ///////////// ///////////// /////////////
		
			PolygonFactory rfb = new PolygonFactory(new float[]{0f,0f, 0f,84f, 42f,84f, 42f,0f}, "FIGURA19W", new Size(42, 84));
			rfb.setPosition(140, 83);
			addActor(rfb);
			
			PolygonFactory rfm = new PolygonFactory(new float[]{0f,0f, 0f,60f, 30f,60f,30f,0f}, "FIGURA18W", new Size(30, 60));
			rfm.setPosition(81, 96);
			addActor(rfm);
			
			PolygonFactory rfs = new PolygonFactory(new float[]{0f,0f, 0f,37f, 19f,37f,19f,0f}, "FIGURA17W", new Size(19, 37));
			rfs.setPosition(40, 110);
			addActor(rfs);
			
			PolygonFactory rfsthin = new PolygonFactory(new float[]{0f,0f, 0f,26f, 6,26, 6,0f}, "FIGURA25W", new Size(6, 26));
			rfsthin.setPosition(220, 205);
			addActor(rfsthin);
			
			PolygonFactory rfmthin = new PolygonFactory(new float[]{0f,0f, 0f,41, 7,41, 7,0f}, "FIGURA24W", new Size(7, 41));
			rfmthin.setPosition(219, 155);
			addActor(rfmthin);
			
			PolygonFactory rfbthin = new PolygonFactory(new float[]{0f,0f, 0f,41, 10,41,10,0f}, "FIGURA26W", new Size(10, 41));
			rfbthin.setPosition(218, 86);
			addActor(rfbthin);			
			
		}
		
		{		
		 ///////////// ///////////// /////////////
		////////////  TRAPEZES  //////////////
		 ///////////// ///////////// /////////////

			PolygonFactory tfb = new PolygonFactory(new float[]{0f,0f, 10f,41f, 31f,41f, 42f,0f}, "FIGURA15W", new Size(42, 41));
			tfb.setPosition(140, 184);
			addActor(tfb);
			
			PolygonFactory tfm = new PolygonFactory(new float[]{0f,0f, 7f,29f, 22f,29f, 30f,0f}, "FIGURA14W", new Size(30, 29));
			tfm.setPosition(81, 192);
			addActor(tfm);
					
			PolygonFactory tfs = new PolygonFactory(new float[]{0f,0f, 4f,18f, 13f,18f, 18f,0f}, "FIGURA13W", new Size(18, 18));
			tfs.setPosition(41, 200);
			addActor(tfs);
		}
		
		{
			
			 ///////////// ///////////// /////////////
			////////////  TRIANGLES  //////////////
			 ///////////// ///////////// /////////////
		
			PolygonFactory tfb = new PolygonFactory(new float[]{0f,0f, 25,42, 25,42, 50,0f}, "FIGURA9W", new Size(50, 42));
			tfb.setPosition(133,270);
			addActor(tfb);
			
			PolygonFactory tfm = new PolygonFactory(new float[]{0f,0f, 18,30, 18,30,36f,0f}, "FIGURA8W", new Size(36, 30));
			tfm.setPosition(76,278);
			addActor(tfm);
			
			PolygonFactory tfs = new PolygonFactory(new float[]{0f,0f, 11,19, 11,19,23,0f}, "FIGURA7W", new Size(23, 19));
			tfs.setPosition(38, 288);
			addActor(tfs);
		}
		
		
		this.addCaptureListener(new EventListener(){

			@Override
			public boolean handle(Event event) {
				if(event instanceof DragStartEvent){
						Color c = FiguresPanel.this.getColor();
						FiguresPanel.this.setColor(c.r, c.g, c.b, c.a*.5f);									
				}
				
				if(event instanceof DragStopEvent){
					Color c = FiguresPanel.this.getColor();
					FiguresPanel.this.setColor(c.r, c.g, c.b, 1);
				}
				
				if(event instanceof DragEvent){
					DragEvent devent = (DragEvent)event;
					Actor target = devent.getDragActor();
					Rectangle thisRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
					Rectangle targetRect = new Rectangle(target.getX(), target.getY(), target.getWidth(), target.getHeight());
				
					Color c = FiguresPanel.this.getColor();
					if(thisRect.overlaps(targetRect)){
						FiguresPanel.this.setColor(c.r, c.g, c.b, .5f);
					}
					else{
						FiguresPanel.this.setColor(c.r, c.g, c.b, 1);						
					}
				}
				
				return false;
			}
			
		});
	}
	
	public void draw(SpriteBatch batch , float parentAlpha){
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentAlpha);
		batch.draw(treg, this.getX(), this.getY(), this.getWidth(), this.getHeight());				
		super.draw(batch, parentAlpha);
	}
}
