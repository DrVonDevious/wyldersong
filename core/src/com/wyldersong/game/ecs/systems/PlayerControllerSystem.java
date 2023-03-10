package com.wyldersong.game.ecs.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.*;

public class PlayerControllerSystem extends EntitySystem {
	public PlayerControllerSystem() {
		super();

		useComponents(
			PlayerComponent.class,
			VelocityComponent.class,
			RotationComponent.class,
			CameraComponent.class,
			PositionComponent.class
		);
	}

	@Override
	public void update(Entity entity) {
		PositionComponent positionComponent = (PositionComponent) entity.getComponent(PositionComponent.class);
		VelocityComponent velocityComponent = (VelocityComponent) entity.getComponent(VelocityComponent.class);
		CameraComponent cameraComponent = (CameraComponent) entity.getComponent(CameraComponent.class);

		Vector3 position = new Vector3(positionComponent.x, positionComponent.y, positionComponent.z);
		PerspectiveCamera camera = cameraComponent.camera;

		Vector3 tempVector = new Vector3();

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).nor().scl(0.4f);
			velocityComponent.dz += tempVector.z;
			velocityComponent.dx += tempVector.x;
			velocityComponent.dy += tempVector.y;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).nor().scl(-0.4f);
			velocityComponent.dz += tempVector.z;
			velocityComponent.dx += tempVector.x;
			velocityComponent.dy += tempVector.y;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).crs(camera.up).nor().scl(-0.4f);
			velocityComponent.dz += tempVector.z;
			velocityComponent.dx += tempVector.x;
			velocityComponent.dy += tempVector.y;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			tempVector.set(new Vector3(camera.direction.x, 0, camera.direction.z)).crs(camera.up).nor().scl(0.4f);
			velocityComponent.dz += tempVector.z;
			velocityComponent.dx += tempVector.x;
			velocityComponent.dy += tempVector.y;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			velocityComponent.dy -= 0.2f;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			velocityComponent.dy += 0.2f;
		}

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			float deltaX = -Gdx.input.getDeltaX() * 0.3f;
			float deltaY = -Gdx.input.getDeltaY() * 0.3f;
			camera.direction.rotate(camera.up, deltaX);
			tempVector.set(camera.direction).crs(camera.up).nor();
			camera.direction.rotate(tempVector, deltaY);
		}

		camera.position.set(new Vector3(position.x, position.y + 4, position.z));
		camera.update();
	}

	@Override
	public void dispose(Entity entity) {
		// Nothing to dispose of
	}
}
