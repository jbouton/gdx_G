package com.gdx.gdx_G.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import box2dLight.PointLight;

public class SpriteInfo extends Sprite{
	
	
	private String name;

	public PointLight getP() {
		return p;
	}

	public void setP(PointLight p) {
		this.p = p;
	}

	private PointLight p;

	private int frameIndex,frameInterval,frameIntervalcounter,frameCount;
	
	
	public int getFrameIndex() {
		return frameIndex;
	}


	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}


	


	@Override
	public float getX() {
		return (int)super.getX();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public SpriteInfo() {
		super();
	}

	public SpriteInfo(Sprite sprite) {
		super(sprite);
	}

	public SpriteInfo(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
		super(texture, srcX, srcY, srcWidth, srcHeight);
	}

	public SpriteInfo(Texture texture, int srcWidth, int srcHeight) {
		super(texture, srcWidth, srcHeight);
	}

	public SpriteInfo(Texture texture) {
		super(texture);
	}

	public SpriteInfo(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
		super(region, srcX, srcY, srcWidth, srcHeight);
	}

	public SpriteInfo(TextureRegion region) {
		super(region);
	}


	public int getFrameInterval() {
		return frameInterval;
	}


	public void setFrameInterval(int frameInterval) {
		this.frameInterval = frameInterval;
	}


	public int getFrameIntervalcounter() {
		return frameIntervalcounter;
	}


	public void setFrameIntervalcounter(int frameIntervalcounter) {
		this.frameIntervalcounter = frameIntervalcounter;
	}


	public int getFrameCount() {
		return frameCount;
	}


	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}


	
	


}
