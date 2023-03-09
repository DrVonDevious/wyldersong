package com.wyldersong.game.ecs;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of Components for an entity
 */
public class ComponentFamily {
	private final List<Component> components;

	public ComponentFamily() {
		components = new ArrayList<>();
	}

	public Component getComponent(Class<? extends Component> componentClass) {
		for (Component component : components) {
			if (component.getClass() == componentClass) {
				return component;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	public List<Component> getComponents() {
		return components;
	}

	public void addComponent(Component component) {
		components.add(component);
	}

	public void removeComponent(Class<? extends Component> componentClass) {
		for (Component component : components) {
			if (component.getClass() == componentClass) {
				components.remove(component);
				return;
			}
		}
	}
}
