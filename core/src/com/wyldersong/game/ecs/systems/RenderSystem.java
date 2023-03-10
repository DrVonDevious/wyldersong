package com.wyldersong.game.ecs.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.wyldersong.game.ecs.Entity;
import com.wyldersong.game.ecs.EntitySystem;
import com.wyldersong.game.ecs.components.CameraComponent;
import com.wyldersong.game.ecs.components.SpriteComponent;

public class RenderSystem extends EntitySystem {
	private final ModelBatch modelBatch;

	public RenderSystem(Object... resources) {
		super(resources);

		modelBatch = new ModelBatch();
		useComponents(SpriteComponent.class);
	}

	@Override
	public void update(Entity entity) {
		SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
		CameraComponent cameraComponent = (CameraComponent) getResource(CameraComponent.class);

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cameraComponent.camera);
		modelBatch.render(sprite.modelInstance);
		modelBatch.end();
	}

	@Override
	public void dispose(Entity entity) {
		modelBatch.dispose();
	}
}
