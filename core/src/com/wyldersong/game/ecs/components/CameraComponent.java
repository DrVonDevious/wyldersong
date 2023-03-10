package com.wyldersong.game.ecs.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.wyldersong.game.ecs.Component;

public class CameraComponent implements Component {
	public PerspectiveCamera camera;
	public float fieldOfView;
	public float viewportWidth;
	public float viewportHeight;
	public float nearClip;
	public float farClip;

	public CameraComponent() {
		this(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.1f, 300f);
	}

	public CameraComponent(float fieldOfView, float viewportWidth, float viewportHeight, float nearClip, float farClip) {
		this.fieldOfView = fieldOfView;
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
		this.nearClip = nearClip;
		this.farClip = farClip;

		camera = new PerspectiveCamera(fieldOfView, viewportWidth, viewportHeight);

		camera.near = nearClip;
		camera.far = farClip;

		camera.update();
	}
}
