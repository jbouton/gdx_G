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
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.assets.SpriteInfo;

public class Magnet {
	private World world;
	private float x, y,radius;
	public Body body, shield,laser,engine1,engine2;
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

	public Magnet(World world, float x, float y, Assets assets,float radius, boolean bShield) {
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
		sprite = new SpriteInfo(assets.getSprite("ship3"));
		bodyDef.position.x = x + sprite.getWidth() / 2 * WB;
		bodyDef.position.y = y + sprite.getHeight() / 2 * WB;
		bodyDef.fixedRotation = true;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.filter.categoryBits = this.CATEGORY_BLOCK;
		fixtureDef.filter.maskBits = this.MASK_BLOCK;
		fixtureDef.density = .5f;
		

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setLinearDamping(0);
		body.isFixedRotation();
		body.setGravityScale(0);
		body.applyLinearImpulse(new Vector2(-1,-1), body.getWorldCenter(), true);

		sprite.setName("magnet");
		body.setUserData(sprite);
		
			BodyDef bodyDef1 = new BodyDef();
			bodyDef1.type = BodyType.DynamicBody;
			
			bodyDef1.position.x = x + sprite.getWidth() / 2 * WB;
			bodyDef1.position.y = y + sprite.getHeight() / 2 * WB;
			bodyDef1.fixedRotation = true;
			CircleShape circleShape1 = new CircleShape();
			circleShape1.setRadius(.1f);
			FixtureDef fixtureDef1 = new FixtureDef();
			fixtureDef1.shape = circleShape1;
			fixtureDef1.filter.categoryBits = this.CATEGORY_BLOCK;
			fixtureDef1.filter.maskBits = this.MASK_BLOCK;
			fixtureDef1.density = .5f;

			laser = world.createBody(bodyDef1);
			laser.setGravityScale(0);
			laser.createFixture(fixtureDef1);
			laser.setLinearDamping(0);
			laser.isFixedRotation();
			
		//	laserSprite = new SpriteInfo(assets.getSprite("laser"));
			
		//	laserSprite.setName("laser");
			//sprite.setAlpha(0);
		//	laser.setUserData(laserSprite);
			revoluteJointDef = new RevoluteJointDef();
			revoluteJointDef.bodyA = laser;
			revoluteJointDef.bodyB = body;
			revoluteJointDef.localAnchorA.y = .0f;
			revoluteJointDef.localAnchorA.x =0f;
			revoluteJointDef.localAnchorB.x = -1f;
			revoluteJointDef.localAnchorB.y = 0;
			world.createJoint(revoluteJointDef);
			
			engine1 = world.createBody(bodyDef1);
			engine1.setGravityScale(0);
			engine1.createFixture(fixtureDef1);
			engine1.setLinearDamping(0);
			engine1.isFixedRotation();
			
			revoluteJointDef = new RevoluteJointDef();
			revoluteJointDef.bodyA = engine1;
			revoluteJointDef.bodyB = body;
			revoluteJointDef.localAnchorA.y = .0f;
			revoluteJointDef.localAnchorA.x =0f;
			revoluteJointDef.localAnchorB.x = .75f;
			revoluteJointDef.localAnchorB.y = .5f;
			world.createJoint(revoluteJointDef);
			
			engine2 = world.createBody(bodyDef1);
			engine2.setGravityScale(0);
			engine2.createFixture(fixtureDef1);
			engine2.setLinearDamping(0);
			engine2.isFixedRotation();
			
			revoluteJointDef = new RevoluteJointDef();
			revoluteJointDef.bodyA = engine2;
			revoluteJointDef.bodyB = body;
			revoluteJointDef.localAnchorA.y = .0f;
			revoluteJointDef.localAnchorA.x =0f;
			revoluteJointDef.localAnchorB.x = .75f;
			revoluteJointDef.localAnchorB.y = -.5f;
			world.createJoint(revoluteJointDef);
			
			
			
			BodyDef bodyDef2 = new BodyDef();
			bodyDef2.type = BodyType.DynamicBody;
			
			bodyDef2.position.x = x + sprite.getWidth() / 2 * WB;
			bodyDef2.position.y = y + sprite.getHeight() / 2 * WB;
			//bodyDef2.fixedRotation = true;
			CircleShape circleShape2= new CircleShape();
			circleShape2.setRadius(radius*2);
			FixtureDef fixtureDef2 = new FixtureDef();
			fixtureDef2.shape = circleShape2;
			fixtureDef2.filter.categoryBits = this.CATEGORY_BLOCK;
			fixtureDef2.filter.maskBits = this.MASK_BLOCK;
			fixtureDef2.density = .5f;

			shield = world.createBody(bodyDef2);
			shield.createFixture(fixtureDef2);
			shield.setLinearDamping(0);
			shield.isFixedRotation();
			shield.setGravityScale(0);
			
			shieldSprite = new SpriteInfo(assets.getSprite("shield"));
			
			shieldSprite.setName("shieldMagnet");
			shieldSprite.setAlpha(0);
			//shieldSprite.setColor(Color.GREEN);
			shield.setUserData(shieldSprite);
			revoluteJointDef = new RevoluteJointDef();
			revoluteJointDef.bodyA = shield;
			revoluteJointDef.bodyB = body;
			revoluteJointDef.localAnchorA.y = .0f;
			revoluteJointDef.localAnchorA.x = .0f;
			revoluteJointDef.localAnchorB.x = 0;
			revoluteJointDef.localAnchorB.y = 0;
			world.createJoint(revoluteJointDef);

	
	}

}
