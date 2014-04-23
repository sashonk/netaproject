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

		properties = Collections.unmodifiableCollection(Arrays.asList(new ContextProperty[]{
				new ContextProperty(ContextProperty.PREPARED, null),
				new ContextProperty(ContextProperty.WORKING, null),
				new ContextProperty(ContextProperty.HALT, null),
				new ContextProperty(ContextProperty.GAME_END, null),
				new ContextProperty(ContextProperty.INGAME, null),
				new ContextProperty(ContextProperty.CELLARS, null),
				new ContextProperty(ContextProperty.LETTERS, null),
				new ContextProperty(ContextProperty.LETTER_ON, null),
				new ContextProperty(ContextProperty.ACTIVE_LETTER, null),
				new ContextProperty(ContextProperty.BETWEEN_CELLARS, null),
				new ContextProperty(ContextProperty.POPUP, null)

		
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

	public void setProperty(String property, Object value){
		if(property==null){
			throw new NullPointerException();
		}
		
		Iterator<ContextProperty> iter = properties.iterator();
		while(iter.hasNext()){
			ContextProperty prop = iter.next();
			if(property.equals(prop.getName())){
				Object oldVal = prop.getValue();
				prop.setValue(value);
				if(!(oldVal==null && value==null) && !(oldVal!=null && oldVal.equals(value)) && !(value!=null && value.equals(oldVal)) ){
					notifyListeners();
				}
				
				return;
			}
		}
		
		throw new IllegalArgumentException(new StringBuilder("property '").append(property).append("' not found").toString());

	}
	
	public Object getProperty(String name){
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
		public static String CELLARS = "cellars";		
		public static String LETTERS = "letters";		
		public static String INGAME = "ingame";		
		public static String LETTER_ON = "letter_on";
		public static String GAME_END ="game_end";
		public static String ACTIVE_LETTER ="active_letter";
		public static String BETWEEN_CELLARS ="between_cellars";
		public static String POPUP ="popup";

		

		
		
		ContextProperty(String name, Object value){
			this.name= name;
			this.value = value;
		}
		
		String getName(){
			return name;
		}
		
		Object getValue(){
			return value;
		}
		
		void setValue(Object value){
			this.value = value;
		}
		
		private Object value;
		private String name;
	}
}
