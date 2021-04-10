package de.fhdw.chitter.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.models.NewsMessageTopic;
import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.*;

public class ReceiverGUI extends JFrame implements ActionListener, Receiver {

	private JTextField txtTopic;
	private JButton btnRegister;
	private JButton btnUnregister;
	private JTextArea txtText;
	// unregister Button
	// Enabled wenn Register Button geklickt
	// Löscht eventlistener vom RegisterButton
	// Speichern, für was man sich registriert hat, damit dem Benutzer mitgeteilt
	// wird, welche Topics unregistert sind

	public ReceiverGUI() {
		// Titel Ändern
		this.setTitle("Receiver GUI");
		this.setSize(1000, 1620);
		// this.setResizable(false);
		this.setLocation(150, 50);
		this.setVisible(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new java.awt.BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Topic:"));
		txtTopic = new JTextField(20);
		txtTopic.setText("Sport");
		// var definitions var btnRegister
		topPanel.add(txtTopic);

		btnRegister = new javax.swing.JButton("Register");
		btnRegister.addActionListener(this);
		topPanel.add(btnRegister);

		btnUnregister = new javax.swing.JButton("Unregister");
		btnUnregister.addActionListener(this);
		topPanel.add(btnUnregister);

		add(topPanel, BorderLayout.PAGE_START);

		JPanel basePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		basePanel.add(new JLabel("Nachrichten:"), c);

		txtText = new JTextArea();
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridwidth = 2;
		c.gridheight = 2;
		basePanel.add(txtText, c);

		add(basePanel, BorderLayout.CENTER);

		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String topicStr = txtTopic.getText();
		NewsMessageTopic topic;
		try {
			topic = NewsMessageTopic.valueOf(topicStr);
		} catch (Exception e) {
			txtText.append("Kein topic mit dem Namen " + topicStr + " verfügbar");
			return;
		}

		if (arg0.getSource() == btnRegister) {
			this.registerTopic(topic);
		}

		if (arg0.getSource() == btnUnregister) {
			this.unregisterTopic(topic);
		}
	}

	protected void registerTopic(NewsMessageTopic topic) {
		Newssystem.getInstance().register(topic, this);
		txtText.append("Topic " + topic.toString() + " wurde registriert\n");

		// Listet alle Dateien auf und looped durch alle
		try {

			String[] files = MyFileHandler.getFileNames("data");
			if (files.length > 0) {
				for (String f : files) {
					try {
						NewsMessage msg = MyFileHandler.readFromFile("data/" + f);
						if (msg.getTopic().equals(topic)) {
							txtText.append("#########################\n");
							txtText.append(msg.toString());

						}
					} catch (Exception e) {
						System.out.println("Error");
						e.printStackTrace();
						continue;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("####################################################################################");
			System.out.println("Error while doing stuff");

			System.out.println("####################################################################################");
			e.printStackTrace();
		}
	}

	protected void unregisterTopic(NewsMessageTopic topic) {
		Newssystem.getInstance().unregister(topic, this);
		txtText.append("Topic " + topic.toString() + " wurde deregistriert\n");
	}

	public void receiveMessage(NewsMessage msg) {
		txtText.append("#########################\n");
		txtText.append(msg.toString());
	}
}
