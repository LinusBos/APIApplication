package org.example.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton shoeWindowButton;
    private JButton userWindowButton;
    private JFrame jFrame;
    private ShoeForm shoeForm;
    private UserForm userForm;

    public MainForm(ShoeForm shoeForm, UserForm userForm) {
        this.shoeForm = shoeForm;
        this.userForm = userForm;
        jFrame = new JFrame();
        jFrame.setContentPane(mainPanel);
        jFrame.pack();
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shoeWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoeForm.showShoeWindow();
            }
        });
        userWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userForm.showUserWindow();
            }
        });
    }
    public void showMainWindow() {
        jFrame.setVisible(true);

    }
}
