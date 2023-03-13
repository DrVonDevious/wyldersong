package com.wyldersong.game.map;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Tile {
	public int x;
	public int y;
	public int tileSize = 4;
	public String type;
	public Model model;
	public ModelInstance modelInstance;
	public Texture texture;

	public Tile(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.texture = new Texture(type + ".png");

		ModelBuilder builder = new ModelBuilder();
		model = builder.createRect(
			(x * tileSize), 0f, (y * tileSize) - tileSize, (x * tileSize) - tileSize, 0f, (y * tileSize) - tileSize, (x * tileSize) - tileSize, 0f, (y * tileSize), (x * tileSize), 0f, (y * tileSize),
			0f, 0f, 0f,
			new Material(TextureAttribute.createDiffuse(texture), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)),
			VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates
		);
		modelInstance = new ModelInstance(model);
	}
}
