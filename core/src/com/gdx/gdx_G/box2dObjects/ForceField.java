package com.gdx.gdx_G.box2dObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.assets.SpriteInfo;


public class ForceField {
	private World world;
	private float x, y;
	private Body body;
	private SpriteInfo sprite;
	private Assets assets;

	public ForceField(World world) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets=assets;
	}


	public void createForceField() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		PolygonShape polygon = new PolygonShape();
		FixtureDef fixtureDef1 = new FixtureDef();
		fixtureDef1.shape = polygon;
		fixtureDef1.friction = 0f;
		fixtureDef1.density = 44.10f;
		fixtureDef1.restitution = 1f;
			//fixtureDef1.isSensor = true;
		fixtureDef1.filter.groupIndex = -1;
		
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef1);
		body.setGravityScale(0);
		
	
	}

	
	public void setShape(Body body,float[] vertices){
		PolygonShape polygon = new PolygonShape();
		polygon.set(vertices);
		FixtureDef fixtureDef1 = new FixtureDef();
		fixtureDef1.shape = polygon;
		fixtureDef1.friction = 0f;
		fixtureDef1.density = 44.10f;
		fixtureDef1.restitution = 1f;
			//fixtureDef1.isSensor = true;
		fixtureDef1.filter.groupIndex = -1;
		body.destroyFixture(body.getFixtureList().get(0));
		body.createFixture(fixtureDef1);
		
		
	}

	public SpriteInfo getSprite() {
		return sprite;
	}


	public void setSprite(SpriteInfo sprite) {
		this.sprite = sprite;
	}


	public Body getBody() {
		return body;
	}


	public void setBody(Body body) {
		this.body = body;
	}


}
