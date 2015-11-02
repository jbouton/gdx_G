package com.gdx.gdx_G.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Laser Class
 * @author Trenton Shaffer
 */
public class Laser {
	
	public Vector2 position = new Vector2() ;
	public float distance;
	public Color color = new Color(Color.RED);
	public Color rayColor = new Color(Color.WHITE);
	public float degrees;
	public Sprite begin1,begin2,mid1,mid2,end1,end2;


	public void render(float delta,SpriteBatch spriteBatch) {
		mid1.setSize(mid1.getWidth(), distance);
		mid2.setSize(mid1.getWidth(), distance);

		begin1.setPosition(position.x, position.y);
		begin2.setPosition(position.x, position.y);

		mid1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());
		mid2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());

		end1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());
		end2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());

		begin1.setOrigin(begin1.getWidth()/2, 0);
		begin2.setOrigin(begin1.getWidth()/2, 0);


		mid1.setOrigin(mid1.getWidth()/2, -begin1.getHeight());
		mid2.setOrigin(mid2.getWidth()/2, -begin1.getHeight());
		end1.setOrigin(mid1.getWidth()/2, -begin1.getHeight()-mid1.getHeight());
		end2.setOrigin(mid2.getWidth()/2, -begin1.getHeight()-mid2.getHeight());


		begin1.setRotation(degrees);
		begin2.setRotation(degrees);
		mid1.setRotation(degrees);
		mid2.setRotation(degrees);
		end1.setRotation(degrees);
		end2.setRotation(degrees);

		spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

		begin1.draw(spriteBatch);
		begin2.draw(spriteBatch);


		mid1.draw(spriteBatch);

		mid2.draw(spriteBatch);

		end1.draw(spriteBatch);
		end2.draw(spriteBatch);
		spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


	}
}
