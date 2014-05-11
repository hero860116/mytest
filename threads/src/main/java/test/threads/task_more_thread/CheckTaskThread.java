package test.threads.task_more_thread;

import test.threads.task_single_thread.CheckCustomerAO;

/**
 * Created by lwl on 14-5-10.
 */
public class CheckTaskThread extends Thread {
    @Override
    public void run() {

        while (true) {
            //访问数据库

            CheckCustomerAO checkTaskAO = (CheckCustomerAO) Main.applicationContext.getBean("checkCustomerAO");

            checkTaskAO.checkCustomer();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
