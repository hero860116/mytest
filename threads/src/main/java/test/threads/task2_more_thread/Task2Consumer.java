package test.threads.task2_more_thread;

import test.common.bean.CustomerDO;
import test.threads.service.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class Task2Consumer extends Thread {

    private  ArrayBlockingQueue<List<CustomerDO>> myQueue;

    public Task2Consumer(ArrayBlockingQueue<List<CustomerDO>> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {

                List<CustomerDO> customerDOs = myQueue.poll();

                CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_more_thread.Main.applicationContext.getBean("checkCustomerAO");

                checkTaskAO.handlerBatchCustomer3(customerDOs);
        }

    }
}
