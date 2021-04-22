package de.fhdw.chitter.receivers;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.receivers.interfaces.Receiver;

public class ConsoleReceiver implements Receiver {
	public void receiveMessage(NewsMessage newMessage) {
		System.out.println(newMessage.getTopics().toString() + "-Nachricht erhalten " + newMessage);
	}
}
