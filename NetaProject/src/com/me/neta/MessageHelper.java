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
import com.me.neta.Context.ContextProperty;
import com.me.neta.events.QuestionEvent;
import com.me.neta.util.WorkHelper;

public class MessageHelper {
	

	
	public static void message(NetaGame ng, String text){
		hide(ng);
		
		TextureManager tm = ng.getManager();
		Skin skin = tm.getSkin();

		Group workspace =	ng.getWorkspace();
		Image curtain = new Image(ng.getManager().getUnmanaged(new Color(0,0,0,0.3f)));
		curtain.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		TextButton tb = new TextButton(text, skin, "system");
		Group group = new Group();
		group.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		group.addActor(curtain);
		group.addActor(tb);
		workspace.addActor(group);
		group.setZIndex(110);
		group.setName("message");
		WorkHelper.center(tb);
		//tb.fire(new WorkspaceStateEvent(WorkspaceState.DISABLED));

		ng.getContext().setProperty(ContextProperty.HALT, true);
	}
	
	
	public static void error(NetaGame ng,String text, Throwable t){
		hide(ng);
		
		
		TextureManager tm = ng.getManager();
		Skin skin = tm.getSkin();
		
		//Label label = new Label(text, s, "system");
		
		StringBuilder sb = new StringBuilder();
		sb.append(text);
		if(t!=null){
			sb.append('\n');
			sb.append(toString(t));
		}
		
		Group workspace =	ng.getWorkspace();
		Image curtain = new Image(ng.getManager().getUnmanaged(new Color(0,0,0,0.3f)));
		curtain.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		TextButton tb = new TextButton(sb.toString(), skin, "error");
		Group group = new Group();
		group.setBounds(0, 0, workspace.getWidth(), workspace.getHeight());
		group.addActor(curtain);
		group.addActor(tb);
		WorkHelper.center(tb);
		workspace.addActor(group);
		group.setZIndex(110);
		group.setName("message");
		
		group.addAction(Actions.delay(10, Actions.removeActor()));

		ng.getContext().setProperty(ContextProperty.HALT, true);
	}
	
	public static void notify(NetaGame ng, String text){
		hide(ng);
		
		
		TextureManager tm = ng.getManager();
		Skin skin = tm.getSkin();
		
		//Label label = new Label(text, s, "system");
		TextButton tb = new TextButton(text, skin, "system");
		tb.setName("message");
		 Group workspace =	ng.getWorkspace();
		 workspace.addActor(tb);
		tb.setZIndex(110);
		WorkHelper.center(tb);
		
		tb.addAction(Actions.sequence(Actions.alpha(0, 5), Actions.removeActor()));
	}
	
	
	public static void hide(NetaGame ng){
		 Group workspace =	ng.getWorkspace();
		Actor msgBtn = workspace.findActor("message");
		if(msgBtn!=null){
			
			//msgBtn.fire(new WorkspaceStateEvent(WorkspaceState.WORKING));
			
			ng.getContext().setProperty(ContextProperty.HALT, false);
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
