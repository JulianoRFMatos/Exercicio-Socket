/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packClientServer;

import java.net.*;
import java.io.*;

/**
 *
 * @author Juliano
 */
public class Client {
    public static void main(String[] args) throws Exception {

        String sendToServer;
        BufferedReader outToServer = new BufferedReader(new InputStreamReader(System.in));

        try (Socket clientSocket = new Socket("localhost", 12345)) {
            while(true) {
                System.out.print("Your message (say bye to close conn.): ");
                sendToServer = outToServer.readLine() + '\n';
                DataOutputStream outgoingMsg = new DataOutputStream(clientSocket.getOutputStream());
                outgoingMsg.writeBytes(sendToServer);
                if(sendToServer.contains("bye")) {
                    clientSocket.close();
                }
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Server said: " + fromServer.readLine());
            }
        } catch (SocketException se) {
            System.out.println("----- Disconnected! -----");
        }
    }
}
