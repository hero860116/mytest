package test.threads.task_single_thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

    public static void main(String[] args) {

        new CheckTaskThread().start();
    }
}
