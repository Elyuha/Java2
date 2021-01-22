package Lesson8.Server;

import java.io.File;

public interface Chat {
    void broadcastMessage(String message);
    boolean isNicknameOccupied(String nickname);
    void subscribe(ClientHandler client);
    void unsubscribe(ClientHandler client);
//    AuthenticationService getAuthenticationService();
    ClientHandler searchClient(String name);
    String searchNickname(String log, String pas);
    void addUser(String log, String pass, String nickname);
    void updateUser(String log, String pass, String nickname);
    boolean checkNickname(String nickname);
    void addMessageToFile(String message);
    String showLastHundredMessage();
}

