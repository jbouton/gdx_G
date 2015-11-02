package com.gdx.gdx_G.tween;

import box2dLight.PointLight;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.TweenAccessor;

public class LightAccessor implements TweenAccessor<PointLight> {
	public static final int DISTANCE = 1;
	@Override
	public int getValues(PointLight target, int tweenType, float[] returnValues) {
		switch (tweenType) {
				
		case DISTANCE:
			returnValues[0] = target.getDistance();
			return 2;
			
		}

		assert false;
		return -1;
	}

	@Override
	public void setValues(PointLight target, int tweenType, float[] newValues) {
		switch (tweenType) {
		
		case DISTANCE:
			target.setDistance(newValues[0]);
				break;
		}
	}

}