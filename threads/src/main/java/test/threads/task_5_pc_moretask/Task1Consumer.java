package test.threads.task_5_pc_moretask;

import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class Task1Consumer extends Thread {

    private  ArrayBlockingQueue<List<CustomerDO>> myQueue;

    public Task1Consumer(ArrayBlockingQueue<List<CustomerDO>> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {

            List<CustomerDO> customerDOs = null;
            try {
                customerDOs = myQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");

                checkTaskAO.handlerBatchCustomer3(customerDOs);
        }

    }
}
