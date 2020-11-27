package Lesson2;

import com.sun.deploy.util.StringUtils;

public class StringToInt {
    
    public static int[][] toInt(String[][] arrayString) throws ArrayExeption{
        try {
            doCheckSize(arrayString);
            int[][] arrayInt;
            arrayInt = doCheckData(arrayString);
            return arrayInt;
        }
        catch (MyArrayDataException | MyArraySizeException e){
            throw new ArrayExeption("Something wrong!", e);
        }
    }

    private static void doCheckSize(String[][] arrayString) throws MyArraySizeException{
        if(arrayString.length != 4)
            throw new MyArraySizeException("Array size != 4.");
    }

    private static int[][] doCheckData(String[][] arrayString) throws MyArrayDataException {
        int[][] arrayInt = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(arrayString[i][j].matches("-?\\d+"))
                    arrayInt[i][j] = Integer.parseInt(arrayString[i][j]);
                else
                    throw new MyArrayDataException("String isn't a number.");
            }
        }
        return arrayInt;
    }
}
