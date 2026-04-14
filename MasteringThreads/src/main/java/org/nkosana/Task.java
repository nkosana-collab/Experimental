package org.nkosana;

public class Task implements Runnable {
    @Override
    public void run(){
        for(int i=1; i <= 5; i++){
            System.out.println(i);
            try {
                // Pause execution for 1000 milliseconds (1 second)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle the case where another thread interrupts the sleeping thread
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("Thread was interrupted");
            }

        }
    }
}
