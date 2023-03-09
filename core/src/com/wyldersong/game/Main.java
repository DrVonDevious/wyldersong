package com.wyldersong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.wyldersong.game.graphics.PlayerCamera;
import com.wyldersong.game.graphics.models.Sprite;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Main extends ApplicationAdapter {
	PlayerCamera camera;
	ModelBatch modelBatch;
	Sprite sprite;
	CharacterController characterController;

	@Override
	public void create () {
		camera = new PlayerCamera();
		modelBatch = new ModelBatch();
		sprite = new Sprite("pine_tree.png", new Vector3f(0, 5, 0), new Vector2f(9f, 9f));
		characterController = new CharacterController(0, 0, 10);
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		characterController.update();

		modelBatch.begin(characterController.getCamera());
		modelBatch.render(sprite.modelInstance);
		modelBatch.end();

		characterController.update();
		Vector3 position = characterController.getPosition();
		sprite.update(new Vector3f(position.x, position.y, position.z));
	}
	
	@Override
	public void dispose () {
		modelBatch.dispose();
		sprite.model.dispose();
		characterController.dispose();
	}
}
