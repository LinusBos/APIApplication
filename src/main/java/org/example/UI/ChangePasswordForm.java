package org.example.UI;

import org.example.ConnectApi;
import org.example.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordForm extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel outputLabel;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JLabel passwordLabel;
    private JFrame jFrame;
    private ConnectApi connectApi;
    private User user;

    public ChangePasswordForm(ConnectApi connectApi, User user) {
        this.connectApi = connectApi;
        this.user = user;
        jFrame = new JFrame();
        jFrame.setContentPane(mainPanel);
        jFrame.pack();
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String(passwordField.getPassword()).isEmpty()) {
                    outputLabel.setText("Please provide a new password.");
                } else {
                    user.setUserPassword(new String(passwordField.getPassword()));
                    outputLabel.setText(connectApi.updateUser(user));
                }
            }
        });
    }
    public void showWindow() {
        jFrame.setVisible(true);
    }
}
