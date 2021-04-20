package de.fhdw.chitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.codec.digest.DigestUtils;

import de.fhdw.chitter.extern.RestAPIServer;
import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.gui.*;
import de.fhdw.chitter.utils.*;
import de.fhdw.chitter.models.*;
import de.fhdw.chitter.processors.MessageTypeProcessor;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.processors.UsersProcessor;
import de.fhdw.chitter.receivers.WebSocketReceiver;
import de.fhdw.chitter.services.UserService;

// Directories
// Models (für DTO)
// GUI (Admin GUI, RecieverGUI, StaffGUI)
// extern (ordner bleibt)
// utils (MDParse, FileWriter, Filereader, Logger)
// controller (Benutzercontroller, TopicController, MessageController)
// 

public class Main {

	public static void main(String[] args) {

		// start newsserver
		Newssystem newssystem = Newssystem.getInstance();
		// var usersProcessor = UsersProcessor.getInstance();
		// start external server api
		System.out.println(DigestUtils.sha256Hex("passwort"));
		System.out.println(DigestUtils.sha256Hex("passwort"));
		RestAPIServer server = new RestAPIServer("127.0.0.1", 8080);
		server.start();

		WebSocketServer wsServer = WebSocketServer.getInstance();
		wsServer.start();

		WebSocketReceiver.getInstance().register();

		// start gui
		new AdminGUI();

		BufferedReader readUConsole = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Q zum beenden");

			try {
				String userinput = readUConsole.readLine();

				if (userinput.startsWith("q")) {
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("Unknown execption " + e);
			}

		}
	}

}

// TODO: Benutzerverwaltung über externe Konfigurationsdatei
// TODO: ändern des Dateiformates auf XML
// TODO: ausgabe der Logmeldungen über Console, GUI oder Datei

// XML Config für Benutzerverwaltung
// - beinhaltet max Anzahl von Redakteuren
// Passwortdatei für username:pw (.txt)

// TODO: einheitlicher message to string "parser"
// Parser Klasse erstellen

// TODO: unregister funktion

// TODO: nachrichten mit mehreren Topics
//

// TODO: neue topics: Wissenschaft, Technik, Computer,
// Enum erweitern

// TODO: alle Nachrichten sollen in eine Datei geschrieben werden
// im Filewriter definieren

// TODO: ein receiver soll die letzten 5 Nachrichten zu seinem Topic angezeigt
// bekommen

// TODO: nachrichten von Dateisystem schreiben und lesen

// TODO: Websocket adapter

// TODO: Datenbankanbindung
// Simulations klasse erzeugen

// TODO: Rest API als JSON serialisieren
// keine HTML return sonder JSONStrings
// müssen Endpunkte zum abfragen von messages erstellt werden

// NewsMessage
// Data Transfer Object (DTO)
// getter Setter
// file write & reader auslagern
// topic Array vom enum Topic
// bei publish funktionen durch alle Topics iterieren

// Staff
// DTO
// getter Setter
// parameter constructor erzeugen

// NewsSystem
// Parser auslagern