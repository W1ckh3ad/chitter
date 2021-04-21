package de.fhdw.chitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.fhdw.chitter.extern.WebServer;
import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.gui.AdminGUI;
import de.fhdw.chitter.receivers.WebSocketReceiver;

// Directories
// Models (für DTO)
// GUI (Admin GUI, RecieverGUI, StaffGUI)
// extern (ordner bleibt)
// utils (MDParse, FileWriter, Filereader, Logger)
// controller (Benutzercontroller, TopicController, MessageController)
// 

public class Main {
	public static void main(String[] args) {
		Newssystem.start();
		WebServer server = new WebServer("127.0.0.1", 8080);
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