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
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.assets.SpriteInfo;

public class Diver {
	private World world;
	private float x, y;
	public SpriteInfo sprite;
	private Assets assets;
	public Body core, h1;
	public Body rA1;
	public Body rA12;
	public Body lA1;
	public Body lA12;
	public Body rL1;
	public Body rL12;
	public Body lL1;
	public Body lL12;
	public Body e1;
	public Body e2;
	final short CATEGORY_DIVER = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short MASK_DIVER = CATEGORY_BLOCK | CATEGORY_ENEMY;
	final short MASK_ENEMY = CATEGORY_BLOCK | CATEGORY_DIVER;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_DIVER;

	public Diver(World world, float x, float y, Assets assets) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
	}

	public void createDiver() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;

		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(.25f, .5f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boxShape;
		// fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = this.CATEGORY_DIVER;
		fixtureDef.filter.maskBits = this.MASK_DIVER;
		fixtureDef.density = 1;
		
		RevoluteJointDef wheeljointDef = new RevoluteJointDef();
		WeldJointDef weldjointDef = new WeldJointDef();
		// mech body
		core = world.createBody(bodyDef);
		//core.setLinearDamping(100f);
		//core.setAngularDamping(10);
		core.createFixture(fixtureDef);
		core.isFixedRotation();
		
		sprite = new SpriteInfo(assets.getSprite("core"));
		sprite.setName("diver");
		core.setUserData(sprite);

		// right arm s1
		boxShape.setAsBox(.1f, .1f);
		fixtureDef.shape = boxShape;

		rA1 = world.createBody(bodyDef);
		rA1.setLinearDamping(5);
		rA1.setSleepingAllowed(false);
		rA1.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("right_shoulder"));
		sprite.setName("diver");
		rA1.setUserData(sprite);

		wheeljointDef.enableLimit = true;
		wheeljointDef.collideConnected = false;
		wheeljointDef.upperAngle = (float) Math.toRadians(10);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-10);
		wheeljointDef.bodyA = core;
		wheeljointDef.bodyB = rA1;
		wheeljointDef.localAnchorA.y = -.1f;
		wheeljointDef.localAnchorA.x = .2f;
		wheeljointDef.localAnchorB.x = -.1f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// right arm s2
		boxShape.setAsBox(.2f, .1f);
		rA12 = world.createBody(bodyDef);
		rA12.setLinearDamping(5);
		rA12.setSleepingAllowed(false);
		rA12.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("right_arm"));
		sprite.setName("diver");
		rA12.setUserData(sprite);

		wheeljointDef = new RevoluteJointDef();
		wheeljointDef.collideConnected = false;
		wheeljointDef.enableLimit = true;
		wheeljointDef.upperAngle = (float) Math.toRadians(0);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-20);
		wheeljointDef.bodyA = rA1;
		wheeljointDef.bodyB = rA12;
		wheeljointDef.localAnchorA.y = 0;
		wheeljointDef.localAnchorA.x = 0f;
		wheeljointDef.localAnchorB.x = -.25f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// left arm s1
		boxShape.setAsBox(.1f, .1f);
		fixtureDef.shape = boxShape;

		lA1 = world.createBody(bodyDef);
		lA1.setLinearDamping(5);
		lA1.setSleepingAllowed(false);
		lA1.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("left_shoulder"));
		sprite.setName("diver");
		lA1.setUserData(sprite);

		wheeljointDef.enableLimit = true;
		wheeljointDef.collideConnected = false;
		wheeljointDef.upperAngle = (float) Math.toRadians(-160);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-180);
		wheeljointDef.bodyA = core;
		wheeljointDef.bodyB = lA1;
		wheeljointDef.localAnchorA.y = -.1f;
		wheeljointDef.localAnchorA.x = -.2f;
		wheeljointDef.localAnchorB.x = -.1f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// left arm s2
		boxShape.setAsBox(.2f, .1f);
		lA12 = world.createBody(bodyDef);
		lA12.setLinearDamping(5);
		lA12.setSleepingAllowed(false);
		lA12.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("left_arm"));
		sprite.setName("diver");
		lA12.setUserData(sprite);

		wheeljointDef = new RevoluteJointDef();
		wheeljointDef.collideConnected = false;
		wheeljointDef.enableLimit = true;
		wheeljointDef.upperAngle = (float) Math.toRadians(20);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-20);
		wheeljointDef.bodyA = lA1;
		wheeljointDef.bodyB = lA12;
		wheeljointDef.localAnchorA.y = 0;
		wheeljointDef.localAnchorA.x = 0f;
		wheeljointDef.localAnchorB.x = -.25f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// right leg s1
		boxShape.setAsBox(.4f, .1f);
		fixtureDef.shape = boxShape;

		rL1 = world.createBody(bodyDef);
		rL1.setLinearDamping(5);
		rL1.setSleepingAllowed(false);
		rL1.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("left_leg"));
		sprite.setName("diver");
		rL1.setUserData(sprite);

		wheeljointDef.enableLimit = true;
		wheeljointDef.collideConnected = false;
		wheeljointDef.upperAngle = (float) Math.toRadians(-70);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-90);
		wheeljointDef.bodyA = core;
		wheeljointDef.bodyB = rL1;
		wheeljointDef.localAnchorA.y = -.5f;
		wheeljointDef.localAnchorA.x = .1f;
		wheeljointDef.localAnchorB.x = -.2f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// left leg s1
		boxShape.setAsBox(.4f, .1f);
		fixtureDef.shape = boxShape;

		lL1 = world.createBody(bodyDef);
		lL1.setLinearDamping(5);
		lL1.setSleepingAllowed(false);
		lL1.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("right_leg"));
		sprite.setName("diver");
		lL1.setUserData(sprite);

		wheeljointDef.enableLimit = true;
		wheeljointDef.collideConnected = false;
		wheeljointDef.upperAngle = (float) Math.toRadians(-70);
		wheeljointDef.lowerAngle = (float) Math.toRadians(-90);
		wheeljointDef.bodyA = core;
		wheeljointDef.bodyB = lL1;
		wheeljointDef.localAnchorA.y = -.5f;
		wheeljointDef.localAnchorA.x = -.1f;
		wheeljointDef.localAnchorB.x = -.2f;
		wheeljointDef.localAnchorB.y = 0;
		world.createJoint(wheeljointDef);

		// engine 1
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(.25f);
		FixtureDef fixtureDef1 = new FixtureDef();
		fixtureDef1.shape = circleShape;
		fixtureDef1.isSensor = true;
		fixtureDef1.density = 1;

		e1 = world.createBody(bodyDef);
		e1.setSleepingAllowed(false);
		e1.createFixture(fixtureDef1);

		weldjointDef.bodyA = core;
		weldjointDef.bodyB = e1;
		weldjointDef.localAnchorA.y = -1f;
		weldjointDef.localAnchorA.x = -.2f;
		weldjointDef.localAnchorB.x = 0;
		weldjointDef.localAnchorB.y = 0;
		world.createJoint(weldjointDef);

		// engine 2
		e2 = world.createBody(bodyDef);
		e2.setSleepingAllowed(false);
		e2.createFixture(fixtureDef1);

		weldjointDef.bodyA = core;
		weldjointDef.bodyB = e2;
		weldjointDef.localAnchorA.y = -1f;
		weldjointDef.localAnchorA.x = .3f;
		weldjointDef.localAnchorB.x = 0;
		weldjointDef.localAnchorB.y = 0;
		world.createJoint(weldjointDef);

		// head
		h1 = world.createBody(bodyDef);
		h1.setLinearDamping(50);
		h1.setSleepingAllowed(false);
		h1.isFixedRotation();
		h1.createFixture(fixtureDef1);

		weldjointDef.bodyA = core;
		weldjointDef.bodyB = h1;
		weldjointDef.localAnchorA.y = .5f;
		weldjointDef.localAnchorA.x = .0f;
		weldjointDef.localAnchorB.x = 0;
		weldjointDef.localAnchorB.y = 0;
		world.createJoint(weldjointDef);

	}

}
