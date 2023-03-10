package com.wyldersong.game.ecs.components;

import com.wyldersong.game.ecs.Component;

public class PositionComponent implements Component {
	public float x;
	public float y;
	public float z;

	@SuppressWarnings("unused")
	public PositionComponent() {
		this(0, 0, 0);
	}

	public PositionComponent(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
