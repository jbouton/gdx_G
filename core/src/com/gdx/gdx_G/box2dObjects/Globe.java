package com.gdx.gdx_G.box2dObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Globe {
	private RayHandler ray;
	private World world;
	private float x, y, radius,flag;
	public Body body,h1;
	private SpriteInfo sprite;
	private Assets assets;
	final short CATEGORY_MAGNET = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short CATEGORY_SHIELD = 0x0008;
	final short MASK_MAGNET = CATEGORY_BLOCK;
	final short MASK_ENEMY = CATEGORY_BLOCK;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_MAGNET;
	final short MASK_SHIELD = CATEGORY_ENEMY;
	RevoluteJointDef wheeljointDef = new RevoluteJointDef();
	WeldJointDef weldjointDef = new WeldJointDef();
	PointLight p;

	public Globe(World world, float x, float y, Assets assets, float radius, RayHandler ray) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
		this.radius = radius;
		this.ray = ray;
	}

	public void createGlobe() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		bodyDef.fixedRotation=true;
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		float fd= (float) (Math.random() *5);
		fixtureDef.density = .3f+fd;
		fixtureDef.filter.categoryBits=this.CATEGORY_ENEMY;
		fixtureDef.filter.maskBits=this.MASK_ENEMY;

		body = world.createBody(bodyDef);
		body.setLinearDamping(0f);
		body.setGravityScale(-1);
		body.createFixture(fixtureDef);
		//body.applyAngularImpulse(.5f,true);
		sprite = new SpriteInfo(assets.getSprite("crabd-0"));
		//sprite.setScale(radius);
		sprite.setFrameInterval(1);
		sprite.setFrameCount(14);
		int cI = (int) (Math.random() *55);
		Color col1 = new Color((cI+100 / 2.55f) / 100, 0, 0, .5f);
		//sprite.setColor(new Color(sprite.getColor().r,sprite.getColor().g,sprite.getColor().b,.99f));
		//sprite.setColor(col1);
		sprite.setName("globe1");
	    p = new PointLight(ray,5,Color.ORANGE,100,x,y);
		sprite.setP(p);
	//	sprite.setScale(1);
		body.setUserData(sprite);
		
		/*WeldJointDef weldjointDef = new WeldJointDef();
		circleShape = new CircleShape();
		circleShape.setRadius(radius/2);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		Body h1 = world.createBody(bodyDef);
		h1.createFixture(fixtureDef);
		
		sprite = new SpriteInfo(assets.getSprite("crabd-0"));
		sprite.setName("globe1");
		sprite.setScale(radius);
		sprite.setColor(Color.RED);
		sprite.setFrameInterval(2);
		sprite.setFrameCount(3);
		h1.setUserData(sprite);
		h1.setSleepingAllowed(false);

		h1.createFixture(fixtureDef);

		weldjointDef.bodyA = body;
		weldjointDef.bodyB = h1;
		weldjointDef.localAnchorA.y = .0f;
		weldjointDef.localAnchorA.x = .0f;
		weldjointDef.localAnchorB.x = 0;
		weldjointDef.localAnchorB.y = 0;
		world.createJoint(weldjointDef);
*/
		
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
