package com.wyldersong.game.ecs.systems;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.CameraComponent;
import com.wyldersong.game.ecs.components.PositionComponent;
import com.wyldersong.game.ecs.components.SpriteComponent;

public class SpriteSystem extends EntitySystem {
	public SpriteSystem(Object... resources) {
		super(resources);

		useComponents(SpriteComponent.class, PositionComponent.class);
	}

	@Override
	public void update(Entity entity) {
		SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
		PositionComponent position = (PositionComponent) entity.getComponent(PositionComponent.class);
		CameraComponent cameraComponent = (CameraComponent) getResource(CameraComponent.class);

		Vector3 cameraPosition = cameraComponent.camera.position;

		Matrix4 tmp = new Matrix4();

		if (sprite.isBillboard) {
			tmp.setToLookAt(new Vector3(cameraPosition.x - position.x, 0, -cameraPosition.z + position.z), Vector3.Y);
		}

		sprite.modelInstance.transform.set(new Vector3(position.x, position.y, position.z), tmp.getRotation(new Quaternion()));
	}

	@Override
	public void dispose(Entity entity) {
		SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
		sprite.modelInstance.model.dispose();
	}
}
