package com.me.neta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public abstract class Moveable extends Actor{

	protected float dragStartX ;
	protected float dragStartY ;
	
	protected float sx ;
	protected float sy ;
	
	public abstract boolean isDisposable();
	
}
