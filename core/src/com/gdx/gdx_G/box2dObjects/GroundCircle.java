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

public class GroundCircle {
	private World world;
	private float x, y, radius;
	private Body body;
	private SpriteInfo sprite;
	private Assets assets;
	final short CATEGORY_DIVER = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short MASK_DIVER = CATEGORY_BLOCK | CATEGORY_ENEMY;
	final short MASK_ENEMY = CATEGORY_BLOCK | CATEGORY_DIVER;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_DIVER;

	public GroundCircle(World world, float x, float y, Assets assets, float radius) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
		this.radius = radius;
	}

	public void createGroundCircle() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		//fixtureDef.isSensor = true;
		fixtureDef.density = 5;
		fixtureDef.friction=5;
		fixtureDef.filter.categoryBits=this.CATEGORY_BLOCK;
		fixtureDef.filter.maskBits=this.MASK_BLOCK;

		body = world.createBody(bodyDef);
		body.setLinearDamping(1f);
		body.setGravityScale(0);
		body.createFixture(fixtureDef);
		body.applyAngularImpulse(.5f,true);
		sprite = new SpriteInfo(assets.getSprite("ground"));
		sprite.setScale(radius/3);
		double cI =  (Math.random() * .5f)+.4f;
		sprite.setColor(new Color((float) (cI),sprite.getColor().g,sprite.getColor().b,1f));
		sprite.setName("ground");
		body.setUserData(sprite);

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
