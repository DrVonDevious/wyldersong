package com.wyldersong.game.ecs.systems;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.SpriteComponent;

public class RenderSystem extends EntitySystem {
	ModelBatch modelBatch;

	public RenderSystem(Object... resources) {
		super(resources);

		useComponents(SpriteComponent.class);
		modelBatch = (ModelBatch) getResource(ModelBatch.class);
	}

	@Override
	public void update(Entity entity) {
		SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);

		modelBatch.render(sprite.modelInstance);
	}

	@Override
	public void dispose(Entity entity) {
		modelBatch.dispose();
	}
}
