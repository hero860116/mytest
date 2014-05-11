package test.threads.taskproduceconsumer;

import test.common.bean.CustomerDO;
import test.threads.producersconsumers.MyQueue;
import test.threads.task_more_thread.*;
import test.threads.task_single_thread.*;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class TaskConsumer extends Thread {

    private MyQueue<CustomerDO> myQueue;

    public TaskConsumer(MyQueue<CustomerDO> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                CustomerDO customerDO = myQueue.pop();

                CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_more_thread.Main.applicationContext.getBean("checkCustomerAO");

                checkTaskAO.handlerCustomer(customerDO);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
