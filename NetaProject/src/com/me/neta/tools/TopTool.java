package com.me.neta.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.neta.NetaGame;
import com.me.neta.Size;

public abstract class TopTool extends AbstractTool{

	public TopTool(NetaGame ng) {
		super(ng);
	}

	@Override
	public Size getSize() {
		return new Size(60, 60);
	}


}
