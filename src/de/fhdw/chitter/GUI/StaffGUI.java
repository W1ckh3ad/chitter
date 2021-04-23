package de.fhdw.chitter.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.fhdw.chitter.models.*;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.services.TopicService;
import de.fhdw.chitter.services.UserService;
import de.fhdw.chitter.Newssystem;

public class StaffGUI extends JFrame implements ActionListener {

	private JTextField txtTopic;
	private JTextField txtHeadline;
	private JTextArea txtText;
	private JTextField txtStaff;
	private JTextField txtPassword;

	private JButton btnSend;
	private JButton btnLogin;

	private JLabel lblUsermsg;

	private boolean isLoggedin = false;

	private MessagesProcessor messagesProcessor = MessagesProcessor.getInstance();

	public StaffGUI() {
		this.setTitle("Staff GUI");
		this.setSize(1000, 620);

		this.setLocation(50, 250);
		this.setVisible(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new java.awt.BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Name:"));
		txtStaff = new JTextField(10);
		txtStaff.setText("Max");

		topPanel.add(txtStaff);
		topPanel.add(new JLabel("Passwort:"));
		txtPassword = new JPasswordField(10);

		topPanel.add(txtPassword);
		btnLogin = new javax.swing.JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isLoggedin = UserService.logIn(txtStaff.getText(), txtPassword.getText());

				if (isLoggedin) {
					lblUsermsg.setText("Benutzer ist eingeloggt.");

					txtStaff.setEnabled(false);
					txtPassword.setEnabled(false);

					btnSend.setEnabled(true);
					txtText.setEnabled(true);

					btnLogin.setEnabled(false);
				} else {
					lblUsermsg.setText("Benutzername bzw. Passwort falsch.");

					txtStaff.setEnabled(true);
					txtPassword.setEnabled(true);

					btnSend.setEnabled(false);
					txtText.setEnabled(false);
				}
			}
		});

		topPanel.add(btnLogin);

		add(topPanel, BorderLayout.PAGE_START);

		JPanel bottomPanel = new JPanel(new FlowLayout());

		lblUsermsg = new JLabel("keine neuen Nachrichten");
		bottomPanel.add(lblUsermsg);

		btnSend = new javax.swing.JButton("Senden");
		btnSend.addActionListener(this);
		bottomPanel.add(btnSend);

		add(bottomPanel, BorderLayout.PAGE_END);

		JPanel basePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		basePanel.add(new JLabel("Topic:"), c);

		txtTopic = new JTextField();
		c.gridx = 1;
		c.weightx = 1;
		c.gridwidth = 2;
		basePanel.add(txtTopic, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		basePanel.add(new JLabel("Headline:"), c);

		txtHeadline = new JTextField();
		c.gridx = 1;
		c.weightx = 1;
		c.gridwidth = 2;
		basePanel.add(txtHeadline, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		basePanel.add(new JLabel("Text:"), c);

		txtText = new JTextArea();
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridwidth = 2;
		c.gridheight = 2;
		basePanel.add(txtText, c);

		add(basePanel, BorderLayout.CENTER);

		txtStaff.setEnabled(true);
		txtPassword.setEnabled(true);

		btnSend.setEnabled(false);
		txtText.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (isLoggedin == false) {
			lblUsermsg.setText("Benutzer ist nicht eingeloggt.");
			return;
		}

		SimpleDateFormat sdf_message = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		String staff = txtStaff.getText();
		String topicStr = txtTopic.getText();
		String headline = txtHeadline.getText();
		String text = txtText.getText();
		String date = sdf_message.format(System.currentTimeMillis());

		try {
			var topicsWithSpace = topicStr.split(",");
			var topicsWithoutSpace = new String[topicsWithSpace.length];
			for (int i = 0; i < topicsWithSpace.length; i++) {
				topicsWithoutSpace[i] = topicsWithSpace[i].trim();
			}
			NewsMessage newsMessage = new NewsMessage(date, staff, topicsWithoutSpace, headline, text);

			TopicService.post(newsMessage.getAllTopics());
			messagesProcessor.post(newsMessage);
			Newssystem.getInstance().publish(newsMessage);
			lblUsermsg.setText("NewsMessage send successfully [" + date + "]");
		} catch (Exception e) {
			e.printStackTrace();
			lblUsermsg.setText(e.getMessage());
		}
	}
}
