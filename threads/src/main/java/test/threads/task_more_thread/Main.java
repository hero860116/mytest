package test.threads.task_more_thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lwl on 14-5-10.
 */
public class Main {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new CheckTaskThread().start();
        }
    }
}
