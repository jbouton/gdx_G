package com.gdx.gdx_G.screens;

import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;

import java.util.ArrayList;
import java.util.LinkedList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.gdx_G.Gdx_G;
import com.gdx.gdx_G.assets.SpriteInfo;
import com.gdx.gdx_G.assets.Assets;
import com.gdx.gdx_G.box2dObjects.Globe;
import com.gdx.gdx_G.box2dObjects.Magnet;
import com.gdx.gdx_G.box2dObjects.MagnetTarget;
import com.gdx.gdx_G.box2dObjects.Shield;
import com.gdx.gdx_G.tween.BodyAccessor;
import com.gdx.gdx_G.tween.CameraAccessor;
import com.gdx.gdx_G.tween.LightAccessor;
import com.gdx.gdx_G.tween.OrthographicCameraInfo;
import com.gdx.gdx_G.tween.ParticleAccessor;
import com.gdx.gdx_G.tween.SpriteAccessor;
import com.sun.net.httpserver.Filter.Chain;

public class PlayScreen implements Screen, InputProcessor, GestureListener {
	InputMultiplexer im;
	public ParticleEffect particleEffect, particleEngine1, particleEngine2, particleWeapon, particleBubble;
	private LinkedList<Body> deleteBodyList;
	private TweenManager tweenManager = new TweenManager();
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Assets assets;
	private float aspectRatio;
	private static final float WB = 0.01f;
	private static final float BW = 100f;
	private ShapeRenderer shapeRenderer;
	private SpriteInfo tempSprite, tempSprite2;
	private Gdx_G game;
	private SpriteBatch spriteBatch;
	private Array<Body> bodies, bodies2;
	private float[] vertices, vertices2;
	private Vector3 cameraPostion;
	private Timeline timeLine1;
	Polygon poly, poly2;
	private float lerp = 0.1f;
	int cI;
	Rectangle recField;
	Polygon polygon;
	Circle cirlce, circleTemp, circleTemp2;
	Rectangle rr;
	Rectangle bounds2;
	Polygon polygon2;
	private ArrayList<SpriteInfo> landSprites;
	private ArrayList<SpriteInfo> shieldSprites;
	private ArrayList<SpriteInfo> globeSprites;
	private SpriteInfo bg, d1;
	private Touchpad touchpad1, touchpad2;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground, touchBackground2;
	private Drawable touchKnob;
	private Texture blockTexture;
	private Sprite blockSprite;
	private float blockSpeed;
	private Stage stage;
	private boolean bMove;
	private RayHandler rayHandler;
	private boolean bTouch = true;
	private Magnet magnet2;
	private MagnetTarget magnetTarget;
	float sX, sY, eX, eY;
	int direction = 0;
	int lineLength, rayType;
	float m, rC;
	float e1X, e1Y, e2X, e2Y, x1, x2;
	Color colRed = new Color(Color.RED);
	Color colOrange = new Color(Color.ORANGE);
	Color colBlue = new Color(Color.BLUE);
	Color colGreen = new Color(Color.GREEN);
	Color colStart = new Color(Color.BLACK);
	Color colEnd = new Color(Color.BLACK);
	float height;
	float ppu;
	float width;
	private float groundCount;
	private TouchpadStyle touchpadStyle2;
	private boolean bTethered;
	private Globe globeTemp;
	private int randNum;
	float tetherDistance;
	private float screenX, screenY;
	private Skin touchpadSkin2;
	private int eCount;
	private SpriteInfo laser, ball;
	private float laserWidth;
	private float screenWidth, screenHeight;
	final short CATEGORY_MAGNET = 0x0001;
	final short CATEGORY_ENEMY = 0x0002;
	final short CATEGORY_BLOCK = 0x0004;
	final short CATEGORY_SHIELD = 0x0008;
	final short MASK_MAGNET = CATEGORY_BLOCK;
	final short MASK_ENEMY = CATEGORY_BLOCK;
	final short MASK_BLOCK = CATEGORY_ENEMY | CATEGORY_MAGNET;
	final short MASK_SHIELD = CATEGORY_ENEMY;
	final short sZ= 0x0000;
	private Viewport viewport;
	private Shield curShield;
	private boolean bShield;
	private Circle shieldCircle;
	PointLight pLight, pLight1,pLight2;
	private float t1x, t1y, t2x, t2y;
	private int vW,vH;
	float scrollTimer;
	private float p_dist;
	private PointLight tempPoint;

