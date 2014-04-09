package com.me.neta.dummy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.dummy.DummyContext.DummyInfo;
import com.me.neta.dummy.DummyContext.GroupInfo;

public class DummyHelper {
	public static void handleInput(NetaGame ng, Group world, float x, float y) {
		DummyContext context = new DummyContext();
		context.setOrigin(new Size(x, y));
		context.setZoom(((OrthographicCamera)ng.getWorkspace().getStage().getCamera()).zoom);
		
		Map<Integer, String> mm = new HashMap<Integer, String>();
		mm.put(Keys.NUM_1, "DOM1.");
		mm.put(Keys.NUM_2, "DOM2.");
		mm.put(Keys.NUM_3, "DOM3");
		mm.put(Keys.NUM_4, "DOM4");
		mm.put(Keys.Q, "ZV1");
		mm.put(Keys.W, "ZV2");
		mm.put(Keys.E, "ZV3");
		
		for(Integer key : mm.keySet()){
			if(Gdx.input.isKeyPressed(key)){
				TextureRegion reg = ng.getManager().getAtlas().findRegion(mm.get(key));
				Dummy dummy = new  Dummy(ng, reg);
				dummy.setSize(reg.getRegionWidth(), reg.getRegionHeight());
				dummy.setPosition(x, y);
				world.addActor(dummy);
				dummy.setName(mm.get(key));
			}			
		}
		
		if(Gdx.input.isKeyPressed(Keys.P)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom += 0.05f;
			camera.position.set(x, y, 0);
			constrainPosition(camera, world.getStage() );
			constrainZoom(camera);
		}
		
		if(Gdx.input.isKeyPressed(Keys.O)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom -= 0.05f;
			camera.position.set(x, y, 0);
			constrainPosition(camera, world.getStage() );
			constrainZoom(camera);
		}
		
		if(Gdx.input.isKeyPressed(Keys.K)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom += 0.01f;
			camera.position.set(x, y, 0);
			constrainPosition(camera, world.getStage() );
			constrainZoom(camera);
		}
		
		if(Gdx.input.isKeyPressed(Keys.L)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom -= 0.01f;
			camera.position.set(x, y, 0);
			constrainPosition(camera, world.getStage() );
			constrainZoom(camera);
		}

		if(Gdx.input.isKeyPressed(Keys.S)){

			List<GroupInfo> groups = new LinkedList<DummyContext.GroupInfo>();
			context.setDummies(groups);
			GroupInfo group = new GroupInfo();		
			groups.add(group);
			List<DummyInfo> flowers = new LinkedList<DummyContext.DummyInfo>();
			group.setFlowers(flowers);
			
			for(Actor dummy : world.getChildren()){
				if(!(dummy instanceof Dummy)){
					continue;
				}
				
				DummyInfo info = new DummyInfo();
				info.setName(dummy.getName());
				info.setX(dummy.getX());
				info.setY(dummy.getY());
				info.setWidth(dummy.getWidth());
				info.setHeight(dummy.getHeight());
				
				flowers.add(info);
			}
			
			ObjectOutputStream oos = null;
			try{
				File dummyFile = new File("D:\\dummy.txt");
				if(dummyFile.exists()){
					File backup = new File(dummyFile.getAbsolutePath());
					backup.renameTo( new File(dummyFile.getAbsolutePath()+System.currentTimeMillis()+".back"));
				}
				dummyFile.delete();
				oos = new ObjectOutputStream(new FileOutputStream(dummyFile));
				oos.writeObject(context);
			}
			catch(Exception ex){
				System.err.println(ex);
			}
			finally{
				if(oos!=null){
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}


		if(Gdx.input.isKeyPressed(Keys.R)){
			ObjectInputStream ois = null;
			try{
				File dummyFile = new File("D:\\dummy.txt");
				ois = new ObjectInputStream(new FileInputStream(dummyFile));
				 context = (DummyContext) ois.readObject();
				
				GroupInfo g = context.getGroups().get(0);
				List<DummyInfo> flowers = g.getFlowers();
				for(DummyInfo info : flowers){
					Dummy dummy = new  Dummy(ng, ng.getManager().getAtlas().findRegion(info.getName()));
					dummy.setSize(info.getWidth(), info.getHeight());
					dummy.setPosition(info.getX(), info.getY());
					dummy.setName(info.getName());
					world.addActor(dummy);
				}
			}
			catch(Exception ex){
				
			}
			finally{
				if(ois!=null)
				{
					try {
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
	}
	
	private static void constrainPosition(OrthographicCamera cam, Stage s){
		
		float vw = cam.viewportWidth*.5f*cam.zoom;
		float vh = cam.viewportHeight*.5f*cam.zoom;
		
		if(cam.position.x - vw<0){
			cam.position.x = vw;
		}
		if(cam.position.y - vh< 0){
			cam.position.y = vh;
		}
		if(cam.position.x + vw > s.getWidth()){
			cam.position.x = s.getWidth() - vw;
		}
		if(cam.position.y + vh > s.getHeight()){
			cam.position.y = s.getHeight() - vh;
		}
	}
	
	private static float Zmin = 0.1f;
	private static void constrainZoom(OrthographicCamera cam){
		if(cam.zoom<Zmin){
			cam.zoom = Zmin;
		}
		if(cam.zoom>1){
			cam.zoom = 1;
		}
	}
}
