package com.me.neta;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Was ist das?";
		cfg.useGL20 = false;
	//	cfg.fullscreen = true;
		cfg.width = 300;
		cfg.height = 200;
		
		new LwjglApplication(new NetaGame(new WinPlatform()), cfg);
	}
}
