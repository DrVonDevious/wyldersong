package com.wyldersong.game.ecs.components;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.wyldersong.game.ecs.Component;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class SpriteComponent implements Component {
	public Texture texture;
	public Model model;
	public ModelInstance modelInstance;
	public Vector2f dimensions;
	public boolean isBillboard;
	public Vector3f billboardTarget;

	public SpriteComponent(String filepath, Vector2f dimensions, boolean isBillboard) {
		this(filepath, dimensions);
		this.isBillboard = isBillboard;
	}

	public SpriteComponent(String filepath, Vector2f dimensions) {
		this.dimensions = dimensions;

		texture = new Texture(filepath);
		ModelBuilder builder = new ModelBuilder();
		model = builder.createRect(
			-dimensions.x / 2, -dimensions.y / 2, 0f, dimensions.x / 2, -dimensions.y / 2, 0f, dimensions.x / 2, dimensions.y / 2, 0f, -dimensions.x / 2, dimensions.y / 2, 0f,
			0f, 0f, 0f,
			new Material(TextureAttribute.createDiffuse(texture), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)),
			VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates
		);
		modelInstance = new ModelInstance(model);
	}

	public boolean hasBillboardTarget() {
		return billboardTarget != null;
	}
}
