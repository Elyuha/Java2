package Lesson8.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ServerChat implements Chat {
    private ServerSocket serverSocket;
    private Set<ClientHandler> clients;
    private File file;
//    private AuthenticationService authenticationService;

    public ServerChat() {
        file = new File("C:\\Users\\Евкадий\\IdeaProjects\\Lesson\\Lesson8\\Server\\MessageHistory.txt");
        DBConnect.init();
        start();
    }

//    @Override
//    public AuthenticationService getAuthenticationService() {
//        return authenticationService;
//    }

    private void start() {
        try {
            serverSocket = new ServerSocket(8888);
            clients = new HashSet<>();
//            authenticationService = new AuthenticationService();

            while (true) {
                System.out.println("Server is waiting for a connection ...");
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                System.out.println(String.format("[%s] Client[%s] is successfully logged in", new Date(), clientHandler.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    @Override
    public synchronized boolean isNicknameOccupied(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    @Override
    public synchronized ClientHandler searchClient(String name) {
        for(ClientHandler client : clients)
            if(client.getName().equals(name))
                return client;
        return null;
    }

    @Override
    public String searchNickname(String log, String pas) {
        return DBConnect.searchNickname(log, pas);
    }

    @Override
    public void addUser(String log, String pass, String nickname) {
        DBConnect.add(log, pass, nickname);
    }

    @Override
    public void updateUser(String log, String pass, String nickname) {
        DBConnect.update(log, pass, nickname);
    }

    @Override
    public boolean checkNickname(String nickname) {
        for(ClientHandler client : clients)
            if(nickname.equals(client.getName()))
                return true;
        return false;
    }

    @Override
    public void addMessageToFile(String message) {
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String showLastHundredMessage() {
        RandomAccessFile fileHandler = null;
        int lines = 200;
        try {
            fileHandler =
                    new java.io.RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();
            int line = 0;

            for(long filePointer = fileLength; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();

                if( readByte == 0xA ) {
                    if (filePointer < fileLength) {
                        line = line + 1;
                    }
                } else if( readByte == 0xD ) {
                    if (filePointer < fileLength-1) {
                        line = line + 1;
                    }
                }
                if (line >= lines) {
                    break;
                }
                sb.append( ( char ) readByte );
            }

            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (fileHandler != null )
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

