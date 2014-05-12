package test.threads.task_5_pc_moretask;

import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:49
 */
public class Producer1 extends Thread{

    @Override
    public void run() {
        CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");


        ArrayBlockingQueue<List<CustomerDO>> myQueue = new ArrayBlockingQueue<List<CustomerDO>>(10);

        for (int i = 0; i < 10; i++) {
            new Task1Consumer(myQueue).start();
        }

        checkTaskAO.getBatchCustomers3(myQueue);
    }
}
