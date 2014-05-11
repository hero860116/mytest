package test.springdata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        NameAO nameAO = (NameAO)context.getBean("nameAO");

        System.out.println(nameAO.getName());

    }

}