	public PlayScreen(Gdx_G libgdxGame) {
		this.game = libgdxGame;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		// System.out.println(screenX + "," + screenY + "-" + pointer);
		Vector3 touchPos = new Vector3(0, 0, 0);
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;
		camera.unproject(touchPos);
		float nX = Interpolation.linear.apply(magnetTarget.body.getPosition().x, touchPos.x * WB, .07f);
		float nY = Interpolation.linear.apply(magnetTarget.body.getPosition().y, touchPos.y * WB, .07f);
		tweenManager.killTarget(magnetTarget.body);
		magnetTarget.body.setTransform(new Vector2(nX, nY), 0);

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private final TweenCallback deleteCallback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {
			Body body = (Body) source.getUserData();
			deleteBodyList.add(body);
		}
	};

	private int bodyIndex;

	public boolean overlaps(Polygon polygon, Circle circle) {
		float[] vertices = polygon.getTransformedVertices();
		Vector2 center = new Vector2(circle.x, circle.y);
		float squareRadius = circle.radius * circle.radius;
		for (int i = 0; i < vertices.length; i += 2) {
			if (i == 0) {
				if (Intersector.intersectSegmentCircle(new Vector2(vertices[vertices.length - 2], vertices[vertices.length - 1]), new Vector2(vertices[i], vertices[i + 1]), center, squareRadius))
					return true;
			} else {
				if (Intersector.intersectSegmentCircle(new Vector2(vertices[i - 2], vertices[i - 1]), new Vector2(vertices[i], vertices[i + 1]), center, squareRadius))
					return true;
			}
		}
		return false;
	}

