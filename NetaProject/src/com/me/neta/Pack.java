package com.me.neta;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Pack {
    public static void main (String[] args) throws Exception {
    	String []dirs = {"main", "fields", "misc", "fig"};    	 
  
    	for(String dir : dirs){
    		TexturePacker2.process("D:\\topack\\"+dir, "D:\\topack\\"+dir+"\\packed", dir+".pack");
    	}
    }
}
