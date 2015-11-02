package com.gdx.gdx_G.box2dObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.assets.SpriteInfo;

public class Shield {
	private World world;
	private float x, y, radius;
	public Body body,h1;
	public SpriteInfo sprite;
	private Assets assets;
	final short CATEGORY_MAGNET = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short CATEGORY_SHIELD = 0x0008;
	final short MASK_MAGNET= CATEGORY_BLOCK;
	final short MASK_ENEMY = CATEGORY_BLOCK;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_MAGNET;
	final short MASK_SHIELD = -1;

	public Shield(World world, float x, float y, Assets assets, float radius) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
		this.radius = radius;
	}

	public void createShield() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.filter.categoryBits=this.CATEGORY_BLOCK;
		fixtureDef.filter.maskBits=this.MASK_BLOCK;
		fixtureDef.density=10.5f;
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("shield"));
		sprite.setScale(radius);
		sprite.setName("shield");
		body.setUserData(sprite);
		body.setGravityScale(-10);
	}

	
}