	@Override
	public void render(float delta) {
		  
		// clear screen
		// 176 224 230
		 //Gdx.gl.glClearColor(.1f, .3f, .1f, .5f);
		Gdx.gl.glClearColor(0f, 0f, 0f, .5f);
		// 176 196 222
		// Gdx.gl.glClearColor((176 / 2.55f) / 100, (196 / 2.55f) / 100, (222 /
		// 2.55f) / 100, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// set up camera for world
		Matrix4 cameraCopy = camera.combined.cpy();
		camera.update();

		tweenManager.update(delta);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// Gdx.gl20.glBlendEquation(Gdx.gl20.GL_FUNC_SUBTRACT);
		Gdx.gl20.glActiveTexture(2);

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		
		
		// position.x += ((BW * crab.getCrabBodyParts()[0].getPosition().x) -
		// position.x) * lerp;

		cameraPostion = camera.position;
		//cameraPostion.x = cameraPostion.x + 5;
		//viewport.update(width, height);
		// scrollTimer=scrollTimer + Gdx.graphics.getDeltaTime()/15;
		//System.out.println(scrollTimer);
		 if(scrollTimer>1.0f)
		    scrollTimer = 0.0f;
		 bgSprite.setU(scrollTimer);
		 bgSprite.setU2(scrollTimer + 1);
		bgSprite.setPosition(cameraPostion.x - vW / 2, -vH / 2 - 200);
		bgSprite.draw(spriteBatch);
		//bg.setPosition(cameraPostion.x-vW/2,-vH/2);
		//bg.setAlpha(.8f);
		//bg.draw(spriteBatch);
		// cameraPostion.x = ((BW * diver.core.getPosition().x -
		// cameraPostion.x) + 1) *lerp;
		// cameraPostion.y = ((BW * magnetTarget.body.getPosition().y -
		// cameraPostion.y) ) *lerp;
		// camera.viewportWidth = camera.gettViewPortWidth();
		// camera.viewportHeight = camera.gettViewPortHeight();
		// camera.apply(Gdx.gl30);

		camera.update();

		world.step(delta, 1, 6);
		bodies = new Array<Body>();
		world.getBodies(bodies);
		rr = new Rectangle();
		bodyIndex = 0;

		for (Body worldBodie : bodies) {
			if (worldBodie.getUserData() != null) {

				tempSprite = (SpriteInfo) worldBodie.getUserData();

				tempSprite.setScale(worldBodie.getFixtureList().get(0).getShape().getRadius());
				vertices = new float[10];
				vertices[0] = magnetTarget.body.getPosition().x * BW;
				vertices[1] = magnetTarget.body.getPosition().y * BW;
				vertices[2] = magnet2.body.getPosition().x * BW;
				vertices[3] = magnet2.body.getPosition().y * BW;
				vertices[4] = magnet2.body.getPosition().x * BW;
				vertices[5] = magnet2.body.getPosition().y * BW;
				vertices[6] = magnetTarget.body.getPosition().x * BW;
				vertices[7] = magnetTarget.body.getPosition().y * BW;
				vertices[8] = magnetTarget.body.getPosition().x * BW;
				vertices[9] = magnetTarget.body.getPosition().y * BW;
				poly = new Polygon(vertices);
				if (tempSprite.getName().equals("shield")) {
					updateSprite(worldBodie, spriteBatch);
					worldBodie.applyLinearImpulse(new Vector2(0, .01f), worldBodie.getWorldCenter(), true);
					cirlce = new Circle();
					cirlce.setPosition(worldBodie.getPosition().x * BW, worldBodie.getPosition().y * BW);
					cirlce.radius = worldBodie.getFixtureList().get(0).getShape().getRadius() * BW;
					shieldCircle = cirlce;

				}

				if (tempSprite.getName().equals("magnet")) {
					Vector2 magnetPosition2 = magnet2.body.getPosition();
					Vector2 magnetPosition1 = magnetTarget.body.getPosition();
					Vector2 distance1 = (new Vector2(0, 0));
					Vector2 distance2 = (new Vector2(0, 0));
					distance1.add(magnetPosition2);
					distance1.sub(magnetPosition1);
					float finalDistance1 = distance1.len();
					tetherDistance = finalDistance1;
					distance1.set(distance1.x, distance1.y);
					distance2.set(-distance1.x, -distance1.y);
					float sum1 = Math.abs(distance1.x) + Math.abs(distance1.y);
					float sum2 = Math.abs(-distance1.x) + Math.abs(-distance1.y);
					float power = 3345f;
					distance1.scl(((1 / sum1) * power / finalDistance1));
					distance2.scl(((1 / sum2) * power / finalDistance1));

					if (finalDistance1 > 5.9) {
						laser.setColor(Color.BLUE);
					} else
						laser.setColor(Color.BLUE);
					if (finalDistance1 > 6) {
						// if(bMove){
						magnet2.body.applyForce(distance2, magnetPosition1, true);
						magnet2.body.setLinearDamping(2);
						// magnetTarget.body.setLinearDamping(5);
						// magnetTarget.body.applyForce(distance1,
						// magnetPosition1,
						// true);

					} else {
						magnetTarget.body.setLinearDamping(2);
						magnet2.body.setLinearDamping(2);
					}

				}
				bodyIndex++;
				if (tempSprite.getName().equals("globe") || tempSprite.getName().equals("globe1")) {
					cirlce = new Circle();
					cirlce.setPosition(worldBodie.getPosition().x * BW, worldBodie.getPosition().y * BW);
					cirlce.radius = worldBodie.getFixtureList().get(0).getShape().getRadius() * BW;
					circleTemp = cirlce;

					if (overlaps(poly, cirlce)) {
						// shieldCircle = circleTemp;

						Timeline.createSequence().push(Tween.to(pLight, LightAccessor.DISTANCE, .2f).target(500)).push(Tween.to(pLight, LightAccessor.DISTANCE, .2f).target(100)).start(tweenManager);
						//worldBodie.setTransform(magnetTarget.body.getPosition(),0);

						//magnetTarget.body.setTransform(worldBodie.getPosition(), 0);
						ball.setSize(cirlce.radius * 2, cirlce.radius * 2);
						ball.setPosition(cirlce.x - ball.getWidth() / 2, cirlce.y - ball.getHeight() / 2);
						colStart = new Color(Color.ORANGE.r, Color.ORANGE.g, Color.ORANGE.b, .5f);
						colEnd = Color.BLUE;

						deleteBodyList.add(worldBodie);
						particleEffect.setPosition(cirlce.x, cirlce.y);
						particleEffect.start();
						if (tempSprite.getName().equals("globe1")) {
							float iR = worldBodie.getFixtureList().get(0).getShape().getRadius();
							tempSprite.setScale(iR);
							if (iR < .2f) {
								deleteBodyList.add(worldBodie);
								particleEffect.setPosition(cirlce.x, cirlce.y);
								particleEffect.start();
							}

							CircleShape circleShape = new CircleShape();
							circleShape.setRadius(iR = iR - .01f);
							FixtureDef fixtureDef = new FixtureDef();
							fixtureDef.shape = circleShape;
							float fd = (float) (Math.random() * 1);
							fixtureDef.density = .3f + fd;
							fixtureDef.filter.categoryBits = this.CATEGORY_ENEMY;
							fixtureDef.filter.maskBits = this.MASK_ENEMY;
							worldBodie.destroyFixture(worldBodie.getFixtureList().get(0));
							worldBodie.createFixture(fixtureDef);
							worldBodie.resetMassData();

						}

					}

					Vector2 objectPosition = worldBodie.getPosition();
					Vector2 magnetPosition1 = new Vector2(magnetTarget.body.getPosition());

					Vector2 distance1 = (new Vector2(0, 0));
					distance1.add(objectPosition);
					distance1.sub(magnetPosition1);
					float finalDistance1 = distance1.len();
					distance1.set(-distance1.x, -distance1.y);
					float sum1 = Math.abs(distance1.x) + Math.abs(distance1.y);
					distance1.scl(((1 / sum1) * 120 / finalDistance1));

					Vector2 magnetPosition2 = new Vector2(magnet2.body.getPosition());
					Vector2 distance2 = (new Vector2(0, 0));
					distance2.add(objectPosition);
					distance2.sub(magnetPosition2);
					float finalDistance2 = distance2.len();
					distance2.set(-distance2.x, -distance2.y);
					float sum2 = Math.abs(distance2.x) + Math.abs(distance2.y);
					distance2.scl(((1 / sum2) * 5 / finalDistance2));


					if (finalDistance2 > 25)
						deleteBodyList.add(worldBodie);

					if (curShield != null) {
						Vector2 magnetPosition3 = new Vector2(curShield.body.getPosition());
						Vector2 distance3 = (new Vector2(0, 0));
						distance3.add(objectPosition);
						distance3.sub(magnetPosition3);
						float finalDistance3 = distance3.len();
						distance3.set(-distance3.x, -distance3.y);
						float sum3 = Math.abs(distance3.x) + Math.abs(distance3.y);
						distance3.scl(((1 / sum2) * 705 / finalDistance3));
						if (finalDistance3 > 1) {
							worldBodie.applyForce(distance3, magnetPosition3, true);
							worldBodie.setLinearDamping(1);
						}
					} else {
						worldBodie.setLinearDamping(0);
						if (finalDistance2 > finalDistance1) {
							worldBodie.applyForce(distance1, magnetPosition2, true);
							//System.out.println("magnetPosition2");
						} else {
							//System.out.println("magnetPosition1");
							worldBodie.applyForce(distance2, magnetPosition1, true);
						}
					}
					float angle = (float) Math.toDegrees(Math.atan2(worldBodie.getPosition().y - magnet2.body.getPosition().y, worldBodie.getPosition().x - magnet2.body.getPosition().x));
					if (angle < 0) {
						angle += 360;
					}
					worldBodie.setTransform(worldBodie.getPosition(), (float) Math.toRadians(angle));
					updateDraw(worldBodie, spriteBatch,rayHandler);

				}

			}
		}
		for (Body bodyTemp : deleteBodyList) {

			try {
				// System.out.println("delete");
				tempSprite = (SpriteInfo) bodyTemp.getUserData();
				tempPoint = (PointLight) tempSprite.getP();
				Timeline.createSequence().push(Tween.to(tempPoint, LightAccessor.DISTANCE, .2f).target(500)).push(Tween.to(tempPoint, LightAccessor.DISTANCE, 1f).target(0)).start(tweenManager);
				Tween.call(deletLightback).delay(5).setUserData(tempPoint).start(tweenManager);

				landSprites.remove(tempSprite);
				shieldSprites.remove(tempSprite);
				System.out.println("delete " + tempSprite.getName());
				if (tempSprite.getName().equals("shield"))
					curShield = null;
				if (tempSprite.getName().equals("globe"))
					eCount--;
				world.destroyBody(bodyTemp);

			} catch (Exception e) {
			}
		}
		deleteBodyList.clear();
		for (SpriteInfo sprite : shieldSprites) {
			sprite.draw(spriteBatch);
		}
		for (SpriteInfo sprite : landSprites) {
			sprite.draw(spriteBatch);
		}

		// rayHandler.updateAndRender();

		// particleWeapon.draw(spriteBatch, delta);
		// particleWeapon.update(Gdx.graphics.getDeltaTime());

		// particleBubble.draw(spriteBatch, delta);
		// particleBubble.update(Gdx.graphics.getDeltaTime());
		particleEngine2.setPosition(magnet2.engine2.getPosition().x * BW, magnet2.engine2.getPosition().y * BW);
		particleEngine1.setPosition(magnet2.engine1.getPosition().x * BW, magnet2.engine1.getPosition().y * BW);
		float angle = (float) Math.toDegrees(Math.atan2(magnet2.body.getPosition().y - magnetTarget.body.getPosition().y, magnet2.body.getPosition().x - magnetTarget.body.getPosition().x));
		if (angle < 0) {
			// angle += 360;
		}

		float A = (float) Math.pow(magnetTarget.body.getPosition().x - magnet2.body.getPosition().x, 2);
		float B = (float) Math.pow(magnetTarget.body.getPosition().y - magnet2.body.getPosition().y, 2);
		float dis = (float) Math.sqrt(A + B) * BW;
		magnetTarget.body.setTransform(magnetTarget.body.getPosition(), (float) Math.toRadians(angle));
		magnet2.body.setTransform(magnet2.body.getPosition(), (float) Math.toRadians(angle));

		laser.setSize(magnet2.sprite.getWidth(), dis + magnet2.sprite.getWidth() / 2);
		laser.setOrigin(magnet2.sprite.getOriginX(), magnet2.sprite.getOriginY());
		laser.setPosition(magnet2.laser.getPosition().x * BW - magnet2.sprite.getWidth() / 2, magnet2.laser.getPosition().y * BW - magnet2.sprite.getHeight() / 2);
		laser.setRotation(magnet2.sprite.getRotation() + 90);

		spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		laser.draw(spriteBatch);
		// updateDraw(magnetTarget.body, spriteBatch);
		spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		particleEffect.draw(spriteBatch, delta);
		particleEffect.update(Gdx.graphics.getDeltaTime());
		particleEngine1.draw(spriteBatch, delta);
		particleEngine1.update(Gdx.graphics.getDeltaTime());
		particleEngine2.draw(spriteBatch, delta);
		particleEngine2.update(Gdx.graphics.getDeltaTime());
		updateDraw(magnet2.body, spriteBatch,rayHandler);
		updateDraw(magnet2.shield, spriteBatch,rayHandler);
		// updateDraw(magnet2.laser, spriteBatch);
		ball.draw(spriteBatch);
		
		spriteBatch.end();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(new Color(1, 1, 1, 0.2f));
		shapeRenderer.begin(ShapeType.Line);
		if (laserWidth < 6)
			laserWidth++;
		else
			laserWidth = 1;
		Gdx.gl20.glEnable(Gdx.gl20.GL_BLEND);
		Gdx.gl20.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl20.glLineWidth(laserWidth);

		if (bTethered)
			shapeRenderer.line(magnetTarget.body.getPosition().x * BW, magnetTarget.body.getPosition().y * BW, 0, magnet2.laser.getPosition().x * BW, magnet2.laser.getPosition().y * BW, 0, colStart, colEnd);
		colStart = new Color(1, 0, 0, .5f);
		colEnd = Color.BLUE;
		ball.setPosition(-1000, -1000);

		shapeRenderer.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		pLight.setPosition(magnetTarget.body.getPosition().x * BW, magnetTarget.body.getPosition().y * BW);
		pLight1.setPosition(magnet2.body.getPosition().x * BW, magnet2.body.getPosition().y * BW);
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.render();
		rayHandler.update();
		p_dist = 500;
		//rayHandler.updateAndRender();
		// if (magnetTarget.body.getPosition().y < -1 ||
		// magnetTarget.body.getPosition().y
		// < -1) {
		// dispose();
		// game.setScreen(game.mainMenuScreen);
		// show();
		// }
		//debugRenderer.render(world, cameraCopy.scl(BW));

	}

