package com.sinsnare.demo;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MyDemoGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite player;
	float playerSpeed;
	OrthographicCamera cam;
	Array<Bullet> bullets;
	Array<Enemy> enemies;
	PlayerMovement movement = new PlayerMovement();

	@Override
	public void create() {
		batch = new SpriteBatch();
		player = new Sprite(new Texture("player.png"));
		player.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		player.setOriginCenter();
		bullets = new Array<Bullet>();
		enemies = new Array<Enemy>();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);

		Gdx.input.setInputProcessor(movement);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.5f, 0.6f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Vector2 playerPos = new Vector2(player.getX(), player.getY());

		playerSpeed = movement.xMovement;

		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX(), y = Gdx.input.getY();
			Vector3 touchPos = cam.unproject(new Vector3(x, y, 0));
			Vector2 dir = new Vector2().set(touchPos.x, touchPos.y).sub(playerPos).nor();
			bullets.add(new Bullet(playerPos.x + (player.getWidth() / 2), playerPos.y + player.getHeight(), dir));
		}
		if (MathUtils.random(200) == 42) {
			System.out.println("spawning ememy");
			Vector2 spawnPos = new Vector2(MathUtils.random(0f, 800f), 520);
			Vector2 dir = new Vector2().set(playerPos).sub(spawnPos).nor();
			Enemy nme = new Enemy(spawnPos.x, spawnPos.y, dir);
			enemies.add(nme);
		}
		
		player.translateX(playerSpeed * Gdx.graphics.getDeltaTime());
		
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		Iterator<Bullet> bs = bullets.iterator();
		while (bs.hasNext()) {
			Bullet b = bs.next();
			b.tick(Gdx.graphics.getDeltaTime());
			if (!b.dead) {
				b.draw(batch);
			} else {
				bs.remove();
			}
		}
		Iterator<Enemy> es = enemies.iterator();
		while (es.hasNext()) {
			Enemy e = es.next();
			e.tick(Gdx.graphics.getDeltaTime());
			if (!e.dead) {
				e.draw(batch);
			} else {
				es.remove();
			}
		}
		player.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.getTexture().dispose();
	}
}
