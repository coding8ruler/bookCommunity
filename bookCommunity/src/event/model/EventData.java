package event.model;

import event.model.Event;

public class EventData {
	
	private Event event;
	
	public EventData(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "EventData [event=" + event + "]";
	}
}
