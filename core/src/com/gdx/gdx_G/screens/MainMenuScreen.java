package com.gdx.gdx_G.screens;

import com.badlogic.gdx.Screen;
import com.gdx.gdx_G.Gdx_G;


public class MainMenuScreen implements Screen{

	private Gdx_G game;
	
	public MainMenuScreen(Gdx_G game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		System.out.println("loading menuScreen");
		game.setScreen(game.playScreen);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
