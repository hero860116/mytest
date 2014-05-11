package test.threads.taskproduceconsumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.common.bean.CustomerDO;
import test.threads.producersconsumers.MyQueue;
import test.threads.task_more_thread.CheckTaskThread;
import test.threads.task_single_thread.CheckCustomerAO;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");

    public static void main(String[] args) {

        CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_more_thread.Main.applicationContext.getBean("checkCustomerAO");


        MyQueue<CustomerDO> myQueue = new MyQueue<CustomerDO>(50);
        for (int i = 0; i < 10; i++) {
            new TaskConsumer(myQueue).start();
        }

        checkTaskAO.getCustomers(myQueue);
    }
}
