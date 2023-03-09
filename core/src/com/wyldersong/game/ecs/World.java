package com.wyldersong.game.ecs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The World contains all the Entities and Systems
 */
@SuppressWarnings("unused")
public class World {
	private final List<Entity> entities;
	private final List<EntitySystem> entitySystems;

	public World() {
		entities = new ArrayList<>();
		entitySystems = new ArrayList<>();
	}

	public void createEntity(Component... components) {
		Entity entity = new Entity();
		for (Component component : components) {
			entity.addComponent(component);
		}
		entities.add(entity);
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	@SuppressWarnings("unused")
	public void removeEntity(UUID id) {
		for (Entity entity : entities) {
			if (entity.id == id) {
				entities.remove(entity);
				return;
			}
		}
	}

	@SuppressWarnings("unused")
	public EntitySystem getSystem(Class<? extends EntitySystem> systemClass) {
		for (EntitySystem entitySystem : entitySystems) {
			if (entitySystem.getClass() == systemClass) {
				return entitySystem;
			}
		}
		return null;
	}

	@SafeVarargs
	public final <T extends Component> List<Entity> getEntitiesWithComponents(Class<T>... componentClasses) {
		List<Entity> entitiesWithComponents = new ArrayList<>();

		for (Entity entity : this.entities) {
			boolean hasAllComponents = true;

			for (Class<? extends Component> componentClass : componentClasses) {
				if (entity.getComponent(componentClass) == null) {
					hasAllComponents = false;
					break;
				}
			}

			if (hasAllComponents) {
				entitiesWithComponents.add(entity);
			}
		}

		return entitiesWithComponents;
	}

	public void addSystem(EntitySystem entitySystem) {
		entitySystem.setWorld(this);
		entitySystems.add(entitySystem);
	}

	@SuppressWarnings("unused")
	public void removeSystem(EntitySystem entitySystem) {
		entitySystems.remove(entitySystem);
	}

	public void update() {
		for (EntitySystem entitySystem : entitySystems) {
			entitySystem.internalUpdate();
		}
	}
}
