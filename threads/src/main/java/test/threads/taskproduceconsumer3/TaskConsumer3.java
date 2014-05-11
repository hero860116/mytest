package test.threads.taskproduceconsumer3;

import test.common.bean.CustomerDO;
import test.threads.producersconsumers.MyQueue;
import test.threads.task_single_thread.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class TaskConsumer3 extends Thread {

    private  ArrayBlockingQueue<List<CustomerDO>> myQueue;

    public TaskConsumer3( ArrayBlockingQueue<List<CustomerDO>> myQueue) {
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
