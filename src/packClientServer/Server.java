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
public class Server {
    public static void main(String[] args) throws Exception {

        String sendToClient;
        BufferedReader outToClient = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket svSocket = new ServerSocket(12345);
        
        try (Socket commSocket = svSocket.accept()) {
            while(true) {
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
                System.out.print("Message from client: " + fromClient.readLine() + 
                        "\nYour response: ");
                sendToClient = outToClient.readLine() + '\n';
                DataOutputStream outgoingMsg = new DataOutputStream(commSocket.getOutputStream());
                outgoingMsg.writeBytes(sendToClient);
            }
        } catch (SocketException se) {
            System.out.println("---- Client disconnected! ----");
        }
    }
}
