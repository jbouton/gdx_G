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

public class Plant {
	private World world;
	private float x, y, radius;
	public Body body,h1,base;
	public SpriteInfo plantSprite,baseSprite;
	private SpriteInfo sprite;
	private Assets assets;
	final short CATEGORY_DIVER = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short MASK_DIVER = CATEGORY_BLOCK | CATEGORY_ENEMY;
	final short MASK_ENEMY = CATEGORY_BLOCK ;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_DIVER;
	RevoluteJointDef wheeljointDef = new RevoluteJointDef();
	WeldJointDef weldjointDef = new WeldJointDef();

	public Plant(World world, float x, float y, Assets assets, float radius) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.assets = assets;
		this.radius = radius;
	}

	public void createPlants() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
	//bodyDef.fixedRotation=true;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(.25f, 1.5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boxShape;
		//fixtureDef.isSensor = true;
		fixtureDef.density = .1f;
		fixtureDef.friction=500;
		fixtureDef.filter.categoryBits=this.CATEGORY_ENEMY;
		fixtureDef.filter.maskBits=this.MASK_ENEMY;

		body = world.createBody(bodyDef);
		body.setLinearDamping(100f);
		//body.setGravityScale(5);
		body.createFixture(fixtureDef);
		
		//body.applyAngularImpulse(.5f,true);
		plantSprite = new SpriteInfo(assets.getSprite("plant"));
		//sprite.setScale();
		double cI =  (Math.random() * .5f)+.4f;
		plantSprite.setColor(new Color((float) (cI),0,0,1f));
		//plantSprite.setScale(1,(float) cI);
		//plantSprite.setColor(Color.RED);
		plantSprite.setName("plant");
		//sprite.setAlpha(.5f);
		body.setUserData(plantSprite);
		
		
		
		//fixtureDef = new FixtureDef();
		boxShape = new PolygonShape();
		boxShape.setAsBox(1.5f, .1f);
		//fixtureDef.isSensor = true;
		fixtureDef.shape = boxShape;
		fixtureDef.friction=50;
		fixtureDef.density=30;
		fixtureDef.filter.categoryBits=this.CATEGORY_ENEMY;
		fixtureDef.filter.maskBits=this.MASK_ENEMY;
		base = world.createBody(bodyDef);
		baseSprite = new SpriteInfo(assets.getSprite("base"));
		double cI2 =  (Math.random() * .5f)+.4f;
		baseSprite.setColor(new Color((float) (cI2),baseSprite.getColor().g,baseSprite.getColor().b,1f));
	
		baseSprite.setName("plant");
		//sprite.setAlpha(.2f);
		base.setUserData(baseSprite);
		base.createFixture(fixtureDef);
		
		weldjointDef.bodyA = body;
		weldjointDef.bodyB = base;
		weldjointDef.localAnchorA.y = -1.5f;
		weldjointDef.localAnchorA.x = 0f;
		weldjointDef.localAnchorB.x = 0;
		weldjointDef.localAnchorB.y = 0;
		world.createJoint(weldjointDef);
		
		
		
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
