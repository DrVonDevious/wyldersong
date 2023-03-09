package com.wyldersong.game.ecs;

import java.util.UUID;

/**
 * Has an ID and a list of Components
 */
public class Entity {
	public UUID id;

	private final ComponentFamily componentFamily;

	@SuppressWarnings("unused")
	public Entity() {
		this.id = UUID.randomUUID();
		this.componentFamily = new ComponentFamily();
	}

	@SuppressWarnings("unused")
	public ComponentFamily getComponentFamily() {
		return componentFamily;
	}

	@SuppressWarnings("unused")
	public Component getComponent(Class<? extends Component> componentClass) {
		return componentFamily.getComponent(componentClass);
	}

	public void addComponent(Component component) {
		componentFamily.addComponent(component);
	}

	@SuppressWarnings("unused")
	public void removeComponent(Class<? extends Component> componentClass) {
		componentFamily.removeComponent(componentClass);
	}
}
