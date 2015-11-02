package com.gdx.gdx_G.assets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Assets {
	private TextureAtlas atlas;
	private TextureRegion texReg;
	private Map<String, SpriteInfo> spriteMap;
	public Assets() {
		String[] spriteList = { "magnet","laser","shield","ball","ship3","bg"};
		atlas = new TextureAtlas(Gdx.files.internal("data/laserPack.pack"));
		spriteMap = new HashMap<String, SpriteInfo>();
		for (int i = 0; i < spriteList.length; i++) {
			loadSprites(spriteList[i]);
		}
		for (int i = 0; i < 15; i++) {
			loadSprites("crabd-"+i);
		}

	}

	public void loadSprites(String spriteName) {
		//texReg = fixBleeding(atlas.findRegion(spriteName));
		
		texReg = atlas.findRegion(spriteName);
		System.out.println(spriteName);
		SpriteInfo tempSprite = new SpriteInfo(texReg);
		//tempSprite.setScale(tempSprite.getScaleX()*screenRatio,tempSprite.getScaleY()*screenRatio);
		//tempSprite.setScale(aRatio);
		tempSprite.setName(spriteName);
		
		spriteMap.put(spriteName, tempSprite);
	}
	
	public SpriteInfo getSprite(String spriteName){
		
		SpriteInfo tempSprite = spriteMap.get(spriteName);
		return tempSprite;
		
	}
	public TextureAtlas getAtlas() {
		return atlas;
	}
	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

}
