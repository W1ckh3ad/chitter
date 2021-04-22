package de.fhdw.chitter;

import java.util.*;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.processors.MessageTypeProcessor;
import de.fhdw.chitter.receivers.interfaces.Receiver;

public class Newssystem {
	public Map<String, Set<Receiver>> receivers = new HashMap<>();
	private MessageTypeProcessor messageTypeProcessor = MessageTypeProcessor.getInstance();

	private Newssystem() {
		update();
	}

	private static Newssystem instance;

	public static Newssystem getInstance() {
		start();
		return instance;
	}

	public static void start() {
		if (instance == null) {
			instance = new Newssystem();
		}
	}

	private void update() {
		for (String topic : messageTypeProcessor.get()) {
			if (!receivers.containsKey(topic)) {
				this.receivers.put(topic, new HashSet<>());
			}
		}
	}

	public void register(String topic, Receiver receiver) {
		this.receivers.get(topic).add(receiver);
	}

	public void unregister(String topic, Receiver receiver) {
		this.receivers.get(topic).remove(receiver);
	}

	public void registerAllTopics(Receiver receiver) {
		for (String topic : messageTypeProcessor.get()) {
			this.register(topic, receiver);
		}
	}

	public void unregisterAllTopics(Receiver receiver) {
		for (String topic : messageTypeProcessor.get()) {
			this.unregister(topic, receiver);
		}
	}

	public void publish(NewsMessage newsMessage) {
		update();
		var topics = newsMessage.getAllTopics();
		for (String topic : topics) {
			for (Receiver receiver : this.receivers.get(topic)) {
				receiver.receiveMessage(newsMessage);
			}
		}
	}
}
