package test.threads.task_single_thread;

import test.threads.service.CheckCustomerAO;

/**
 * Created by lwl on 14-5-10.
 */
public class CheckTaskThread extends Thread {
    @Override
    public void run() {
        CheckCustomerAO checkTaskAO = (CheckCustomerAO)Main.applicationContext.getBean("checkCustomerAO");
        checkTaskAO.checkCustomer();
    }
}
