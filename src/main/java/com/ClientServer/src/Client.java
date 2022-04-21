import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// A client sends messages to the server, the server spawns a thread to communicate with the client
// Each communication with a client is added to an array list so any message sent gets sent to every other client by looping through it

public class Client {

    // A client has a socket to connect to the server and a reader and writer to receive and send messages respectively
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // closes everything
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage() {
        try {
            // Initially send the username of the client
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            // Create a scanner for user input
            Scanner scanner = new Scanner(System.in);
            // While there is still a connection with the server, continue to scan the terminal and then send the message
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            // closes everything
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Listening for a message is blocking, so we need a separate thread for that
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                // While there is still a connection with the server, continue to listen for messages on a separate thread
                while (socket.isConnected()) {
                    try {
                        // Get the messages sent from other users and print it to the console
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        // closes everything
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    // Helper method to close everything so we don't have to repeat ourself
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // We only need to close the outer wrapper as the underlying streams are closed when we close the wrapper
        // We want to close the outermost wrapper so that everything gets flushed
        // closing a socket will also close the socket's InputStream and OutputStream
        // Closing the input stream closes the socket. We would need to use shutdownInput() on socket to just close the input stream
        // Closing the socket will also close the socket's input stream and output stream
        // Closing the socket after closing the streams
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runs the program
    public static void main(String[] args) throws IOException {

        // Gets a username for the user and a socket connection
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        // Creates a socket to connect to the server
        Socket socket = new Socket("localhost", 1234);

        // Pass the socket and give the client a username
        Client client = new Client(socket, username);
        // Infinite loop to read and send messages
        client.listenForMessage();
        client.sendMessage();
    }
}