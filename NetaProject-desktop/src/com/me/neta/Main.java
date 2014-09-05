package com.me.neta;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		System.out.println(Character.valueOf('c').equals(Character.valueOf('c')));
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Run this game for kids on PC";
		cfg.useGL20 = true;
		cfg.fullscreen = false;
		cfg.width = 1024;
		cfg.height = 768;
		
		new LwjglApplication(new NetaGame(new WinNative()), cfg);
	}
}
