package test.threads.task_4_pc_jdkqueue;

import test.threads.task_0_service.CheckCustomerAO;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:49
 */
public class Producer extends Thread{

    @Override
    public void run() {
        CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");


        checkTaskAO.distributionCustomerForJdk(Main.myQueue);
    }
}
