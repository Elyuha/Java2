package Lesson3;

import java.util.*;

public class Phonebook {
    List<Record> phonebook;
    Phonebook(){
        phonebook = new ArrayList<>();
    }

    void add(String phoneNumber, String secondName){
        phonebook.add(new Record(phoneNumber, secondName));
    }

    void showPhoneNumber(String secondName){
        for(Record r : phonebook)
            if(secondName.equals(r.secondName))
                System.out.println("Second name: " + secondName +
                        " phone number: " + r.number);
    }
}
