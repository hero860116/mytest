package test.threads.task_3_pc;

import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class TaskConsumer extends Thread {

    @Override
    public void run() {


        while (true) {

            List<CustomerDO> customerDOs = null;
            try {
                customerDOs = Main.myQueue.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");

            checkTaskAO.handlerCustomer(customerDOs);
        }

    }
}
