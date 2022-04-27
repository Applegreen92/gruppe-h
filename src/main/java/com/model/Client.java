
package com.model;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.RawRowObjectMapper;
/**  @author raphaelkruska
 *  Folgendes klappt jetzt mit diesem Code: Der Client sendet ne Message und wird als User mit dem Usernamen aus der Userklasse angezeigt.
 Wenn du "BYE" schreibst beendest du automatisch die Connection.
 Probier es mal aus und guck ob es bei dir auch funktioniert! Habe deinen Code mal auskommentiert, da ich beim Ausführen immer ne Exception bekommen habe.
 */


public class Client {

    private static final int port = 1231;

    public static void main(String[] args) {

        User user1 = new User("Lodda123","Lothar","Matthaeus","lodda123@hotmail.com","Welmeister123",false);

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", port);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {

                String msgToSend = scanner.nextLine();
                bufferedWriter.write(user1.getUserName()+": " +msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server: " + bufferedReader.readLine());

                if (msgToSend.equalsIgnoreCase("BYE"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null)
                    socket.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(outputStreamWriter != null)
                    outputStreamWriter.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(bufferedWriter != null)
                    bufferedWriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }


//    User user = new User("Aladin","Hans","Jürgen","Hans-Jürgen@web.de","12345",false);
//    Socket clientSocket;
//
//    public Client() {
//        try {
//
//            this.clientSocket= new Socket("127.0.0.1",4321);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void sendUser (){
//        try {
//
//            String userJsonString = new ObjectMapper().writeValueAsString(this.user.getUserName());
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
//            pw.write(userJsonString);
//            pw.flush();
//            pw.close();
//
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        }
    }




