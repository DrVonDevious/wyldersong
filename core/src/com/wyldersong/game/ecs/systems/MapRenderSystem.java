package com.wyldersong.game.ecs.systems;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.wyldersong.game.map.Map;
import com.wyldersong.game.map.MapChunk;

public class MapRenderSystem {
	ModelBatch modelBatch;
	Map map;

	public MapRenderSystem(Map map, ModelBatch modelBatch) {
		this.modelBatch = modelBatch;
		this.map = map;
	}

	public void render() {
		for (int x = 0; x < map.width; x++) {
			for (int y = 0; y < map.height; y++) {
				MapChunk chunk = map.getChunk(x, y);
				for (int tileX = 0; tileX < chunk.width; tileX++) {
					for (int tileY = 0; tileY < chunk.height; tileY++) {
						modelBatch.render(chunk.getTile(tileX, tileY).modelInstance);
					}
				}
			}
		}
		modelBatch.flush();
	}
}
