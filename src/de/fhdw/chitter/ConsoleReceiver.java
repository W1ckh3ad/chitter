package de.fhdw.chitter;

import de.fhdw.chitter.models.NewsMessage;

public class ConsoleReceiver implements Receiver {
	public void receiveMessage(NewsMessage msg) {
		System.out.println(msg.getTopic().toString() + "-Nachricht erhalten " + msg);
	}
}
