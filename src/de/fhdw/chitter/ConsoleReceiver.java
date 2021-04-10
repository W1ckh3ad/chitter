package de.fhdw.chitter;

import de.fhdw.chitter.models.NewsMessage;

public class ConsoleReceiver implements Receiver {
	public void receiveMessage(NewsMessage msg) {
		System.out.println(msg.getTopics().toString() + "-Nachricht erhalten " + msg);
	}
}
