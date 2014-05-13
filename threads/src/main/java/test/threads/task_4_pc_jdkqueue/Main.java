package test.threads.task_4_pc_jdkqueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.common.bean.CustomerDO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");


   public static ArrayBlockingQueue<List<CustomerDO>> myQueue = new ArrayBlockingQueue<List<CustomerDO>>(10);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new TaskConsumer3(myQueue).start();
        }

        new Producer().start();
    }
}
