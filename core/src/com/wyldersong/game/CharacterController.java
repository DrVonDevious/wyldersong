package com.wyldersong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CharacterController {
	private Vector3 position;
	private Vector3 rotation;
	private boolean isGodMode = false;
	private final PerspectiveCamera camera;
	private final Vector3 tempVector = new Vector3();

	public CharacterController(int x, int y, int z) {
		this.position = new Vector3(x, y, z);
		this.rotation = new Vector3();
		this.camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.position.set(x, y, z);
		camera.near = 1f;
		camera.far = 100f;
		camera.update();
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	@SuppressWarnings("unused")
	public Vector3 getRotation() {
		return rotation;
	}

	@SuppressWarnings("unused")
	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}

	public PerspectiveCamera getCamera() {
		return this.camera;
	}

	@SuppressWarnings("unused")
	public void toggleGodMode() {
		isGodMode = !isGodMode;
	}

	public void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).nor().scl(0.2f);
			this.position.add(tempVector);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).nor().scl(-0.2f);
			this.position.add(tempVector);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).crs(camera.up).nor().scl(-0.2f);
			this.position.add(tempVector);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).crs(camera.up).nor().scl(0.2f);
			this.position.add(tempVector);
		}

		if (isGodMode) {
			if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				setPosition(new Vector3(this.position.x, this.position.y - 0.2f, this.position.z));
			}

			if (Gdx.input.isKeyPressed(Input.Keys.E)) {
				setPosition(new Vector3(this.position.x, this.position.y + 0.2f, this.position.z));
			}
		}

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			float deltaX = -Gdx.input.getDeltaX() * 1.0f;
			float deltaY = -Gdx.input.getDeltaY() * 1.0f;
			camera.direction.rotate(camera.up, deltaX);
			tempVector.set(camera.direction).crs(camera.up).nor();
			camera.direction.rotate(tempVector, deltaY);
		}

		this.camera.position.set(new Vector3(position.x, position.y + 4, position.z));
		this.camera.update();
	}

	public void update() {
		this.handleInput();
	}

	public void dispose() {
		// dispose of things here
	}
}
