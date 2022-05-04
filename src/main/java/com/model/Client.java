
package com.model;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.runtime.ObjectMethods;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.j256.ormlite.dao.RawRowObjectMapper;
import org.json.JSONObject;


public class Client {

    private final int port = 7779;
    Socket clientSocket;

    private String userName;
    private String password;


    User user = new User("Aladin", "Hans", "Jürgen", "Hans-Jürgen@web.de", "12345", false);

    public Client() {
        try {

            this.clientSocket = new Socket("127.0.0.1", port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUser() {
        try {

            String userJsonString = new ObjectMapper().writeValueAsString(this.user);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            pw.println(userJsonString);
            pw.flush();

            Scanner input = new Scanner(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));
            String userString = input.next();

            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(userString, User.class);

            System.out.println("Json-Object from Server: " + user.toString());


            pw.println("[Client] Json-Object successfully received.");
            pw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printUserEmail () {
        System.out.println(user.geteMail());
    }


//            public void loginGetUserData(String userNameLabel.getText, String password) throws IOException {
//        JSONObject json = new JSONObject();
//        json.put("userName", userName);
//        json.put("password", password);
//
//        String jsonString = json.toString();
//        Socket socket = new Socket("localhost", 7999);
//    }
//}

    // Diese Methode kann erst ausgeführt werden, wenn GUI und LoginController loginPressed funktioniert
    // ich erstelle einen Dummy um zu teten ob die Funktionalität dieser Methode gegeben ist
    /*public void login(String userName, String password) throws IOException {
        JSONObject userLogin = new JSONObject();
        userLogin.put("userName", userName);
        userLogin.put("password", password);

        try {
            ObjectOutputStream loginObject = new ObjectOutputStream(clientSocket.getOutputStream());
            loginObject.writeObject(userLogin);
            loginObject.flush();
            loginObject.close();

            Scanner loginAnswer = new Scanner(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));
            System.out.println("[SERVER]: " + loginAnswer.nextLine());
            loginAnswer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void loginDummy() throws IOException {
       try {
            String userName = "Hans";
            String password = "Peter";
            JSONObject userLogin = new JSONObject();
            userLogin.put("userName", userName);
            userLogin.put("password", password);

            String userJsonString = userLogin.toString();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            pw.println(userJsonString);
            pw.flush();

            Scanner input = new Scanner(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));
            String userString = input.next();

            System.out.println("Json-Object from Server: " + userString);

            pw.println("[Client] Json-Object successfully received.");
            pw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws IOException {
        Client client = new Client();
        //client.sendUser();
        client.loginDummy();

    }

 /*  The following code contains a few methods to extract Data from the LOGIN GUI and create JSONObjects to give it to the client
     of course they have to be implemented in the LOGIN/GUI Class not in the Client, this is just preworked for the team
     hopefully some of these will work!! peace out, Rapha
     Will have to wait for the GUI code from Lorenz and Saiyan to see if it works...

     extracts the Strings from the LOGIN GUI and saves it in variables
     these variables need to be in GUI CLASS
     String userName;
     String password;

    public void extractLoginUserData() {
        String this.userName = userNameField.getText();
        String this.password = passwordField.getText();



    // creates a JSONObject with String username and String password we created above
    public void createUserJSON(String userName, String password) {
        JSONObject user = new JSONObject();
        user.put("userName", this.userName);
        user.put("password", this.password)

    }

    // extracts the Strings from the LOGIN GUI and creates a JSONObject with the values - 2in1

    public void extractLoginDataAndCreateUserJSON(String userNameField.getText(), String passwordField.getText()) {
        JSONObject user = new JSONObject();
        user.put("userName", userNameField.getText());
        user.put("password", passwordField.getText());
    }
}*/

}








//    public static void main(String[] args) {
//
//        User user1 = new User("Lodda123","Lothar","Matthaeus","lodda123@hotmail.com","Welmeister123",false);
//
//        Socket socket = null;
//        InputStreamReader inputStreamReader = null;
//        OutputStreamWriter outputStreamWriter = null;
//        BufferedReader bufferedReader = null;
//        BufferedWriter bufferedWriter = null;
//
//        try {
//            socket = new Socket("localhost", port);
//
//            inputStreamReader = new InputStreamReader(socket.getInputStream());
//            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//
//            bufferedReader = new BufferedReader(inputStreamReader);
//            bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//            Scanner scanner = new Scanner(System.in);
//
//            while (true) {
//
//                String msgToSend = scanner.nextLine();
//                bufferedWriter.write(user1.getUserName()+": " +msgToSend);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//
//                System.out.println("Server: " + bufferedReader.readLine());
//
//                if (msgToSend.equalsIgnoreCase("BYE"))
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(socket != null)
//                    socket.close();
//                if(inputStreamReader != null)
//                    inputStreamReader.close();
//                if(outputStreamWriter != null)
//                    outputStreamWriter.close();
//                if(bufferedReader != null)
//                    bufferedReader.close();
//                if(bufferedWriter != null)
//                    bufferedWriter.close();
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }




