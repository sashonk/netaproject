package com.me.neta.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import com.me.neta.Context;
import com.me.neta.Context.ContextProperty;
import com.me.neta.Popup.PopupGroup;
import com.me.neta.ContextListener;
import com.me.neta.NetaGame;
import com.me.neta.Popup;
import com.me.neta.Size;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

public abstract class AbstractTool extends Group implements ContextListener{
	
	
	
	protected float disabledAlphaValue = 0.4f;
	TextureRegion region;
	
	protected boolean blink;
	protected boolean everClicked;
	protected NetaGame ng;
	
	@Deprecated
	public boolean hasEverBeenClickedOnPopup(){
		return everClicked;
	}

	public float getK(){
		return .65f;
	}
	
	protected boolean popupAccepted(Context ctx){
		return 	(ctx.getProperty(ContextProperty.POPUP)==null ||((PopupGroup)ctx.getProperty(ContextProperty.POPUP)).contains(this));

	}
	
	public void setPopup(String text, float tailPadX, final PopupGroup pg, final float hideTimeout){
		this.setPopup(text, tailPadX, pg, hideTimeout, false);
	}
	
	public void setPopup(String text, float tailPadX, final PopupGroup pg, final float hideTimeout, boolean upsideDown){
	    
		if(findActor("popup")==null){
			
			float y = getHeight()+10;
			if(upsideDown){
				y = 0 - 10;
			}
			
			Popup p = new Popup(ng, this, text,getWidth()/2,y, tailPadX, upsideDown);
			p.setName("popup");
			if(hideTimeout>0){
				p.addAction(Actions.sequence(Actions.delay(hideTimeout), Actions.removeActor()));
			}
			ng.getContext().setProperty(ContextProperty.POPUP, pg);
		}
	}
	
	public void oldFashionedDraw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
	}
	
	public AbstractTool(final NetaGame ng){
		this.ng = ng;
		enabled = false;
		blink = true;
		everClicked= !Gdx.app.getPreferences(NetaGame.class.getName()).getBoolean("showPopup", true);
			
		AtlasRegion reg = ng.getManager().getAtlas().findRegion(getImagePath());
		Texture tex = reg.getTexture();
		
		region = new TextureRegion(tex, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight());
				

		this.addListener(new InputListener(){

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		/*		if(event.getTarget() instanceof Popup){
					return false;
				}*/
				
				if(enabled()){
					if(blink)event.getTarget().addAction(alpha(.4f));
					Actor popup = AbstractTool.this.findActor("popup");
					if(popup!=null){
						everClicked = true;
						popup.remove();
					//	ng.getContext().setProperty(ContextProperty.POPUP,null);
					}
					doAction();
	
					ng.getContext().notifyListeners();
				}
				event.setBubbles(false);
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(blink)event.getTarget().addAction(alpha(1, .2f));
			}
		});
		
		float k = getK();
		Size size = getSize();
		this.setSize(size.width*k, size.height*k);
		
	}
	
	public abstract Size getSize();
	

	
	public void contextChanged(Context ctx){
		setEnabled(accept(ctx));
	}

//	public abstract boolean accept(WorkspaceState state);
	
	public abstract boolean accept(Context ctx);

	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		super.draw(batch, parentAlfa);
		
		float alphaMul = enabled() ? 1 : disabledAlphaValue;
		Color c = getColor();
		batch.setColor(1, 1, 1, c.a*alphaMul*parentAlfa);
		batch.draw(region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	
	
	}
	
	
	public void setEnabled(boolean value){
		enabled = value;
	}
	
	private boolean enabled;
	
	public  boolean enabled(){
		return enabled;
	}

	public abstract void doAction();
	
	public abstract String getImagePath();
}
