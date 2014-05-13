package test.threads.task_7_jdk_execute;

import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午8:27
 */
public class CustomerTask implements Runnable {

    public CustomerTask(List<CustomerDO> customerDOs) {
        this.customerDOs = customerDOs;
    }

    private List<CustomerDO> customerDOs;

    @Override
    public void run() {
            CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");
            checkTaskAO.handlerCustomer(customerDOs);
    }
}
