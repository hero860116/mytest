package test.threads.task_7_jdk_execute;

import test.threads.task_0_service.CheckCustomerAO;
import test.threads.task_6_pc_command.ITask;
import test.threads.task_6_pc_command.TaskConsumer;

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

        checkTaskAO.distributionCustomerTaskForJdk();
    }
}
