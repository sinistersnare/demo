package com.sinsnare.demo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Sprite {
	
	float speed;
	Vector2 currentDirection, velocity, movement;
	boolean dead;

	public Enemy(float x, float y, Vector2 direction) {
		super(new Texture("enemy.png"));
		this.setPosition(x,  y);
		speed = 50;
		currentDirection = direction;
		velocity = new Vector2(currentDirection).scl(speed);
		movement = new Vector2();
		dead = false;
	}

	public void tick(float delta) {
		Vector2 cur = new Vector2(this.getX(), this.getY());
		Vector2 next = cur.add(movement.set(velocity).scl(delta));
		this.setPosition(next.x, next.y);
	}
	
	public void dispose() {
		this.getTexture().dispose();
	}

}
