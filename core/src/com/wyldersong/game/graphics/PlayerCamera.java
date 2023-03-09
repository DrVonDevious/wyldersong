package com.wyldersong.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import org.joml.Vector3f;

/**
 * The primary camera for the Player
 */
public class PlayerCamera {
	private PerspectiveCamera perspectiveCamera;

	/**
	 * The current position of the player's camera
	 * @default 10f 10f 10f
	 */
	public Vector3f position = new Vector3f(0f, 0f, 10f);

	/**
	 * The current rotation of the player's camera
	 * @default 0f 0f 0f
	 */
	public Vector3f rotation = new Vector3f(0f, 0f, -1f);

	/**
	 * The current field of view for the player's camera
	 * @default 70
	 */
	public int fov = 70;

	/**
	 * Constructs a PlayerCamera Object with the default values
	 */
	public PlayerCamera() {
		perspectiveCamera = new PerspectiveCamera(fov, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		perspectiveCamera.near = 1f;
		perspectiveCamera.far = 300f;

		update();
	}

	/**
	 * Constructs a PlayerCamera Object with the given position and rotation vectors
	 * @param position The starting position value of the camera
	 * @param rotation The starting rotation value of the camera
	 */
	@SuppressWarnings("unused")
	public PlayerCamera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
		new PlayerCamera();
	}

	public void update() {
		perspectiveCamera.update();
	}

	/**
	 * Gets the underlying LibGDX PerspectiveCamera
	 * @return A PerspectiveCamera Object
	 */
	@SuppressWarnings("unused")
	public PerspectiveCamera getPerspectiveCamera() {
		return perspectiveCamera;
	}
}
