package com.wyldersong.game.ecs.systems;

import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.CameraComponent;
import com.wyldersong.game.ecs.components.PositionComponent;
import com.wyldersong.game.ecs.components.RotationComponent;

public class CameraSystem extends EntitySystem {
	public CameraSystem() {
		super();

		useComponents(CameraComponent.class, PositionComponent.class, RotationComponent.class);
	}
	@Override
	public void update(Entity entity) {
		CameraComponent cameraComponent = (CameraComponent) entity.getComponent(CameraComponent.class);
		cameraComponent.camera.update();
	}

	@Override
	public void dispose(Entity entity) {
		// Nothing to dispose of
	}


}