	private final TweenCallback spaunGlobeCallback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {
			if (world.getBodyCount() < 25) {
				float rand = (float) (Math.random() * 1.5);
				Globe globe = new Globe(world, magnet2.body.getPosition().x, -10, assets, .5f,rayHandler);

				globe.createGlobe();

			}
		}
	};
	private final TweenCallback deletCallback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {
			Body body = (Body) source.getUserData();
			deleteBodyList.add(body);
		}
	};
	private final TweenCallback deletLightback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {
			PointLight p =(PointLight) source.getUserData();
			p.remove();
		}
	};
	private final TweenCallback spaunShieldCallback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {

			if (curShield == null) {
				curShield = new Shield(world, magnetTarget.body.getPosition().x + 3, -6, assets, 2f);
				curShield.createShield();
				Tween.to(curShield.body, BodyAccessor.RADIUS_SHIELD, 10).target(.1f).setUserData(curShield.body).setCallback(deletCallback).start(tweenManager);
				shieldSprites.add(curShield.sprite);
				for (int i = 0; i < 50; i++) {
					if (world.getBodyCount() < 50) {
						float rand = (float) (Math.random() * .5);
						Globe globe = new Globe(world, curShield.body.getPosition().x, curShield.body.getPosition().y, assets, .1f + rand,rayHandler);
						globe.createGlobe();
					}

				}

			}
		}
	};
	private Sprite bgSprite;

	private void updateDraw(Body worldBodie, SpriteBatch spriteBatch, RayHandler ray) {

		SpriteInfo tempSprite = (SpriteInfo) worldBodie.getUserData();
			tempSprite.setPosition((worldBodie.getPosition().x * BW) - tempSprite.getWidth() / 2, (worldBodie.getPosition().y * BW) - tempSprite.getHeight() / 2);
		tempSprite.setRotation(MathUtils.radiansToDegrees * worldBodie.getAngle());

		if (tempSprite.getName().equals("globe") || (tempSprite.getName().equals("globe1"))) {
			PointLight p = (PointLight) tempSprite.getP();
			p.setPosition((worldBodie.getPosition().x * BW) , (worldBodie.getPosition().y * BW));

			tempSprite2 = globeSprites.get(tempSprite.getFrameIndex());
			tempSprite2.setPosition(tempSprite.getX(), tempSprite.getY());
			tempSprite2.setRotation(tempSprite.getRotation());
			tempSprite2.setScale(tempSprite.getScaleX(), tempSprite.getScaleY());
			//pLight2.setPosition(worldBodie.getPosition().x * BW, worldBodie.getPosition().y * BW);
			tempSprite2.draw(spriteBatch);
			if (tempSprite.getFrameIntervalcounter() < tempSprite.getFrameInterval()) {
				tempSprite.setFrameIntervalcounter(tempSprite.getFrameIntervalcounter() + 1);
			} else {
				tempSprite.setFrameIntervalcounter(0);
				if (tempSprite.getFrameIndex() < tempSprite.getFrameCount()) {
					tempSprite.setFrameIndex(tempSprite.getFrameIndex() + 1);

					// System.out.println(tempSprite.getFrameIndex());
				} else {
					tempSprite.setFrameIndex(0);
					tempSprite.setFrameIntervalcounter(0);

				}

			}

		} else {
			
			tempSprite.draw(spriteBatch);
		}

	}

	private void updateSprite(Body worldBodie, SpriteBatch spriteBatch) {

		SpriteInfo tempSprite = (SpriteInfo) worldBodie.getUserData();
		tempSprite.setPosition((worldBodie.getPosition().x * BW) - tempSprite.getWidth() / 2, (worldBodie.getPosition().y * BW) - tempSprite.getHeight() / 2);
		tempSprite.setRotation(MathUtils.radiansToDegrees * worldBodie.getAngle());
		tempSprite.draw(spriteBatch);

	}

	private void createCollisionListener() {

		world.setContactListener(new ContactListener() {
			private SpriteInfo SpriteInfoB;
			private SpriteInfo SpriteInfoA;
			private String spriteTypeA;
			private String spriteTypeB;

			@Override
			public void beginContact(Contact contact) {
				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				WorldManifold worldManifold = contact.getWorldManifold();
				if (fixtureA.getBody().getUserData() != null && fixtureB.getBody().getUserData() != null) {
					SpriteInfoA = (SpriteInfo) fixtureA.getBody().getUserData();
					SpriteInfoB = (SpriteInfo) fixtureB.getBody().getUserData();
					spriteTypeA = SpriteInfoA.getName();
					spriteTypeB = SpriteInfoB.getName();
					System.out.println(spriteTypeA + "," + spriteTypeB);
					if (spriteTypeA.equals("magnet") && spriteTypeB.equals("shield")) {

						// System.out.println("hit shield2");
						// System.out.println(worldManifold.getNumberOfContactPoints());
						Vector2 vc = new Vector2(worldManifold.getPoints()[0]);
						// System.out.println(vc.x+","+vc.y);
					}
					if ((spriteTypeA.equals("shieldMagnet") && spriteTypeB.equals("globe1")) || (spriteTypeA.equals("shieldMagnet") && spriteTypeB.equals("shield"))) {

						float rr = fixtureA.getBody().getFixtureList().get(0).getShape().getRadius();
						if (rr > .2f) {
							if (!(tweenManager.containsTarget(fixtureA.getBody()))) {
								Tween.to(fixtureA.getBody(), BodyAccessor.RADIUS_SHIELD, .2f).target(rr - .01f).start(tweenManager);

							}
						} else {
							if (!(tweenManager.containsTarget(fixtureA.getBody())))
								Tween.to(fixtureA.getBody(), BodyAccessor.RADIUS_SHIELD, .2f).target(1.5f).start(tweenManager);
							Tween.to(SpriteInfoA, SpriteAccessor.ALPHA, .5f).target(0).setUserData(fixtureB.getBody()).setCallback(deleteCallback).start(tweenManager);

						}
						SpriteInfoA.setAlpha(90);
						Tween.to(SpriteInfoA, SpriteAccessor.ALPHA, .5f).target(0).setUserData(fixtureB.getBody()).setCallback(deleteCallback).start(tweenManager);

					}

				}
			}

			@Override
			public void endContact(Contact contact) {

				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				if (fixtureA.getBody().getUserData() != null && fixtureB.getBody().getUserData() != null) {
					SpriteInfoA = (SpriteInfo) fixtureA.getBody().getUserData();
					SpriteInfoB = (SpriteInfo) fixtureB.getBody().getUserData();
					spriteTypeA = SpriteInfoA.getName();
					spriteTypeB = SpriteInfoB.getName();
					// System.out.println(spriteTypeA + "," + spriteTypeB);
					if (spriteTypeA.equals("globe") && spriteTypeB.equals("magnet")) {

					}
				}
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}

		});
	}

	@Override
	public void show() {
		System.out.println("Show");
		
		landSprites = new ArrayList<SpriteInfo>();
		shieldSprites = new ArrayList<SpriteInfo>();
		globeSprites = new ArrayList<SpriteInfo>();
		bTethered = true;
		groundCount = 0;
		height = 1200;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		stage = new Stage();
		shapeRenderer = new ShapeRenderer();
		im = new InputMultiplexer();

		GestureDetector gd = new GestureDetector(this);
		// Create a Stage and add TouchPad
		spriteBatch = new SpriteBatch();

		cirlce = new Circle();
		cirlce.setRadius(5);
		im.addProcessor(gd);
		im.addProcessor(stage);
		im.addProcessor(this);
		Gdx.input.setInputProcessor(im);
		Tween.registerAccessor(SpriteInfo.class, new SpriteAccessor());
		Tween.registerAccessor(ParticleEffect.class, new ParticleAccessor());
		Tween.registerAccessor(Body.class, new BodyAccessor());
		Tween.registerAccessor(PointLight.class, new LightAccessor());
		Tween.registerAccessor(OrthographicCameraInfo.class, new CameraAccessor());
		// set up box2d world
		world = new World(new Vector2(0, 0), true);
		createCollisionListener();
		debugRenderer = new Box2DDebugRenderer();
		// camera = new OrthographicCameraInfo();
		// camera.update();

		camera = new OrthographicCamera();
		vW=1920*2;
		vH=1600*2;
		viewport = new StretchViewport (vW,vH, camera);

		assets = new Assets();

		for (int i = 0; i < 15; i++) {
			globeSprites.add(new SpriteInfo(assets.getSprite("crabd-" + i)));
			//globeSprites.get(i).setColor(Color.YELLOW);
		}

		bg = new SpriteInfo(assets.getSprite("bg"));
		bg.setOrigin(vW * 3 / 2, vH * 3 / 2);
		bg.setSize(vW * 3, vH * 3);
		Tween.to(bg, SpriteAccessor.ROTATE, 360).target(360).ease(TweenEquations.easeNone).repeatYoyo(-1, 0).start(tweenManager);
		Texture spriteTexture = new Texture(Gdx.files.internal("data/bg.png"));
		spriteTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		bgSprite = new Sprite(spriteTexture, 0, 0, vW, vH*2);
		//bgSprite.setColor(Color.GREEN);
		bg.setAlpha(.5f);
		// Animation animation = new Animation(1 / 1f,
		// assets.getSprite("laser"));
		// animation.setPlayMode(Animation.PlayMode.LOOP);
		laser = new SpriteInfo(assets.getSprite("laser"));
		laser.setColor(Color.BLUE);

		ball = new SpriteInfo(assets.getSprite("ball"));
		ball.setColor(Color.ORANGE);
		ball.setPosition(-1000, -1000);

		deleteBodyList = new LinkedList<Body>();
		rayHandler = new RayHandler(world);
		//rayHandler.setLightMapRendering(true);
		//rayHandler.setCombinedMatrix(camera.combined);
		//rayHandler.setShadows(false);
		// rayHandler.setAmbientLight(0);
		rayHandler.setBlur(true);
		rayHandler.setAmbientLight(.001f);
		rayHandler.setCulling(false);
		// rayHandler.setAmbientLight(0f, 0f, 0f, 1f);

		pLight = new PointLight(rayHandler, 5, new Color(1, 1, 1, 1), 100, (width / 2) * WB, (height / 2) * WB);
		pLight2 = new PointLight(rayHandler, 50, new Color(1, 0, 0, 1), 1, (width / 2) * WB, (height / 2) * WB);
		pLight1 = new PointLight(rayHandler, 50, new Color(0, 0, 1, 1), 1500, (width / 2) * WB, (height / 2) * WB);
		pLight1.setContactFilter(this.sZ, this.sZ, this.sZ);
		 pLight1.setSoftnessLength(0);
		 pLight.setStaticLight(true);
		//pLight.setSoft(true);

		// pLight1.setStaticLight(true);
		// tempSprite = (SpriteInfo) magnet.getBody().getUserData();

		Tween.call(spaunGlobeCallback).repeat(-1, .2f).start(tweenManager);
		// Tween.call(spaunShieldCallback).repeat(-1, 1f).start(tweenManager);

		particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("data/exp"), Gdx.files.internal("data"));

		particleEngine1 = new ParticleEffect();
		particleEngine1.load(Gdx.files.internal("data/engineB"), Gdx.files.internal("data"));
		particleEngine1.start();

		particleEngine2 = new ParticleEffect();
		particleEngine2.load(Gdx.files.internal("data/engineB"), Gdx.files.internal("data"));
		particleEngine2.start();

		particleEngine1.setPosition(-10000, -10000);
		particleEngine2.setPosition(-10000, -10000);

		magnetTarget = new MagnetTarget(world, vW / 2 * WB, vH / 2 * WB, assets);
		magnetTarget.createMagnet();
		magnetTarget.sprite.setColor(Color.BLUE);
		//pLight.attachToBody(magnetTarget.body);
		magnet2 = new Magnet(world, vW / 2 * WB, vH / 2 * WB, assets, 1f, true);
		magnet2.createMagnet();
	}

	@Override
	public void resize(int width, int height) {
		// System.out.println("resize");
		// System.out.println(width + "," + height);
		viewport.update(width, height);
		camera.update();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		System.out.println("dispose");
		world.dispose();
		assets.getAtlas().dispose();

	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		System.out.println("Long press");
		// dispose();
		// game.setScreen(game.mainMenuScreen);
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// System.out.println("fling");
		// System.out.println(screenX + "<" + width / 2);
		Vector3 touchPos = new Vector3(0, 0, 0);
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;
		camera.unproject(touchPos);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = new Vector3(0, 0, 0);
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;
		camera.unproject(touchPos);
		return false;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {
		Vector3 touchPos = new Vector3(0, 0, 0);
		touchPos.x = x;
		touchPos.y = y;
		touchPos.z = 0;
		camera.unproject(touchPos);
		float A = (float) Math.pow(magnetTarget.body.getPosition().x - touchPos.x * WB, 2);
		float B = (float) Math.pow(magnetTarget.body.getPosition().y - touchPos.y * WB, 2);
		float dis = ((float) Math.sqrt(A + B) * BW) / 450;
		// System.out.println("dis="+dis / 450);
		Tween.to(magnetTarget.body, BodyAccessor.POSITION_XY, dis).target(touchPos.x * WB, touchPos.y * WB, 0).start(tweenManager);
		// magnetTarget.body.setTransform(new Vector2(touchPos.x*WB,
		// touchPos.y*WB),
		// 0);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

}
