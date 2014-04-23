package com.me.neta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.neta.tools.AbstractTool;

public class Popup extends Group{
	public Popup(NetaGame ng, Group parent, String text,float tailX, float tailY, float tpX){
		
    	Label popup = new Label(text, ng.getManager().getSkin(), "popup2");
    	TextureRegion tailRegion = ng.getManager().getAtlas().findRegion("bubble_tail");
    	Image tailImage = new Image(tailRegion);    	
    	float tailPadY = -54;
    	
    	float tailPadX = popup.getWidth()/2 - tailImage.getWidth()/2+tpX;
    	tailImage.setBounds(tailPadX,tailPadY, 43, 64);    	
    	tailImage.addListener(new MetricListener());
    	
    	
    	addActor(popup);
    	addActor(tailImage);    	
    	setSize(popup.getWidth(), popup.getHeight());

    	parent.addActor(this);
    	float tx = 4;
    	float ty = 5;
    	Vector2 tailOffset = tailImage.localToParentCoordinates(new Vector2(tx,ty));
    	setPosition(tailX-tailOffset.x, tailY- tailOffset.y  );
    	this.toFront();
	}

	
	public static class PopupGroup{
		
		public PopupGroup(AbstractTool...tls){
			this.tools.addAll(Arrays.asList(tls));
		}
		
		public PopupGroup(){
			
		}

		public boolean contains(AbstractTool tool){
			return tools.contains(tool);
		}
		
		public void add(AbstractTool tool){
			if(!tools.contains(tool)){
				tools.add(tool);
			}
		}
		 
		List<AbstractTool> tools = new LinkedList<AbstractTool>();
	}
}
