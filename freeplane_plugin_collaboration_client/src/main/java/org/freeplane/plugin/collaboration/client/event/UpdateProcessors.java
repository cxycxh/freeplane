package org.freeplane.plugin.collaboration.client.event;

import java.util.HashMap;

import org.freeplane.features.map.MapModel;

public class UpdateProcessors implements UpdateProcessor<MapUpdated> {
	
	private final HashMap<Class<? extends MapUpdated>, UpdateProcessor<? extends MapUpdated> > processors = new HashMap<>();

	public <T extends MapUpdated> UpdateProcessors addProcessor(Class<T> eventClass, UpdateProcessor<T> processor) {
		processors.put(eventClass, processor);
		return this;
	}
	

	@Override
	public void onUpdate(MapModel map, MapUpdated event) {
		final Class<?>[] interfaces = event.getClass().getInterfaces();
		for(Class<?> eventClassCandidate : interfaces) {
			final UpdateProcessor<?> updateProcessor = processors.get(eventClassCandidate);
			if(updateProcessor != null) {
				updateProcessor.onMapUpdated(map, event);
				return;
			}
			
		}
		throw new IllegalArgumentException("No processor available for " + event);
	}
}
