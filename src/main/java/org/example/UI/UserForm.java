package org.example.UI;

import org.example.ConnectApi;
import org.example.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO Kolla igenom Bug-catch

public class UserForm extends JFrame {
    private JPanel mainPanel;
    private JPanel inputsPanel;
    private JPanel buttonsPanel;
    private JTextField usernameInputField;
    private JPasswordField passwordField;
    private JLabel outputLabel;
    private JButton createUserButton;
    private JButton loginButton;
    private JButton changePassButton;
    private JFrame jFrame;
    private ConnectApi connectApi;
    private User currentUser;
    private ChangePasswordForm changePasswordForm;
    public UserForm(ConnectApi connectApi) {
        this.connectApi = connectApi;
        jFrame = new JFrame();
        jFrame.setContentPane(mainPanel);
        jFrame.pack();
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameInputField.getText().isEmpty() || passwordField.getText().isEmpty())
                {
                    outputLabel.setText("Please provide all the information needed.");
                }
                else {
                    String name = usernameInputField.getText();
                    String password = new String(passwordField.getPassword());
                    outputLabel.setText(connectApi.loginUser(name, password));
                }
            }
        });
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameInputField.getText().isEmpty() || passwordField.getText().isEmpty())
                {
                    outputLabel.setText("Please provide all the information needed.");
                }
                else {
                    int balance = 20; // Set for easier testing.
                    String name = usernameInputField.getText();
                    String password = new String(passwordField.getPassword());
                    outputLabel.setText(connectApi.addUser(name, balance, password));
                }
            }
        });
        changePassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connectApi.IsUserLoggedIn()){
                    currentUser = connectApi.getUser();
                    changePasswordForm = new ChangePasswordForm(connectApi, currentUser);
                    changePasswordForm.showWindow();


                } else {
                    outputLabel.setText("No user is signed in, please sign in to continue.");
                }
            }
        });
    }
    public void showUserWindow() {
        jFrame.setVisible(true);
    }
}
