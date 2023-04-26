package org.example.Client;
import java.io.*;
import java.net.*;

public class Client {
    static AuthRequest authenticationRequest;
    static StdRequest runner;
    static ObjectInputStream fromServer;
    static ObjectOutputStream toServer;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        authenticationRequest = new AuthRequest();
        try {
            authenticationRequest.run(fromServer, toServer);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        int studentId = authenticationRequest.getId();
        runner = new StdRequest(studentId);
        try {
            runner.run(fromServer, toServer);
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

}
