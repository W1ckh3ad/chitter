package de.fhdw.chitter;

import java.io.IOException;

import org.atmosphere.websocket.WebSocket;

import de.fhdw.chitter.extern.WebSocketServer;

import de.fhdw.chitter.models.*;
import de.fhdw.chitter.utils.MarkDownParser;

public class Newssystem {
	// Funktion Publish


	// OOP Patterns keine getter und Setter
	
	// Auslagern
	public Receiver[] receiversSport = new Receiver[1000];
	public Receiver[] receiversPolitik = new Receiver[1000];
	public Receiver[] receiversWirtschaft = new Receiver[1000];
	
	public int receiverSportPointer = 0;
	public int receiverPolitikPointer = 0;
	public int receiverWirtschaftPointer = 0;
	
	//auslagern	
	public Staff[] stafflist = new Staff[10];
	

	// auslagern als ENUM
	public NewsMessageTopic[] resorts = NewsMessageTopic.values();
	
	
	private Newssystem()
	{
		Staff s1 = new Staff("Max", "passwort");
		
		stafflist[0] = s1;
		// Stafflist in Dokument auslagern
		stafflist[1] = new Staff("Hans", "12345");		
		stafflist[2] = new Staff("John", "wer?");
		
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
	
	// vereinfachen, mit zweitem parameter
	public void registerSportReceiver(Receiver receiver)
	{
		receiversSport[receiverSportPointer++] = receiver;
	}
	public void registerPolitikReceiver(Receiver receiver)
	{
		receiversPolitik[receiverPolitikPointer++] = receiver;
	}
	public void registerWirtschaftReceiver(Receiver receiver)
	{
		receiversWirtschaft[receiverWirtschaftPointer++] = receiver;
	}
	
	
	// vereinfachen
	// zwei publish-arten
	public void publishSportNews(NewsMessage msg)
	{
		
		msg.setText( MarkDownParser.parse(msg.getText()));
		
		for(int i=0; i<receiverSportPointer; i++)
		{
			receiversSport[i].receiveSportMessage(msg);
		}
		
		publishMessageForTicker(msg);
	}
	public void publishPolitikNews(NewsMessage msg)
	{
		msg.setText( MarkDownParser.parse(msg.getText()));
		
		
		for(int i=0; i<receiverPolitikPointer; i++)
		{
			receiversPolitik[i].receivePolitikMessage(msg);
		}
		
		publishMessageForTicker(msg);
		
	}
	public void publishWirtschaftNews(NewsMessage msg)
	{
		
		msg.setText( MarkDownParser.parse(msg.getText()));
		
		
		for(int i=0; i<receiverWirtschaftPointer; i++)
		{
			receiversWirtschaft[i].receiveWirtschaftMessage(msg);
		}
		
		publishMessageForTicker(msg);
	}
	
	public void publishMessageForTicker(NewsMessage msg)
	{
		String msgtext = "<h2>" + msg.getTopic() + "</h2><br><h3>" + msg.getHeadline() + "</h3><br>" + "\n" 
				+ msg.getText() + "<br><br><hr>";
		
		
		WebSocket connection = WebSocketServer.getInstance().currentConnection;
		
		if(connection == null)
		{
			return;
		}
		
		try {
			connection.write(msgtext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}


