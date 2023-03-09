package com.wyldersong.game.ecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EntitySystem {
	public List<Class<? extends Component>> componentClasses;
	public World world;

	protected EntitySystem() {
		componentClasses = new ArrayList<>();
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@SafeVarargs
	public final void useComponents(Class<? extends Component>... componentClasses) {
		this.componentClasses.addAll(Arrays.asList(componentClasses));
	}

	public void internalUpdate() {
		@SuppressWarnings("unchecked")
		List<Entity> entities = world.getEntitiesWithComponents(componentClasses.toArray(new Class[0]));

		for (Entity entity : entities) {
			update(entity);
		}
	}

	public abstract void update(Entity entity);
}
