package com.gdx.gdx_G.tween;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.TweenAccessor;

public class ParticleAccessor implements TweenAccessor<ParticleEffect> {
	public static final int POSITION_XY = 1;
	@Override
	public int getValues(ParticleEffect target, int tweenType, float[] returnValues) {
		switch (tweenType) {
				
		case POSITION_XY:
			returnValues[0] = target.getEmitters().get(0).getX();
			returnValues[1] = target.getEmitters().get(0).getY();
			return 2;
			
		}

		assert false;
		return -1;
	}

	@Override
	public void setValues(ParticleEffect target, int tweenType, float[] newValues) {
		switch (tweenType) {
		
		case POSITION_XY:
			target.setPosition(newValues[0],newValues[1]);
			target.start();
			
			break;
		}
	}

}