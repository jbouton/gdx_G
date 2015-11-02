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
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.assets.SpriteInfo;

public class SpaceStation {
	private World world;
	private float x, y, bx, by;
	private Body body;
	private SpriteInfo sprite;
	private Assets assets;

	public SpaceStation(World world, float x, float y, float bx, float by, Assets assets) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.bx = bx;
		this.by = by;
		this.assets = assets;
	}

	public void createSpaceStation() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(.2f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		//fixtureDef.isSensor = true;
		fixtureDef.density = 1;

		body = world.createBody(bodyDef);
		body.setLinearDamping(1f);
		body.setGravityScale(0);
		body.createFixture(fixtureDef);
		sprite = new SpriteInfo(assets.getSprite("tribase-u3-d0"));
		sprite.setName("spaceStation");
		body.setUserData(sprite);
		body.isSleepingAllowed();
		//body.applyAngularImpulse(.5f, true);

		BodyDef bodyDef1 = new BodyDef();
		bodyDef1.type = BodyType.StaticBody;
		bodyDef1.position.x = bx;
		bodyDef1.position.y = by;
		CircleShape circleShape1 = new CircleShape();
		circleShape1.setRadius(1f);
		FixtureDef fixtureDef1 = new FixtureDef();
		fixtureDef1.isSensor = true;
		fixtureDef1.shape = circleShape1;
		Body gearBase = world.createBody(bodyDef1);
		gearBase.createFixture(fixtureDef1);
		
		RevoluteJointDef wheeljointDef = new RevoluteJointDef();
		wheeljointDef.bodyA = gearBase;
		wheeljointDef.bodyB = body;
		wheeljointDef.localAnchorA.y = 0;
		wheeljointDef.localAnchorA.x = 0;
		wheeljointDef.localAnchorB.x = 3;
		wheeljointDef.localAnchorB.y = 3;
		wheeljointDef.motorSpeed = 1f;
		wheeljointDef.enableMotor = true;
		wheeljointDef.maxMotorTorque = 1f;
		// Joint joint =
		// wheeljointDef.bodyA.getWorld().createJoint(wheeljointDef);
		world.createJoint(wheeljointDef);
   	    circleShape.dispose();


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
