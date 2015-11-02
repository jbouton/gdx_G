package com.gdx.gdx_G.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class BodyAccessor implements TweenAccessor<Body> {
	public static final int RADIUS = 1;
	public static final int RADIUS_SHIELD = 5;
	public static final int DRIP = 2;
	public static final int ROTATE = 4;
	PolygonShape polygon;
	private Vector2 vertex;
	Vector2[] vertices;
	public static final int POSITION_XY = 3;
	final short CATEGORY_MAGNET = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short CATEGORY_SHIELD = 0x0008;
	final short MASK_MAGNET = CATEGORY_BLOCK;
	final short MASK_ENEMY = CATEGORY_BLOCK;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_MAGNET;
	final short MASK_SHIELD = CATEGORY_ENEMY;

	@Override
	public int getValues(Body target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case RADIUS:
			returnValues[0] = target.getFixtureList().get(0).getShape().getRadius();
			return 1;
		case RADIUS_SHIELD:
			System.out.println(target.getFixtureList().size);
			if (target.getFixtureList().size > 0)
				returnValues[0] = target.getFixtureList().get(0).getShape().getRadius();
			return 1;
		case DRIP:
			polygon = (PolygonShape) target.getFixtureList().get(0).getShape();
			vertex = new Vector2(0, 0);
			polygon.getVertex(2, vertex);
			returnValues[0] = vertex.y;
			return 1;
		case POSITION_XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			returnValues[2] = target.getAngle();
			return 3;
		case ROTATE:
			returnValues[0] = target.getAngle();
			return 1;

		}

		assert false;
		return -1;
	}

	@Override
	public void setValues(Body target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case RADIUS:

			CircleShape circleShape = new CircleShape();
			circleShape.setRadius(newValues[0]);
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = circleShape;
			fixtureDef.friction = 1;
			fixtureDef.density = 1.5f;
			fixtureDef.restitution = 0f;
			// fixtureDef.filter.groupIndex = -1;
			if (target.getFixtureList().size > 0)
				target.destroyFixture(target.getFixtureList().get(0));
			target.createFixture(fixtureDef);

			break;
		case RADIUS_SHIELD:

			CircleShape circleShape1 = new CircleShape();
			circleShape1.setRadius(newValues[0]);
			FixtureDef fixtureDef2 = new FixtureDef();
			fixtureDef2.shape = circleShape1;
			fixtureDef2.filter.categoryBits = this.CATEGORY_BLOCK;
			fixtureDef2.filter.maskBits = this.MASK_BLOCK;
			fixtureDef2.density = .5f;
			if (target.getFixtureList().size > 0)
				target.destroyFixture(target.getFixtureList().get(0));
			target.createFixture(fixtureDef2);

			break;
		case DRIP:
			vertices = new Vector2[3];
			vertices[0] = new Vector2(-.1f, 0);
			vertices[1] = new Vector2(.1f, 0);
			vertices[2] = new Vector2(0, newValues[0]);
			PolygonShape polygon = new PolygonShape();
			polygon.set(vertices);
			FixtureDef fixtureDef1 = new FixtureDef();
			fixtureDef1.shape = polygon;
			fixtureDef1.friction = 0f;
			fixtureDef1.density = 44.10f;
			fixtureDef1.restitution = 1f;
			// fixtureDef1.isSensor = true;
			fixtureDef1.filter.groupIndex = -1;
			target.destroyFixture(target.getFixtureList().get(0));
			target.createFixture(fixtureDef1);
			break;

		case POSITION_XY:
			target.setTransform(newValues[0], newValues[1], newValues[2]);
		case ROTATE:
			target.setAngularVelocity(0);
			target.setTransform(target.getPosition().x, target.getPosition().y, target.getAngle() + newValues[0]);
			break;
		}
	}
}