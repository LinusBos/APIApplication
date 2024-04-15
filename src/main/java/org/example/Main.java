package org.example;

import org.example.UI.MainForm;
import org.example.UI.ShoeForm;
import org.example.UI.UserForm;

public class Main {
    public static void main(String[] args) {
        ConnectApi connectApi = new ConnectApi();

        ShoeForm shoeForm = new ShoeForm(connectApi);
        UserForm userForm = new UserForm(connectApi);
        MainForm mainForm = new MainForm(shoeForm, userForm);
        mainForm.showMainWindow();
    }
}