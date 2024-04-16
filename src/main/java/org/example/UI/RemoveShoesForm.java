package org.example.UI;

import org.example.ConnectApi;
import org.example.Shoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveShoesForm extends JFrame {
    private JPanel mainPanel;
    private JPanel textFieldPanel;
    private JPanel choicesPanel;
    private JTextArea shoeDataTextArea;
    private JTextField idInputField;
    private JLabel idLabel;
    private JButton deleteButton;
    private JLabel outputLabel;
    private JButton refreshButton;
    private JFrame jFrame;
    private ConnectApi connectApi;
    private ArrayList<Shoe> shoes;
    public RemoveShoesForm(ConnectApi connectApi, ArrayList<Shoe> shoes) {
        this.connectApi = connectApi;
        this.shoes = shoes;
        jFrame = new JFrame();
        jFrame.setContentPane(mainPanel);
        jFrame.pack();
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        refresh();

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idInputField.getText().isEmpty())
                {
                    outputLabel.setText("Please provide all the information needed.");
                } else {
                    try {
                        int lookupId = Integer.parseInt(idInputField.getText());
                        System.out.println(lookupId + "in removeshoesform.java");
                        if (connectApi.shoeExist(lookupId)) {
                            outputLabel.setText(connectApi.removeShoe(lookupId));

                        } else {
                            outputLabel.setText("No shoe with that id.");
                        }

                    } catch (NumberFormatException numberFormatException) {
                        outputLabel.setText("Must be a number.");
                    }
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
    }
    public void showWindow() {
        jFrame.setVisible(true);
    }
    private void refresh() {
        shoeDataTextArea.setText("");
        loadShoes();
        for (Shoe shoe: shoes) {
            shoeDataTextArea.append(shoe.getShoeId() + " " + shoe.getShoeName() + "\n");
        }
    }
    private void loadShoes() {
        shoes = connectApi.getAllShoes();
    }
}
