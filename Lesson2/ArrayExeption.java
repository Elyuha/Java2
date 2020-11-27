package Lesson2;

public class ArrayExeption extends Exception {
    public ArrayExeption() {
        super();
    }

    public ArrayExeption(String message) {
        super(message);
    }

    public ArrayExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
