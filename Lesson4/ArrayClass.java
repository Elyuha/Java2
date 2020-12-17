package Lesson4;

import java.util.Arrays;

public class OriginArrayClass {
    private final int size = 10000000;
    private final int h = size / 2;
    private float[] arr = new float[size];

    OriginArrayClass(){
        Arrays.fill(arr, 1);
    }

    void program(){
        oneThread();
        twoThreads();
    }

    private void oneThread() {
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
        long start = System.currentTimeMillis();
        float a1[] = new float[h];
        float a2[] = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

                
        long end = System.currentTimeMillis();
        System.out.println("2 threads time: " + (end- start));
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
