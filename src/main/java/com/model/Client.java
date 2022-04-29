
package com.model;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.j256.ormlite.dao.RawRowObjectMapper;
import org.json.JSONObject;


public class Client {

    private final int port = 7779;


    User user = new User("Aladin", "Hans", "Jürgen", "Hans-Jürgen@web.de", "12345", false);
    Socket clientSocket;

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

            Scanner scanner = new Scanner(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));
            String answer = scanner.nextLine();
            System.out.println("Incoming answer from [server] : " + answer);
            pw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
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




