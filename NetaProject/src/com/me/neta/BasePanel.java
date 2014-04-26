package com.me.neta;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class BasePanel extends Window{
	NetaGame ng;
	public BasePanel(NetaGame ng, final Group parent, float tailX, float tailY, float  tailPadX) {
		super("", ng.getManager().getSkin());
		this.ng = ng;	
		this.setClip(false);

		parent.addActor(this);
		
		

		
		TextureRegion tr =new TextureRegion(ng.getManager().getAtlas().findRegion("frame-tail")) ;
		tr.flip(true, false);
		Image tail = new Image(tr);
		tail.setSize(52, 65);
		tail.setPosition(tailPadX, -51);
		addActor(tail);
		
    	float tx = 5;
    	float ty = 10;
    	Vector2 tailOffset = tail.localToParentCoordinates(new Vector2(tx,ty));
    	setPosition(tailX-tailOffset.x, tailY- tailOffset.y  );
	}

	

}
