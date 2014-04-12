package com.me.neta.dummy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.Util;
//import com.me.neta.dummy.Dummy.DummyType;

public class DummyForm extends Window{

	Label label ;
	Label position ;
	Label size ;

	TextField group;
	TextField type;
	TextField originX;
	TextField originY;
	TextField zoom;
	TextField name;
	

	Dummy dummy;
	
	
	public DummyForm(NetaGame ng) {		
		super("", ng.getManager().getSkin());
		Skin s =ng.getManager().getSkin();
		
		Label l1 = new Label("Info:", s);
		label = new Label("", s);
		
		Label l10 = new Label("Pos:", s);
		position = new Label("", s);
		
		Label l20 = new Label("Size:", s);
		size = new Label("", s);
		
		Label l2 = new Label("Group:", s);
		group = new TextField("", s);
		
		Label l3 = new Label("Type:", s);
		type = new TextField("", s);
		
		Label l4 = new Label("Ox:", s);
		originX = new TextField("", s);
		
		Label l5 = new Label("Oy:", s);
		originY = new TextField("", s);
		
		Label l6 = new Label("Z:", s);
		zoom = new TextField("", s);		
		
		Label l7 = new Label("Name:", s);
		name = new TextField("", s);	
		
		TextButton ok = new TextButton("  OK  ", s);	
		ok.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				DummyForm.this.write();
				DummyForm.this.setVisible(false);
				return true;
			}
		});
		
		
		TextButton zoomBtn = new TextButton("  ZOOM  ", s);	
		zoomBtn.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				OrthographicCamera camera  = (OrthographicCamera) event.getListenerActor().getStage().getCamera();
				camera.zoom=dummy.getZoom();
				camera.position.set(dummy.getGroupOrigin().width, dummy.getGroupOrigin().height, 0);
				Util.constrainPosition(camera, event.getListenerActor().getStage() );
				Util.constrainZoom(camera);
				return true;
			}
		});
		
		TextButton cancel = new TextButton("CANCEL", s);	
		cancel.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				DummyForm.this.setVisible(false);
				return true;
			}
		});
		
		defaults().pad(1);
		defaults().align(Align.left);
		add(l1);
		add(label).row();
		add(l10);
		add(position).row();
		add(l20);
		add(size).row();		
		
		add(l2);
		add(group).row();
		add(l3);
		add(type).row();
		add(l4);
		add(originX).row();
		add(l5);
		add(originY).row();
		add(l6);
		add(zoom).row();
		add(l7);
		add(name).row();
		add(zoomBtn).row();
		
		add(cancel);		
		add(ok).row();


		
		pack();
		
	}
	
	public void show(Dummy dummy){
		
	
		this.dummy = dummy;
		label.setText(dummy.getName());
		group.setText(Integer.toString(dummy.getGroup()));
		type.setText(dummy.getType()!=null ? dummy.getType() : "");
		zoom.setText(Float.toString(dummy.getZoom()));
		if(dummy.getGroupOrigin()!=null){
		originX.setText(Float.toString(dummy.getGroupOrigin().width));
		originY.setText(Float.toString(dummy.getGroupOrigin().height));
		}
		size.setText(String.format("[w=%.1f; h=%.1f]", dummy.getWidth(), dummy.getHeight()));
		position.setText(String.format("[x=%.1f; y=%.1f]", dummy.getX(), dummy.getY()));
		name.setText(dummy.getName());

		this.pack();
		this.toFront();
		this.setPosition(0,768-this.getHeight());
		this.setVisible(true);
	}

	public void write(){
		if(dummy!=null){
			dummy.setGroup(Integer.parseInt(group.getText()));
			
			if(type.getText().length()>0){
				dummy.setType(type.getText());
			}
			
			if(originX.getText().length()>0 && originY.getText().length()>0){
				dummy.setGroupOrigin(new Size(Float.parseFloat(originX.getText()),Float.parseFloat(originY.getText())));				
			}
			if(zoom.getText().length()>0){
				dummy.setZoom(Float.parseFloat(zoom.getText()));
			}
			dummy.setName(name.getText());
		}
	}


}
