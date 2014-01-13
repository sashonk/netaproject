package com.me.neta;

import java.io.File;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;

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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.me.neta.events.ScreenshotEvent;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SavePanel extends Group{
	
	String ALBUM_NAME="Nikole&CO";
	
	TextureRegion treg;
	public SavePanel(){
		treg= TextureManager.get().getMiscAtlas().findRegion("savePanel");

		addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				Platform p = NetaGame.platform;

				Rectangle save = new Rectangle(22, 238, 212, 38);
				Rectangle email = new Rectangle(22, 183, 212, 38);
				Rectangle facebook = new Rectangle(22, 125, 212, 38);
				Rectangle print = new Rectangle(22, 68, 212, 38);

				boolean result = false;
				
				
				if (save.contains(x, y)) {
					// NetaGame.platform.
					//fire(new ScreenshotEvent());
				
					
					SavePanel.this.addAction(run(new Runnable(){

						@Override
						public void run() {				
							if (Gdx.files.isExternalStorageAvailable()) {
							try {
								Pixmap pixmap = getScreenshot(0, 0,
										Gdx.graphics.getWidth(),
										Gdx.graphics.getHeight(), true);
								FileHandle handle = Gdx.files.external(String
										.format("picture%d.png",
												System.currentTimeMillis()));
								PixmapIO.writePNG(handle, pixmap);
								TextureManager.get().showMessage(
										"Изображение сохранено");
							} catch (Exception ex) {
								TextureManager.get().showMessage(ex.toString());
							}
						} else {
							TextureManager.get().showMessage(
									"Файловое хранилище недоступно");

						}	}}));
					
				


					result = true;
				} else if (email.contains(x, y)) {
					// email
					if (Gdx.files.isExternalStorageAvailable()) {
						try {
							Pixmap pixmap = getScreenshot(0, 0,
									Gdx.graphics.getWidth(),
									Gdx.graphics.getHeight(), true);
							FileHandle handle = Gdx.files.external(String
									.format("picture%d.png",
											System.currentTimeMillis()));
							PixmapIO.writePNG(handle, pixmap);
							p.setForEmail("golombek@mail.ru", handle.file(), "Send email...");

						} catch (Exception ex) {
							TextureManager.get().showMessage(ex.toString());
						}
					} else {
						TextureManager.get().showMessage(
								"Файловое хранилище недоступно");

					}

					result = true;
				} else if (facebook.contains(x, y)) {
					p.openWebPage("https://www.facebook.com/groups/nikoldruzya/");
					result = true;
				} else if (print.contains(x, y)) {
					// print
					result = true;
				}
				
				if(result){
					event.getTarget().setVisible(false);
				}

				return result;
			}
		});
		
		this.addListener(new MetricListener());
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
	
	public void draw(SpriteBatch batch , float parentAlpha){
		Color c = this.getColor();
		batch.setColor(c.r, c.g, c.b, c.a* parentAlpha);
		batch.draw(treg, this.getX(), this.getY(), this.getWidth(), this.getHeight());				
		super.draw(batch, parentAlpha);
	}
}
