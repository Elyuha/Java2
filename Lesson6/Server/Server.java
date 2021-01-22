package Lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;

    Server(){
        start();
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(80);
            System.out.println("Server start!");
            System.out.println("Waiting connection!");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client info: " + clientSocket);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            new Thread(() -> {
                try {
                    while(true) {
                        String massage = in.readUTF();
                        if(massage.startsWith("-exit")){
                            System.out.println("BEY! Press enter to end!");
                            break;
                        }
                        System.out.println("Client message:\n" + new Date() + ": " + massage);
                    }

                } catch (EOFException e){
                    System.out.println("BEY! Press enter to end!");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
            Scanner in = new Scanner(System.in);
            while(true){
                String message = in.nextLine();
                if(clientSocket.isClosed()){
                    System.out.println("See next time!");
                    break;
                }
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }

    }

    private void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
