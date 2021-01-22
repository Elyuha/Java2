package Lesson8.App;

import Lesson8.Client.ClientChatAdapter;

public class ClientAppThree {
    public static void main(String[] args) {
        new ClientChatAdapter("localhost", 8888);
    }
}
