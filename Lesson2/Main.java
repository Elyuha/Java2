package Lesson2;

import java.util.Scanner;

import static Lesson2.StringToInt.toInt;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = in.nextInt();
        String[][] array = new String[size][size];
        for(int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                System.out.print("Enter next string: ");
                array[i][j] = in.next();
            }
        int[][] arrayInt;
        try {
            arrayInt = toInt(array);
            for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++)
                    System.out.println(arrayInt);
        } catch (ArrayExeption arrayExeption) {
            arrayExeption.printStackTrace();
        }

    }
}
