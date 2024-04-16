package org.example.UI;

import org.example.ConnectApi;
import org.example.Shoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class ShoeForm extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel BottomPanel;
    private JTextField nameTextField;
    private JTextField sizeTextField;
    private JButton confirmButton;
    private JTextField styleTextField;
    private JLabel outputLabel;
    private JLabel titleLabel;
    private JTextField brandTextField;
    private JTextField genderTextField;
    private JButton removeShoeButton;
    private JFrame jFrame;

    public ShoeForm(ConnectApi connectApi) {
        jFrame = new JFrame();
        jFrame.setContentPane(mainPanel);
        jFrame.pack();
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        genderTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(genderTextField.getText().equals("Gender")) {
                    genderTextField.setText("");
                }
            }
        });

        nameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(nameTextField.getText().equals("Name")) {
                    nameTextField.setText("");
                }
            }
        });
        brandTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(brandTextField.getText().equals("Brand")) {
                    brandTextField.setText("");
                }
            }
        });
        sizeTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(sizeTextField.getText().equals("Size")) {
                    sizeTextField.setText("");
                }
            }
        });
        styleTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(styleTextField.getText().equals("Style")) {
                    styleTextField.setText("");
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputLabel.setText(""); // If been changed previously.
                if (sizeTextField.getText().equals("Size") || sizeTextField.getText().isEmpty() ||
                        genderTextField.getText().equals("Gender") || genderTextField.getText().isEmpty() ||
                        nameTextField.getText().equals("Name") || nameTextField.getText().isEmpty() ||
                        brandTextField.getText().equals("Brand") || brandTextField.getText().isEmpty() ||
                        styleTextField.getText().equals("Style") || styleTextField.getText().isEmpty())
                {
                    outputLabel.setText("Please provide all the information needed.");
                }
                else {
                    try {
                        int size = Integer.parseInt(sizeTextField.getText());
                        String name = nameTextField.getText();
                        String style = styleTextField.getText();
                        String brand = brandTextField.getText();
                        String gender = genderTextField.getText();
                        String photo = "photo"; // Declared for easier testing.
                        System.out.println("Name: " + name + " Style: " + style + " Brand: " + brand + " Gender: " + gender + " Size: " + size);
                        outputLabel.setText(connectApi.addShoe(name, size, brand, gender, style, photo));
                    }catch (NumberFormatException numberFormatException) {
                        outputLabel.setText("Size has to be numbers.");
                    }

                }

            }
        });
        removeShoeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Shoe> shoes = new ArrayList<>();
                shoes = connectApi.getAllShoes();
                RemoveShoesForm removeShoesForm = new RemoveShoesForm(connectApi, shoes);
                removeShoesForm.showWindow();

            }
        });
    }
    public void showShoeWindow() {
        confirmButton.requestFocusInWindow();
        jFrame.setVisible(true);

    }


}
