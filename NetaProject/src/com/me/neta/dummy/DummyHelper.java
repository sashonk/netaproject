package com.me.neta.dummy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import sun.awt.image.ByteArrayImageSource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.neta.NetaGame;
import com.me.neta.Size;
import com.me.neta.World;
//import com.me.neta.dummy.Dummy.DummyType;
import com.me.neta.dummy.DummyContext.DummyInfo;
import com.me.neta.dummy.DummyContext.GroupInfo;

public class DummyHelper {
	
	 DummyContext dummyContext;
	 DummyForm form;
	 NetaGame ng;
	 World world;
	 
	 public DummyContext getContext(){
		 return dummyContext;
	 }
	 
	 public DummyForm getForm(){
		 return form;
	 }
	 
	 Map<Integer, String> mm;
	// Map<String, DummyType> typeMap;

	 
	 public DummyHelper(NetaGame ng, World wor){
		 this.ng = ng;
		 this.world = wor;
		 form = new DummyForm(ng);
		 form.setVisible(false);
		 world.addActor(form);
		 dummyContext = new DummyContext();
		 
			
			mm = new HashMap<Integer, String>();
			mm.put(Keys.NUM_1, "DOM1");
			mm.put(Keys.NUM_2, "DOM2");
			mm.put(Keys.NUM_3, "DOM3");
			mm.put(Keys.NUM_4, "DOM4");
			mm.put(Keys.Q, "FLOWER1");
			mm.put(Keys.W, "FLOWER2");
			mm.put(Keys.E, "FLOWER3");
			mm.put(Keys.T, "barrier");
			mm.put(Keys.G, "start");
/*
			
			typeMap = new HashMap<String, DummyType>();
			typeMap.put("DOM1", DummyType.HOUSE);
			typeMap.put("DOM2", DummyType.HOUSE);
			typeMap.put("DOM3", DummyType.HOUSE);
			typeMap.put("DOM4", DummyType.HOUSE);

			typeMap.put( "FLOWER1", DummyType.FLOWER);sssss
			typeMap.put( "FLOWER2", DummyType.FLOWER);
			typeMap.put( "FLOWER3", DummyType.FLOWER);
			typeMap.put("barrier", DummyType.BARRIER);
			typeMap.put("start", DummyType.START);*/
	 }

	
	public  void handleInput(float x, float y) {		
		for(Integer key : mm.keySet()){
			if(Gdx.input.isKeyPressed(key)){
				String regionName = mm.get(key);
				TextureRegion reg = ng.getManager().getAtlas().findRegion(regionName);
				Dummy dummy = new  Dummy(ng, reg, this);
				dummy.setType(regionName);
				dummy.setSize(reg.getRegionWidth(), reg.getRegionHeight());
				dummy.setPosition(x, y);
				world.addActor(dummy);
				dummy.setName("");
			}			
		}
		
		if(Gdx.input.isKeyPressed(Keys.P)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom += 0.05f;
			camera.position.set(x, y, 0);
			constrainPosition(camera, world.getStage() );
			constrainZoom(camera);
		}
		
		if(Gdx.input.isKeyPressed(Keys.U)){
			OrthographicCamera camera  = (OrthographicCamera) world.getStage().getCamera();
			camera.zoom=1;
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
			dummyContext.setDummies(groups);
			
			Map<Integer, GroupInfo> groupsMap = new HashMap<Integer, DummyContext.GroupInfo>();
			
			for(Actor dummy : world.getChildren()){
				if(!(dummy instanceof Dummy)){
					continue;
				}
				Dummy dm = (Dummy)dummy;
				
				if(dm.getType().contains("DOM")){
					GroupInfo gInfo = new GroupInfo();
					groupsMap.put(Integer.valueOf(dm.getGroup()), gInfo);		
					gInfo.setOrder(dm.getGroup());
					gInfo.setZoom(dm.getZoom());
					gInfo.setOrigin(dm.getGroupOrigin());
					groups.add(gInfo);
				}				
			}
			
			
			for(Actor dummy : world.getChildren()){
				if(!(dummy instanceof Dummy)){
					continue;
				}
				Dummy dm = (Dummy)dummy;
				
				DummyInfo info = new DummyInfo();
				info.setName(dummy.getName());
				info.setX(dummy.getX());
				info.setY(dummy.getY());
				info.setWidth(dummy.getWidth());
				info.setHeight(dummy.getHeight());
				info.setType(dm.getType());
				
				GroupInfo gInfo = groupsMap.get(dm.getGroup());
				List<DummyInfo> flowers = gInfo.getFlowers();
				if(flowers==null){
					flowers = new LinkedList<DummyContext.DummyInfo>();
					gInfo.setFlowers(flowers);
				}
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
				oos.writeObject(dummyContext);
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
			
			System.out.println("SAVED");
		}


		if(Gdx.input.isKeyPressed(Keys.R)){
			ObjectInputStream ois = null;
			try{
				
				
				FileHandle dummyFile = Gdx.files.absolute("D:\\dummy.txt");//Gdx.files.internal("data/dummy/dummy-"+world.getTitle()+".ser");				
				ois = new ObjectInputStream(new ByteArrayInputStream(dummyFile.readBytes()));
				dummyContext = (DummyContext) ois.readObject();
				
				List<Actor> rm = new ArrayList<Actor>( world.getChildren().size);
				Iterator<Actor> iter = world.getChildren().iterator();
				while(iter.hasNext()){
					Actor a = iter.next();
					if(a instanceof Dummy){
						rm.add(a);
					}
				}
				for(Actor a: rm){
					a.remove();
				}
				
				for(GroupInfo gInfo : dummyContext.getGroups()){
					
					for(DummyInfo info : gInfo.getFlowers()){
						Dummy dummy = new  Dummy(ng, ng.getManager().getAtlas().findRegion(info.getType()), this);
						dummy.setSize(info.getWidth(), info.getHeight());
						dummy.setPosition(info.getX(), info.getY());
						dummy.setName(info.getName());
						dummy.setGroup(gInfo.getOrder());
						dummy.setType(info.getType());
						if(info.getType().contains("DOM")){
							dummy.setGroupOrigin(gInfo.getOrigin());
							dummy.setZoom(gInfo.getZoom());
						}
						
						world.addActor(dummy);		
					}
				
				}
				

			}
			catch(Exception ex){
				System.err.println(ex);
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
			
			System.out.println("READ");
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
