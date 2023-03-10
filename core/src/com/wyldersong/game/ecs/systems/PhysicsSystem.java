package com.wyldersong.game.ecs.systems;

import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.PositionComponent;
import com.wyldersong.game.ecs.components.VelocityComponent;

public class PhysicsSystem extends EntitySystem {
	public PhysicsSystem() {
		super();

		useComponents(PositionComponent.class, VelocityComponent.class);
	}

	@Override
	public void update(Entity entity) {
		PositionComponent position = (PositionComponent) entity.getComponent(PositionComponent.class);
		VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);

		position.x += velocity.dx;
		position.y += velocity.dy;
		position.z += velocity.dz;

		velocity.dx = 0;
		velocity.dy = 0;
		velocity.dz = 0;
	}

	@Override
	public void dispose(Entity entity) {
		// Nothing to dispose of
	}
}
