package de.fhdw.chitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhdw.chitter.models.*;
import de.fhdw.chitter.utils.MarkDownParser;

public class Newssystem {
	public Map<NewsMessageTopic, List<Receiver>> receivers = new HashMap<>();
	
	private Newssystem()
	{
		for(NewsMessageTopic topic : NewsMessageTopic.values()) {
			this.receivers.put(topic, new ArrayList<>());
		}
	}
	// Zustand Singleton pattern
	private static Newssystem instance;
	public static Newssystem getInstance()
	{
		if(instance == null)
		{
			instance = new Newssystem();
		}
		
		return instance;
	}

	public void register(NewsMessageTopic topic, Receiver receiver) {
		this.receivers.get(topic).add(receiver);
	}

	public void unregister(NewsMessageTopic topic, Receiver receiver) {
		this.receivers.get(topic).remove(receiver);
	}

	public void registerAllTopics(Receiver receiver) {
		for (NewsMessageTopic topic : NewsMessageTopic.values()) {
			this.register(topic, receiver);
		}
	}

	public void unregisterAllTopics(Receiver receiver) {
		for (NewsMessageTopic topic : NewsMessageTopic.values()) {
			this.unregister(topic, receiver);
		}
	}

	public void publish(NewsMessage msg) {
		msg.setText( MarkDownParser.parse(msg.getText()));
		for (Receiver receiver : this.receivers.get(msg.getTopic())) {
			receiver.receiveMessage(msg);
		}
	}
}


