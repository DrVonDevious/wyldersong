package com.wyldersong.game.ecs.components;

import com.wyldersong.game.ecs.Component;

public class RotationComponent implements Component {
	public float x;
	public float y;
	public float z;

	@SuppressWarnings("unused")
	public RotationComponent() {
		this(0, 0, 0);
	}

	public RotationComponent(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
