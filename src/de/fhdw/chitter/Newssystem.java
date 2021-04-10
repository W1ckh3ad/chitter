package de.fhdw.chitter;

import java.util.*;

import de.fhdw.chitter.models.*;
import de.fhdw.chitter.utils.MarkDownParser;

public class Newssystem {
	public Map<NewsMessageTopic, Set<Receiver>> receivers = new HashMap<>();

	// Todo: We should add a history of news messages and add a method to initialize it from the files
	
	private Newssystem()
	{
		for(NewsMessageTopic topic : NewsMessageTopic.values()) {
			this.receivers.put(topic, new HashSet<>());
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
		// Todo: Markdown parsing is not the responsibility of news system
		msg.setText( MarkDownParser.parse(msg.getText()));
		for (NewsMessageTopic topic : msg.getTopics()) {
			for (Receiver receiver : this.receivers.get(topic)) {
				receiver.receiveMessage(msg);
			}
		}
	}
}


