package test.threads.task_6_pc_command;

import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午7:06
 */
public class CustomerTask implements ITask {

    public CustomerTask(List<CustomerDO> customerDOs) {
        this.customerDOs = customerDOs;
    }

    private List<CustomerDO> customerDOs;

    @Override
    public void execute() {
        CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");
        checkTaskAO.handlerCustomer(customerDOs);
    }
}
