package Lesson8.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String name;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private Chat chat;
    private static final Object lockFile = new Object();

    public ClientHandler(Socket socket, Chat chat) {
        this.socket = socket;
        this.chat = chat;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        if(!checkReg())
            doReg();
        doAuth();
        synchronized (lockFile) {
            sendMessage(chat.showLastHundredMessage());
        }
        receiveMessage();
    }

    private boolean checkReg() {
        sendMessage("Are you registered?\n 1 - yes, 2 - no");
        try {
            while(true) {
                String answer = in.readUTF();
                if(answer.equals("1"))
                    return true;
                if(answer.equals("2"))
                    return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void doReg() {
        sendMessage("Please, enter desired login, password and username. Sample [-reg login password nickname]");
        try {
            while (true){
                String mayBeCredentials = in.readUTF();
                if (mayBeCredentials.startsWith("-reg")){
                    String[] credentials = mayBeCredentials.split("\\s");
                    String loginAdnPassword = chat.searchNickname(credentials[1], credentials[2]);
                    /**
                     * if searchNickname return null
                     * it means that the combination
                     * of username and password is not used
                     */
                    if (loginAdnPassword == null){
                        if (!chat.checkNickname(credentials[3])){
                            sendMessage("[INFO] Reg OK");
                            chat.addUser(credentials[1], credentials[2], credentials[3]);
                            return;
                        }
                        else{
                            sendMessage("[INFO] User with this nickname already registered");
                        }
                    }
                    else{
                        sendMessage("[INFO] User with this login and password already registered");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void doAuth() {
        sendMessage("Please enter credentials. Sample [-auth login password]");
        try {
            /**
             * -auth login password
             * sample: -auth l1 p1
             */
            while (true) {
                String mayBeCredentials = in.readUTF();
                if (mayBeCredentials.startsWith("-auth")) {
                    String[] credentials = mayBeCredentials.split("\\s");
                    String mayBeNickname = chat.searchNickname(credentials[1], credentials[2]);
                    if (mayBeNickname != null) {
                        if (!chat.isNicknameOccupied(mayBeNickname)) {
                            sendMessage("[INFO] Auth OK");
                            name = mayBeNickname;

                            chat.broadcastMessage(String.format("[%s] logged in", name));
                            chat.subscribe(this);

                            return;
                        } else {
                            sendMessage("[INFO] Current user is already logged in.");
                        }
                    } else {
                        sendMessage("[INFO] Wrong login or password.");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-exit")) {
                    chat.unsubscribe(this);
                    chat.broadcastMessage(String.format("[%s] logged out", name));
                    break;
                }
                if(message.startsWith("-update")) {
                    String[] credentials = message.split("\\s");
                    if (!chat.checkNickname(credentials[3])) {
                        chat.broadcastMessage(String.format("[%s] change nickname to [%s]", name, credentials[3]));
                        chat.updateUser(credentials[1], credentials[2], credentials[3]);
                        name = credentials[3];
                    }
                    else{
                        sendMessage("[INFO] This nickname is already used.");
                    }
                }

                if (message.startsWith("-pm")) {
                    String[] credentials = message.split("\\s");
                    ClientHandler client = chat.searchClient(credentials[1]);
                    if(client != null) {
                        String newMessage = message.replaceAll(credentials[0] + " " + credentials[1], "");
                        client.out.writeUTF(String.format("Message from %s: %s", name, newMessage));
                        out.writeUTF("To " + client.getName() + ": " + newMessage);
                    }
                    else
                        out.writeUTF("Client doesn't exist.");
                }
                else if(message.startsWith("-update")) {
                    String[] credentials = message.split("\\s");
                    if (!chat.checkNickname(credentials[3])) {
                        chat.broadcastMessage(String.format("[%s] change nickname to [%s]", name, credentials[3]));
                        chat.updateUser(credentials[1], credentials[2], credentials[3]);
                        name = credentials[3];
                    }
                    else{
                        sendMessage("[INFO] This nickname is already used.");
                    }
                } else {
                    chat.broadcastMessage(String.format("[%s]: %s", name, message));
                    synchronized (lockFile) {
                        chat.addMessageToFile(String.format("[%s]: %s\n", name, message));
                    }
                }
            } catch (IOException e) {
                chat.unsubscribe(this);
                chat.broadcastMessage(String.format("[%s] logged out", name));
                throw new RuntimeException("SWW", e);
            }
        }
    }
}

