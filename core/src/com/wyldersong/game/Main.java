package com.wyldersong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.wyldersong.game.ecs.World;
import com.wyldersong.game.ecs.components.*;
import com.wyldersong.game.ecs.systems.*;
import org.joml.Vector2f;

public class Main extends ApplicationAdapter {
	World world;

	@Override
	public void create () {
		world = new World();

		// Store the camera component, so we can add it to the player entity and use it in the render system
		CameraComponent cameraComponent = new CameraComponent();
		// Store the player's position component, so we can use it for the sprite's billboard target
		PositionComponent positionComponent = new PositionComponent(0, 0, 10f);

		world.createEntity(
			new SpriteComponent(
				"pine_tree.png",
				new Vector2f(9f, 9f),
				true
			),
			new PositionComponent(0, 5, -10)
		);

		// Create the player entity
		world.createEntity(
			new PlayerComponent(),
			positionComponent,
			new RotationComponent(0, 0, -1),
			new VelocityComponent(),
			cameraComponent
		);

		world.addSystem(new PhysicsSystem());
		world.addSystem(new CameraSystem());
		world.addSystem(new PlayerControllerSystem());
		world.addSystem(new SpriteSystem(cameraComponent));
		world.addSystem(new RenderSystem(cameraComponent));
	}

	@Override
	public void render () {
		world.update();
	}
	
	@Override
	public void dispose () {
		world.dispose();
	}
}
