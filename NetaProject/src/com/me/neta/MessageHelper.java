package com.me.neta;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.me.neta.events.WorkspaceStateEvent;
import com.me.neta.util.WorkHelper;
import com.me.neta.util.WorkspaceState;

public class MessageHelper {
	public static void message(String text){
		hide();
		
		TextureManager tm = TextureManager.get();
		Skin skin = tm.getSkin();

		Group workspace =	(Group) NetaGame.stage.getRoot().findActor("workspace");
		Image curtain = new Image(TextureManager.get().getUnmanaged(new Color(0,0,0,0.3f)));
		curtain.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		TextButton tb = new TextButton(text, skin, "system");
		Group group = new Group();
		group.addActor(curtain);
		group.addActor(tb);
		WorkHelper.center(tb);
		workspace.addActor(group);
		group.setZIndex(110);
		group.setName("message");
		//tb.fire(new WorkspaceStateEvent(WorkspaceState.DISABLED));

		
	}
	
	
	public static void error(String text, Throwable t){
		hide();
		
		
		TextureManager tm = TextureManager.get();
		Skin skin = tm.getSkin();
		
		//Label label = new Label(text, s, "system");
		
		StringBuilder sb = new StringBuilder();
		sb.append(text);
		if(t!=null){
			sb.append('\n');
			sb.append(toString(t));
		}
		
		Group workspace =	(Group) NetaGame.stage.getRoot().findActor("workspace");
		Image curtain = new Image(TextureManager.get().getUnmanaged(new Color(0,0,0,0.3f)));
		curtain.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		TextButton tb = new TextButton(sb.toString(), skin, "error");
		Group group = new Group();
		group.addActor(curtain);
		group.addActor(tb);
		WorkHelper.center(tb);
		workspace.addActor(group);
		group.setZIndex(110);
		group.setName("message");

		//tb.fire(new WorkspaceStateEvent(WorkspaceState.DISABLED));	
	}
	
	public static void notify(String text){
		hide();
		
		
		TextureManager tm = TextureManager.get();
		Skin skin = tm.getSkin();
		
		//Label label = new Label(text, s, "system");
		TextButton tb = new TextButton(text, skin, "system");
		tb.setName("message");
		 Group workspace =	(Group) NetaGame.stage.getRoot().findActor("workspace");
		 workspace.addActor(tb);
		tb.setZIndex(110);
		WorkHelper.center(tb);
		
		tb.addAction(Actions.sequence(Actions.alpha(0, 5), Actions.removeActor()));
	}
	
	
	public static void hide(){
		 Group workspace =	(Group) NetaGame.stage.getRoot().findActor("workspace");
		Actor msgBtn = workspace.findActor("message");
		if(msgBtn!=null){
			
			//msgBtn.fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
			msgBtn.remove();
		}
		
		
	}

	
	public static String toString(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}
}
