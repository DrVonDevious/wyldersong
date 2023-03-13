package com.wyldersong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.wyldersong.game.ecs.World;
import com.wyldersong.game.ecs.components.*;
import com.wyldersong.game.ecs.systems.*;
import com.wyldersong.game.map.Map;
import org.joml.Vector2f;

public class Main extends ApplicationAdapter {
	World world;
	MapRenderSystem mapRenderSystem;
	Map map;
	ModelBatch modelBatch;
	CameraComponent cameraComponent;

	@Override
	public void create () {
		world = new World();
		map = new Map(1, 1);
		modelBatch = new ModelBatch();

		// Store the camera component, so we can add it to the player entity and use it in the render system
		cameraComponent = new CameraComponent();
		// Store the player's position component, so we can use it for the sprite's billboard target
		PositionComponent positionComponent = new PositionComponent(0, 0, 0f);

		mapRenderSystem = new MapRenderSystem(map, modelBatch);

		world.createEntity(
			new SpriteComponent(
				"pine_tree.png",
				new Vector2f(9f, 9f),
				true
			),
			new PositionComponent(0, 4.5f, -10)
		);

		world.createEntity(
			new SpriteComponent(
				"pine_tree.png",
				new Vector2f(9f, 9f),
				true
			),
			new PositionComponent(4, 4.5f, -17)
		);

		// Create the player entity
		world.createEntity(
			new PlayerComponent(),
			positionComponent,
			new RotationComponent(0, 0, 0),
			new VelocityComponent(),
			cameraComponent
		);

		world.addSystem(new PhysicsSystem());
		world.addSystem(new CameraSystem());
		world.addSystem(new PlayerControllerSystem());
		world.addSystem(new SpriteSystem(cameraComponent));
		world.addSystem(new RenderSystem(modelBatch));
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cameraComponent.camera);
		mapRenderSystem.render();
		world.update();
		modelBatch.end();
	}
	
	@Override
	public void dispose () {
		world.dispose();
	}
}
