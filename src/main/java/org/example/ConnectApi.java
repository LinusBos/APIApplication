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
    private User user;
    private boolean userLoggedIn = false;
    public ArrayList<Shoe> getAllShoes() {
        try {
            URL url = new URL("http://localhost:8080/shoe/allshoes");
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
                System.out.println(shoes instanceof ArrayList<Shoe>);
                return shoes;
            } else {
                System.out.println("Error. Response code: " + responseCode + ".");
            }
        } catch (Exception e) {
            System.out.println("Connection failed.");
        }
        return new ArrayList<Shoe>();
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
                return (code + " User successfully added!");
            }
            else {
                return ("Error. Status code " + code + ".");
            }
        }
        catch (Exception e) {
            return ("Error. " + e);
        }
    }
    public String loginUser(String userName, String userPassword) {
        try {
            URL url = new URL("http://localhost:8080/user/allusers");
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


                Gson gson = new Gson();
                ArrayList<User> users = new ArrayList<>();

                Type listType = new TypeToken<ArrayList<User>>() {}.getType();
                users = gson.fromJson(message, listType);


                for (User user: users) {
                    if(user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword)) {
                        this.user = user;
                        userLoggedIn = true;
                        return responseCode + " login successful!";
                    }
                }

            } else {
                return "Error. Response code: " + responseCode + ".";
            }
        } catch (Exception e) {
            return "Connection failed.";
        }
        return "Wrong password!";
    }
    public String updateUser(User user){
        try {
            int id = user.getUserId();
            String name = user.getUserName();
            int balance = user.getUserBalance();
            String password = user.getUserPassword();

            String urlString = "http://localhost:8080/user/user?";
            urlString += "id=" + id + "&name=" + name + "&balance=" + balance + "&password=" + password;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {
                return responseCode + " update successful!";
            } else {
                return "Error. Response code: " + responseCode + ".";
            }
        } catch (Exception e) {
            return "Connection failed.";
        }
    }
    public boolean shoeExist(int id) {
        try {
            URL url = new URL("http://localhost:8080/shoe/shoe?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {
                return true;
            } else {
                System.out.println("Error. Response code: " + responseCode + ".");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Connection failed.");
            return false;
        }

    }
    public String removeShoe(int id) {
        try {
            URL url = new URL("http://localhost:8080/shoe/shoe?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {
                return responseCode + " Shoe has been removed.";
            } else {
                return responseCode + " no shoe has been removed.";
            }
        } catch (Exception e) {
            return "Connection failed.";
        }

    }
    public User getUser() {
        return user;
    }
    public boolean IsUserLoggedIn() {
        return userLoggedIn;
    }
}
