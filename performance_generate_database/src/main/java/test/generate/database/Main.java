package test.generate.database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");

/*        GenarateDataDAO nameAO = (GenarateDataDAO)context.getBean("genarateDataDAO");

        nameAO.genarateData();*/

        GenarateItemDAO nameAO = (GenarateItemDAO)context.getBean("genarateItemDAO");

        nameAO.genarateData();

    }

}
