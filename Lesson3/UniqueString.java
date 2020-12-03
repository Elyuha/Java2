package Lesson3;

public class UniqueString {
    String string;
    int numberOfDuplicates;
    UniqueString(String string){
        this.string = string;
        this.numberOfDuplicates = 1;
    }

    String getString(){
        return string;
    }

    void incNumberOfDuplicates(){
        ++numberOfDuplicates;
    }

    void show(){
        System.out.println("String: " + string + " repeated "
                + numberOfDuplicates + " times");
    }
}