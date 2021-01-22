package Lesson6.Client;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    Client() {
        start();
    }

    private void start(){
        try {
            System.out.println("Client start");
            socket = new Socket("127.0.0.1", 80);
            System.out.println("Server info: " + socket);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while(true) {
                        String massage = in.readUTF();
                        if(massage.startsWith("-exit")){
                            System.out.println("BEY! Press enter to end!");
                            break;
                        }
                        System.out.println("Server message:\n" + new Date() + ": " + massage);
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
                if(socket.isClosed()){
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
