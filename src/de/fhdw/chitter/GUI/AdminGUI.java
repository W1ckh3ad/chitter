package de.fhdw.chitter.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminGUI extends JFrame {

	private JButton btnNewStaff;
	private JButton btnNewReceiver;
	private JButton btnSignUp;
	private JButton btnExit;

	// ist ok
	public AdminGUI() {
		this.setTitle("Admin GUI");
		this.setSize(1000, 620);
		// this.setResizable(false);
		this.setLocation(50, 50);
		this.setVisible(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		getContentPane().setLayout(new java.awt.BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout());

		btnNewStaff = new JButton("Neuer Redakteur");
		btnNewStaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new StaffGUI();
			}
		});
		topPanel.add(btnNewStaff);

		btnNewReceiver = new JButton("Neuer Leser");
		btnNewReceiver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ReceiverGUI();
			}
		});
		topPanel.add(btnNewReceiver);

		btnSignUp = new JButton("SignUp Redakteur");
		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SignUpGUI();
			}
		});
		topPanel.add(btnSignUp);

		btnExit = new JButton("Beenden");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		topPanel.add(btnExit);

		add(topPanel, BorderLayout.PAGE_START);
		pack();
	}

}
