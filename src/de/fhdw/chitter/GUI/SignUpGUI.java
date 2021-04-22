package de.fhdw.chitter.gui;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.fhdw.chitter.services.UserService;

public class SignUpGUI extends JFrame implements ActionListener {

    private JTextField txtNewUsername;
    private JTextField txtNewPassword;
    private JTextField txtNewPasswordConfirm;
    private JTextField txtStaff;
    private JTextField txtPassword;

    private JButton btnLogin;
    private JButton btnSignUp;

    private JLabel lblUsermsg;

    private boolean isLoggedin = false;

    public SignUpGUI() {
        this.setTitle("SignUp GUI");
        this.setSize(600, 170);
        this.setLocation(50, 150);
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
        txtNewPassword = new JPasswordField(10);
        txtNewPasswordConfirm = new JPasswordField(10);

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
                    btnLogin.setEnabled(false);

                    btnSignUp.setEnabled(true);
                    txtNewUsername.setEnabled(true);
                    txtNewPassword.setEnabled(true);
                    txtNewPasswordConfirm.setEnabled(true);

                } else {
                    lblUsermsg.setText("Benutzername bzw. Passwort falsch.");

                    txtStaff.setEnabled(true);
                    txtPassword.setEnabled(true);
                    btnLogin.setEnabled(true);

                    btnSignUp.setEnabled(false);
                    txtNewUsername.setEnabled(false);
                    txtNewPassword.setEnabled(false);
                    txtNewPasswordConfirm.setEnabled(false);
                }
            }
        });

        topPanel.add(btnLogin);

        add(topPanel, BorderLayout.PAGE_START);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        lblUsermsg = new JLabel("");
        bottomPanel.add(lblUsermsg);

        btnSignUp = new javax.swing.JButton("Benutzer erstellen");
        btnSignUp.addActionListener(this);
        bottomPanel.add(btnSignUp);

        add(bottomPanel, BorderLayout.PAGE_END);

        JPanel basePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        basePanel.add(new JLabel("Benutzername:"), c);

        txtNewUsername = new JTextField();
        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        basePanel.add(txtNewUsername, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        basePanel.add(new JLabel("Passwort:"), c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        basePanel.add(txtNewPassword, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        basePanel.add(new JLabel("Bestätigen:"), c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        basePanel.add(txtNewPasswordConfirm, c);

        add(basePanel, BorderLayout.CENTER);

        txtStaff.setEnabled(true);
        txtPassword.setEnabled(true);

        txtNewUsername.setEnabled(false);
        txtNewPassword.setEnabled(false);
        txtNewPasswordConfirm.setEnabled(false);
        btnSignUp.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (isLoggedin == false) {
            lblUsermsg.setText("Benutzer ist nicht eingeloggt.");
            return;
        }

        String username = txtNewUsername.getText();
        String pw1 = txtNewPassword.getText();
        String pw2 = txtNewPasswordConfirm.getText();

        if (!pw1.equals(pw2)) {
            lblUsermsg.setText("Passwörter stimmen nicht überein!");
            return;
        }

        try {
            if (UserService.signUp(username, pw1)) {
                lblUsermsg.setText("User created successfully");
            }

        } catch (Exception e) {
            lblUsermsg.setText(e.getMessage());
        }
    }

}
