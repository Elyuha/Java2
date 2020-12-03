package Lesson3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        firstQuest();
        System.out.println("\n\n");
        secondQuest();
    }

    private static void firstQuest() {
        List<UniqueString> arrayUniqueString = initUniqueList();
        for(int i = 0; i < arrayUniqueString.size() - 1; i++){
            for(int j = i + 1; j < arrayUniqueString.size(); j++){
                if(arrayUniqueString.get(i).getString().equals(arrayUniqueString.get(j).getString())){
                    arrayUniqueString.remove(j);
                    arrayUniqueString.get(i).incNumberOfDuplicates();
                    j--;
                }
            }
        }
        for(UniqueString s : arrayUniqueString){
            s.show();
        }
    }

    private static List initUniqueList() {
        List<UniqueString> arrayUniqueString= new ArrayList<>();
        arrayUniqueString.add(new UniqueString("zabiray"));
        arrayUniqueString.add(new UniqueString("menya"));
        arrayUniqueString.add(new UniqueString("skorey"));
        arrayUniqueString.add(new UniqueString("uvozi"));
        arrayUniqueString.add(new UniqueString("za"));
        arrayUniqueString.add(new UniqueString("100"));
        arrayUniqueString.add(new UniqueString("morey"));
        arrayUniqueString.add(new UniqueString("i"));
        arrayUniqueString.add(new UniqueString("tseluy"));
        arrayUniqueString.add(new UniqueString("menya"));
        arrayUniqueString.add(new UniqueString("vezde"));
        arrayUniqueString.add(new UniqueString("18"));
        arrayUniqueString.add(new UniqueString("mene"));
        arrayUniqueString.add(new UniqueString("ushe"));
        arrayUniqueString.add(new UniqueString("zabiray"));
        arrayUniqueString.add(new UniqueString("menya"));
        arrayUniqueString.add(new UniqueString("skorey"));
        arrayUniqueString.add(new UniqueString("uvozi"));
        arrayUniqueString.add(new UniqueString("za"));
        arrayUniqueString.add(new UniqueString("100"));
        arrayUniqueString.add(new UniqueString("morey"));
        arrayUniqueString.add(new UniqueString("i"));
        arrayUniqueString.add(new UniqueString("tseluy"));
        arrayUniqueString.add(new UniqueString("menya"));
        arrayUniqueString.add(new UniqueString("vezde"));
        arrayUniqueString.add(new UniqueString("ya"));
        arrayUniqueString.add(new UniqueString("ved"));
        arrayUniqueString.add(new UniqueString("vzroslaya"));
        arrayUniqueString.add(new UniqueString("ushe"));
        return arrayUniqueString;
    }

    private static void secondQuest() {
        Phonebook phonebook = initPhonebook();

        phonebook.showPhoneNumber("Biba");
        phonebook.showPhoneNumber("Boba");
        phonebook.showPhoneNumber("Spenser");
        phonebook.showPhoneNumber("Gay2");
    }

    private static Phonebook initPhonebook() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("79812228300", "Varisky");
        phonebook.add("7981412300", "Veste");
        phonebook.add("7988300", "Ledo");
        phonebook.add("78300", "Biba");
        phonebook.add("798122280300", "Boba");
        phonebook.add("79833333300", "Boba");
        phonebook.add("7123400", "Boba");
        phonebook.add("7981234238300", "Boba");
        phonebook.add("719812228300", "Biba");
        phonebook.add("791812228300", "Spenser");
        phonebook.add("798112228300", "Gay1");
        phonebook.add("7983122228300", "Gay1");
        phonebook.add("798122283100", "Gay2");
        phonebook.add("79812128300", "Gay2");
        return phonebook;
    }
}
