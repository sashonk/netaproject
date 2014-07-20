package com.me.neta;

import java.io.File;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;

import sun.security.provider.NativePRNG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.me.neta.events.ScreenshotEvent;
import com.me.neta.tools.SaveTool.SavePanelHideEvent;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SavePanel extends Window{
	
	String ALBUM_NAME="Nikole&CO";
	NetaGame ng;
	TextureRegion treg;
	int timesHiden ;
	
	public int getTimesHidden(){
		return timesHiden;
	}
	
	public void setTimesHidden(int th){
		timesHiden = th;
	}
	
	public SavePanel(final NetaGame ng){
		
		super("", ng.getManager().getSkin());
		timesHiden = 0;
		this.ng = ng;
		//treg= ng.getManager().getMiscAtlas().findRegion("savePanel");
		this.setClip(false);


		
		Table table = this;
		TextButton item1 = new TextButton("Сохранить в фото альбом", ng.getManager().getSkin(), "menuitem");
		TextButton item2 = new TextButton("Email", ng.getManager().getSkin(), "menuitem");
		TextButton item3 = new TextButton("Facebook", ng.getManager().getSkin(), "menuitem");
		table.add(item1).row();
		table.add(item2).row();
		table.add(item3).row();
		table.pack();

		Image background = new Image(ng.getManager().getSkin().getPatch("frame"));
		background.setFillParent(true);
		addActor(background);
		background.toBack();
		
		
		TextureRegion tr = new TextureRegion(ng.getManager().getAtlas().findRegion("frame-tail"));
		Image tail = new Image(tr);
		tail.setSize(52, 65);
		tail.setPosition(190, -51);
		addActor(tail);
		table.pack();
		
		
		item1.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					saveAction();
					
					fire(new SavePanelHideEvent(++SavePanel.this.timesHiden));
					SavePanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});

		
		item2.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);

					emailAction();
					fire(new SavePanelHideEvent(++SavePanel.this.timesHiden));

					SavePanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});
		
		item3.addListener(new InputListener(
				){	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					event.setBubbles(false);
					facebookAction();
					fire(new SavePanelHideEvent(++SavePanel.this.timesHiden));

					SavePanel.this.addAction(sequence(fadeOut(0.4f), visible(false)));

					return true;
				}});
		

		//this.addListener(new MetricListener());
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

    
    
    void saveAction(){


		SavePanel.this.addAction(delay(.1f,run(new Runnable(){

			@Override
			public void run() {				
				if (Gdx.files.isExternalStorageAvailable()) {
				try {
					final Pixmap pixmap = getScreenshot(0, 0,
							Gdx.graphics.getWidth(),
							Gdx.graphics.getHeight(), true);
					MessageHelper.message(ng, "Пожалуйста, подождите...");	
					
					SavePanel.this.addAction(delay(.1f, Actions.run(new Runnable(){public void run() {	
					FileHandle handle = Gdx.files.external(String
							.format("picture%d.png",
									System.currentTimeMillis()));

					
					//for(int i = 0; i<50; i++){
						PixmapIO.writePNG(handle, pixmap);
				//	}
					
					String text ="Изображение сохранено "+  (NetaGame.debug ? new StringBuilder("[файл:").append(handle.file().getAbsolutePath()).append(']').toString() : "");  
					MessageHelper.notify(ng, text);
					
					}})));
				} catch (Exception ex) {
					MessageHelper.error(ng, "Ошибка! Изображение не сохранено!", ex);
				}
			} else {
				MessageHelper.error(ng, "Файловое хранилище недоступно!", null);

			}	}})));
		
					
		

    }
    
    void emailAction(){

		// email

		SavePanel.this.addAction(delay(.1f,run(new Runnable(){
			@Override
			public void run() {	
		if (Gdx.files.isExternalStorageAvailable()) {
			try {
				final Native p = ng.getNative();
				final Pixmap pixmap = getScreenshot(0, 0,
						Gdx.graphics.getWidth(),
						Gdx.graphics.getHeight(), true);
				MessageHelper.message(ng, "Пожалуйста, подождите...");
				
				SavePanel.this.addAction(delay(.1f, Actions.run(new Runnable(){public void run() {	
				
				FileHandle handle = Gdx.files.external(String
						.format("picture%d.png",
								System.currentTimeMillis()));
				PixmapIO.writePNG(handle, pixmap);
				p.setForEmail("nikoldruzya@ya.ru", handle.file(), "Письмо Николь");
				MessageHelper.hide(ng);
				}})));
			} catch (Exception ex) {
				MessageHelper.error(ng, "Ошибка!", ex);
			}
		} else {
			MessageHelper.error(ng, "Файловое хранилище недоступно!", null);


		}}})));
	
    	
    	
	}
    
    void facebookAction(){
		final Native p = ng.getNative();
		p.openWebPage("https://www.facebook.com/groups/nikoldruzya/");
	}
}
