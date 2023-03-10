package com.wyldersong.game.ecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EntitySystem {
	public List<Class<? extends Component>> componentClasses;
	public List<Object> resources;
	public World world;

	protected EntitySystem(Object... resources) {
		this();
		assert this.resources != null;
		this.resources.addAll(Arrays.asList(resources));
	}
	protected EntitySystem() {
		componentClasses = new ArrayList<>();
		resources = new ArrayList<>();
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@SafeVarargs
	public final void useComponents(Class<? extends Component>... componentClasses) {
		this.componentClasses.addAll(Arrays.asList(componentClasses));
	}

	public Object getResource(Class<?> resourceClass) {
		for (Object resource : resources) {
			if (resource.getClass() == resourceClass) {
				return resource;
			}
		}

		return null;
	}

	public void internalUpdate() {
		@SuppressWarnings("unchecked")
		List<Entity> entities = world.getEntitiesWithComponents(componentClasses.toArray(new Class[0]));

		for (Entity entity : entities) {
			update(entity);
		}
	}

	public void internalDispose() {
		@SuppressWarnings("unchecked")
		List<Entity> entities = world.getEntitiesWithComponents(componentClasses.toArray(new Class[0]));

		for (Entity entity : entities) {
			dispose(entity);
		}
	}

	public abstract void update(Entity entity);

	public abstract void dispose(Entity entity);
}
