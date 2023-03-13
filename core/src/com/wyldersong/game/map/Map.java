package com.wyldersong.game.map;

import java.util.ArrayList;
import java.util.List;

public class Map {
	public List<List<MapChunk>> chunks;
	public int width;
	public int height;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		// Create the map chunks
		chunks = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			List<MapChunk> chunkRow = new ArrayList<>();
			for (int y = 0; y < height; y++) {
				chunkRow.add(new MapChunk());
			}
			chunks.add(chunkRow);
		}
	}

	public MapChunk getChunk(int x, int y) {
		return chunks.get(x).get(y);
	}
}
