package de.fhdw.chitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhdw.chitter.models.*;
import de.fhdw.chitter.utils.MarkDownParser;

public class Newssystem {
	// Funktion Publish


	// OOP Patterns keine getter und Setter
	
	// Auslagern
	public Map<NewsMessageTopic, List<Receiver>> receivers = new HashMap<>();
	
	//auslagern	
	public Staff[] stafflist = new Staff[10];
	
	
	private Newssystem()
	{
		Staff s1 = new Staff("Max", "passwort");
		
		stafflist[0] = s1;
		// Stafflist in Dokument auslagern
		stafflist[1] = new Staff("Hans", "12345");		
		stafflist[2] = new Staff("John", "wer?");

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

	public void registerAllTopics(Receiver receiver) {
		for (NewsMessageTopic topic : NewsMessageTopic.values()) {
			this.receivers.get(topic).add(receiver);
		}
	}

	public void publish(NewsMessage msg) {
		msg.setText( MarkDownParser.parse(msg.getText()));
		for (Receiver receiver : this.receivers.get(msg.getTopic())) {
			receiver.receiveMessage(msg);
		}
	}
}


