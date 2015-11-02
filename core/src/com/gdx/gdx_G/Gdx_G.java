package com.gdx.gdx_G;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.gdx_G.screens.MainMenuScreen;
import com.gdx.gdx_G.screens.PlayScreen;
import com.gdx.gdx_G.screens.SplashScreen;
public class Gdx_G extends Game {

	public MainMenuScreen mainMenuScreen;
	public PlayScreen playScreen;
	public SplashScreen splashScreen;
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		playScreen = new PlayScreen(this);
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);


	}

	@Override
	public void dispose() {
		super.dispose();

	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
