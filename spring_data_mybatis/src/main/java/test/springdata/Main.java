package test.springdata;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");

        NameDAO nameAO = (NameDAO)context.getBean("nameDAO");

        System.out.println(nameAO.getUsers());

    }

}
