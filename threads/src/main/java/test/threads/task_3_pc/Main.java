package test.threads.task_3_pc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.common.bean.CustomerDO;
import test.threads.producersconsumers.MyQueue;
import test.threads.task_0_service.CheckCustomerAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");

    public static MyQueue<List<CustomerDO>> myQueue = new MyQueue<List<CustomerDO>>(10);


    public static void main(String[] args) {


        MyQueue<CustomerDO> myQueue = new MyQueue<CustomerDO>(50);
        for (int i = 0; i < 10; i++) {
            new TaskConsumer().start();
        }

        new Producer().start();

    }
}
