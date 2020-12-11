package de.fhdw.chitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.fhdw.chitter.extern.RestAPIServer;
import de.fhdw.chitter.extern.WebSocketServer;

public class Main {

	public static void main(String[] args) {
		
		
		// start newsserver
		Newssystem newssystem = Newssystem.getInstance();
		
		
		// start external server api
		RestAPIServer server = new RestAPIServer("127.0.0.1", 8080);
		server.start();
		
		WebSocketServer wsServer = WebSocketServer.getInstance();
		wsServer.start();
		
		
		// start gui
		new AdminGUI();
		new StaffGUI();
		new ReceiverGUI();
		
		
		BufferedReader readUConsole = new BufferedReader(new InputStreamReader(System.in));   
		while(true)
		{
			System.out.println("Q zum beenden");
			
			try
			{
			String userinput = readUConsole.readLine();
			
				if(userinput.startsWith("q"))
				{
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("Unknown execption " + e);
			}
			
		}
	}

}

//TODO: Benutzerverwaltung über externe Konfigurationsdatei
//TODO: ändern des Dateiformates auf XML
//TODO: ausgabe der Logmeldungen über Console, GUI oder Datei

//TODO: einheitlicher message to string "parser"
//TODO: unregister funktion
//TODO: nachrichten mit mehreren Topics
//TODO: neue topics: Wissenschaft, Technik, Computer, 
//TODO: alle Nachrichten sollen in eine Datei geschrieben werden
//TODO: ein receiver soll die letzten 5 Nachrichten zu seinem Topic angezeigt bekommen
//TODO: nachrichten von Dateisystem schreiben und lesen

//TODO: Websocket adapter
//TODO: Datenbankanbindung
//TODO: Rest API als JSON serialisieren


