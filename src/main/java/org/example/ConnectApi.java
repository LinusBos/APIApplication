package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ConnectApi {
    public void getAllShoes() {
        try {
            URL url = new URL("http://localhost:8080/shoe/shoe");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                String message = "";
                String readMessage = reader.readLine();
                while (readMessage != null) {
                    message += readMessage;
                    readMessage = reader.readLine();
                }

                System.out.println(message);

                Gson gson = new Gson();
                ArrayList<Shoe> shoes = new ArrayList<>();

                Type listType = new TypeToken<ArrayList<Shoe>>() {}.getType();
                shoes = gson.fromJson(message, listType);


                for (Shoe shoe: shoes) {
                    System.out.println(shoe.toString());
                }

            } else {
                System.out.println("Error. Response code: " + responseCode + ".");
            }
        } catch (Exception e) {
            System.out.println("Connection failed.");
        }
    }
    public String addShoe(String name, int size, String brand, String gender, String style, String photo) {
        try {
            URL url = new URL("http://localhost:8080/shoe/shoe");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject shoeJson = new JSONObject();
            shoeJson.put("shoeName", name);
            shoeJson.put("shoeSize", size);
            shoeJson.put("shoeBrand", brand);
            shoeJson.put("shoeGender", gender);
            shoeJson.put("shoeStyle", style);
            shoeJson.put("shoePhoto", photo);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(shoeJson.toString());
            writer.flush();

            int code = connection.getResponseCode();


            if (code >= 200 && code <= 299) {
                return (code + " Shoe successfully added!");
            }
            else {
                return ("Error. Status code " + code + ".");
            }
        }
        catch (Exception e) {
            return ("Error. " + e);
        }
    }
    public String addUser(String name, int balance, String password) {
        try {
            String urlString = "http://localhost:8080/user/user?";
            urlString += "name=" + name + "&balance=" + balance + "&password=" + password;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.connect();

            int code = connection.getResponseCode();


            if (code >= 200 && code <= 299) {
                return (code + "User successfully added!");
            }
            else {
                return ("Error. Status code " + code + ".");
            }
        }
        catch (Exception e) {
            return ("Error. " + e);
        }
    }
}
