package com.sinsnare.demo;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class PlayerMovement implements InputProcessor {
	
	float xMovement = 0;

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.A:
		case Keys.LEFT:
			xMovement -= 300;
			break;
		case Keys.D:
		case Keys.RIGHT:
			xMovement += 300;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.A:
		case Keys.LEFT:
			xMovement += 300;
			break;
		case Keys.D:
		case Keys.RIGHT:
			xMovement -= 300;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
