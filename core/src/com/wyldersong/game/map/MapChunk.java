package com.wyldersong.game.map;

import java.util.ArrayList;
import java.util.List;

public class MapChunk {
	public List<List<Tile>> tiles;
	public int width = 16;
	public int height = 16;

	public MapChunk() {
		// Create the tiles
		tiles = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			List<Tile> tileRow = new ArrayList<>();
			for (int y = 0; y < height; y++) {
				tileRow.add(new Tile(x - ((width / 2) - 1), y - ((height / 2) - 1), "grass"));
			}
			tiles.add(tileRow);
		}
	}

	public Tile getTile(int x, int y) {
		return tiles.get(x).get(y);
	}
}
