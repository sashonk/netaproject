package com.me.neta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Context {
	
	{

		properties = Collections.unmodifiableCollection(Arrays.asList(new ContextProperty[]{new ContextProperty(ContextProperty.PREPARED, false),
				new ContextProperty(ContextProperty.WORKING, false),
				new ContextProperty(ContextProperty.HALT, false)
		
		}));
		
		listeners = new LinkedList<ContextListener>();
	}
	
	public void registerListener(final ContextListener listener){
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}
	
	public void removeListener(final ContextListener listener){
		listeners.remove(listener);
	}

	public void setProperty(String property, boolean value){
		if(property==null){
			throw new NullPointerException();
		}
		
		Iterator<ContextProperty> iter = properties.iterator();
		while(iter.hasNext()){
			ContextProperty prop = iter.next();
			if(property.equals(prop.getName())){
				boolean oldVal = prop.getValue();
				prop.setValue(value);
				if(oldVal!=value){
					notifyListeners();
				}
				
				return;
			}
		}
		
		throw new IllegalArgumentException(new StringBuilder("property '").append(property).append("' not found").toString());

	}
	
	public boolean getProperty(String name){
		if(name==null){
			throw new NullPointerException();
		}
		
		Iterator<ContextProperty> iter = properties.iterator();
		while(iter.hasNext()){
			ContextProperty prop = iter.next();
			if(name.equals(prop.getName())){
				return prop.getValue();
			}
		}		
		
		throw new IllegalArgumentException(new StringBuilder("property '").append(name).append("' not found").toString());
	}


/*	public boolean prepared;
	public boolean working;
	public boolean halt;*/

	Collection<ContextProperty> properties;
	List<ContextListener> listeners ;
	
	public void notifyListeners(){
		for(ContextListener listener : listeners){
			listener.contextChanged(this);
		}
	}
	
	public static class ContextProperty{
		
		public static String PREPARED = "prepared";
		public static String WORKING = "working";
		public static String HALT = "halt";
		
		ContextProperty(String name, boolean value){
			this.name= name;
			this.value = value;
		}
		
		String getName(){
			return name;
		}
		
		boolean getValue(){
			return value;
		}
		
		void setValue(boolean value){
			this.value = value;
		}
		
		private boolean value;
		private String name;
	}
}
