package Lesson4;

import java.util.Arrays;

public class ArrayClass {
    private final int size = 10000000;
    private final int h = size / 2;
    private float[] arr = new float[size];

    ArrayClass(){    }

    void program(){
        oneThread();
        twoThreads();
    }

    private void oneThread() {
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        for(int i = 0; i < size; i++){
            arr[i] = (float)(arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println("1 thread time: " + (end - start));
    }

    private void twoThreads() {
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        float a1[] = new float[h];
        float a2[] = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(() -> {
            calc(a1);
        });
        Thread t2 = new Thread(() -> {
            calc(a2);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long end = System.currentTimeMillis();
        System.out.println("2 threads time: " + (end - start));
    }

    private void calc(float[] a) {
        for(int i = 0; i < h; i++){
            a[i] = (float)(a[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }
}
