package org.nkosana;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Task task = new Task();
//        Thread worker = new Thread(task);
//        worker.start();
//        //Thread.sleep(1000);
//        System.out.println("Main thread is still running!");

        Task2 job = new Task2();
        Thread worker1 = new Thread(job);
        Thread worker2 = new Thread(job);

        worker1.start();
        worker2.start();

        Thread.sleep(1000);

        // The final count to be printed out.
        System.out.println(job.count);
    }
}