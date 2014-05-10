package com.springapp.mvc;

/**
 * Created by lwl on 14-5-10.
 */
public class PrintTpsRunable implements Runnable {



    @Override
    public void run() {
        while (true) {
            System.out.println("#############tps:" + HelloController.count.get() + "###############");

            HelloController.count.set(0);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
