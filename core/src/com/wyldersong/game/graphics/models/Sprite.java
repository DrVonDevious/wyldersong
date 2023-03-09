package com.wyldersong.game.graphics.models;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Sprite {
	public Texture texture;
	public Model model;
	public ModelInstance modelInstance;
	public Vector3f position;
	public Vector2f dimensions;
	public boolean isBillboard = true;

	public Sprite(String filepath, Vector3f position, Vector2f dimensions) {
		this.position = position;
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

	public void update(Vector3f target) {
		Matrix4 tmp = new Matrix4();

		if (isBillboard) {
			tmp.setToLookAt(new Vector3(target.x - position.x, 0, -target.z + position.z), Vector3.Y);
		}

		modelInstance.transform.set(new Vector3(position.x, position.y, position.z), tmp.getRotation(new Quaternion()));
	}
}
