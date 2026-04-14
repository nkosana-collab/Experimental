package org.nkosana;

public class Task2 implements Runnable{

    static int count = 0;

    @Override
    public void run(){
        for(int i=1; i <= 1000; i++){
            increment();
        }
    }

    public synchronized void increment(){
        count += 1;
    }
}
