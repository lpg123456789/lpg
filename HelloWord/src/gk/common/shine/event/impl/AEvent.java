package gk.common.shine.event.impl;

import gk.common.shine.event.GameEvent;

public class AEvent implements GameEvent{
	
	public final static String Id = AEvent.class.getName();

	private long playerId;
	
	public AEvent(long playerId) {
		this.playerId = playerId;
	}

	@Override
	public String getId() {
		return Id;
	}
	
}
