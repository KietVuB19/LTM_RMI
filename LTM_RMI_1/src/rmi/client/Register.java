/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.client;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import rmi.server.RMILoginServerControl;

public class Register extends JFrame implements ActionListener {
    private JLabel usernameLabel, passwordLabel, addressLabel, birthdayLabel, sexLabel, descriptionLabel;
    private JTextField usernameField, addressField, birthdayField, sexField, descriptionField;
    private JPasswordField passwordField;
    private JButton registerButton, cancelButton;
    private JPanel panel;

    public Register() {
        super("Registration Form");

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        addressLabel = new JLabel("Address:");
        
        birthdayLabel = new JLabel("Birthday:");
        sexLabel = new JLabel("Sex:");
        descriptionLabel = new JLabel("Description:");
        
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        addressField = new JTextField(20);
        birthdayField = new JTextField(20);
        sexField = new JTextField(20);
        descriptionField = new JTextField(20);
        
        
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        panel = new JPanel(new GridLayout(7, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        
        panel.add(passwordLabel);
        panel.add(passwordField);
        
        panel.add(addressLabel);
        panel.add(addressField);
        
        panel.add(birthdayLabel);
        panel.add(birthdayField);
        
        panel.add(sexLabel);
        panel.add(sexField);
        
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        
        
        panel.add(registerButton);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            RMILoginServerControl impl = new RMILoginServerControl();
            if (e.getSource() == registerButton) {
                // Call the register() method on the RMI server
                boolean registerResult = impl.register(usernameField.getText(), new String(passwordField.getPassword()), addressField.getText(),
                        birthdayField.getText(), sexField.getText(), descriptionField.getText());
                if (registerResult) {
                    JOptionPane.showMessageDialog(this, "Registration successful");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Username already exists");
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
