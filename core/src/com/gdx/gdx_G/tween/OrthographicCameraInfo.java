package com.gdx.gdx_G.tween;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class OrthographicCameraInfo extends OrthographicCamera{
	
	private float tViewPortWidth,tViewPortHeight;

	
	public float gettViewPortWidth() {
		return tViewPortWidth;
	}

	public void settViewPortWidth(float tViewPortWidth) {
		this.tViewPortWidth = tViewPortWidth;
	}

	public float gettViewPortHeight() {
		return tViewPortHeight;
	}

	public void settViewPortHeight(float tViewPortHeight) {
		this.tViewPortHeight = tViewPortHeight;
	}
	

}
