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

public class MagnetTarget {
	private World world;
	private float x, y,radius;
	public Body body, shield,laser;
	public String spriteName;
	public SpriteInfo sprite,shieldSprite,laserSprite;
	private Assets assets;
	private boolean bShield;
	private static final float WB = 0.01f;
	private static final float BW = 100f;
	final short CATEGORY_MAGNET = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short CATEGORY_SHIELD = 0x0008;
	final short MASK_MAGNET = CATEGORY_BLOCK;
	final short MASK_ENEMY = CATEGORY_BLOCK;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_MAGNET;
	final short MASK_SHIELD = CATEGORY_ENEMY;
	RevoluteJointDef revoluteJointDef = new RevoluteJointDef();

	public MagnetTarget(World world, float x, float y, Assets assets) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
		this.bShield=bShield;
		this.radius=radius;
	
	}

	public void createMagnet() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		sprite = new SpriteInfo(assets.getSprite("magnet"));
		bodyDef.position.x = x + sprite.getWidth() / 2 * WB;
		bodyDef.position.y = y + sprite.getHeight() / 2 * WB;
		bodyDef.fixedRotation = true;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(.1f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.filter.categoryBits = this.CATEGORY_MAGNET;
		fixtureDef.filter.maskBits = this.MASK_MAGNET;
		fixtureDef.density = .5f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setLinearDamping(0);
		body.isFixedRotation();
		body.setGravityScale(0);

		sprite.setName("target");
		body.setUserData(sprite);
		
		
		
		
		
	
	}

}
