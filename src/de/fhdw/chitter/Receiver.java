package de.fhdw.chitter;

import de.fhdw.chitter.models.NewsMessage;

public class Receiver {

	// Console Log
	public void receiveSportMessage(NewsMessage msg)
	{
		System.out.println("Sportnachricht erhalten" + msg);
	}
	
	public void receivePolitikMessage(NewsMessage msg)
	{
		System.out.println("Politiknachricht erhalten" + msg);
	}
	
	public void receiveWirtschaftMessage(NewsMessage msg)
	{
		System.out.println("Wirtschaftsnachricht erhalten" + msg);
	}
	
	// neu
	public void reciveMessage(NewsMessage msg) {
		System.out.println(msg.getTopic().toString() + msg);
		
	}
}
