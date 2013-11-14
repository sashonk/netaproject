package com.me.neta;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class FieldsTool extends AbstractTool{

	FieldsPanel panel;
	
	@Override
	void doAction() {
		Stage stage = this.getStage();
		
		if(panel==null){
			 panel = new FieldsPanel();
			panel.setBounds(this.getX()-140, this.getY()+80, 600, 350);
			stage.addActor(panel);
			panel.setVisible(false);
			Color c = panel.getColor();
			panel.setColor(c.r, c.g, c.b, 0);
		}
		
			if(panel.isVisible()){
				
				panel.addAction(add(panel, sequence(fadeOut(0.2f), visible(false))));				

			}
			else{
				panel.addAction(add(panel, sequence(visible(true), fadeIn(0.2f))));

			}
			
		
	}

	@Override
	String getImagePath() {
		return "monitor";
	}
	
	@Override
	public Size getSize() {
		return new Size(113 , 78);
	}
}
