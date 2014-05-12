package test.threads.task_4_pc_jdkqueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.common.bean.CustomerDO;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");

    public static void main(String[] args) {

        CheckCustomerAO checkTaskAO = (CheckCustomerAO) test.threads.task_2_more.Main.applicationContext.getBean("checkCustomerAO");


        ArrayBlockingQueue<List<CustomerDO>> myQueue = new ArrayBlockingQueue<List<CustomerDO>>(10);
        for (int i = 0; i < 10; i++) {
            new TaskConsumer3(myQueue).start();
        }

        checkTaskAO.getBatchCustomers3(myQueue);
    }
}
