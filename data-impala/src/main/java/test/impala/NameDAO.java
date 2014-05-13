package test.impala;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
@Component
public class NameDAO extends BaseDAO{

    public void getUser() {
        Map map = sqlSessionTemplate.selectMap("impala.testshow", null);

        System.out.println(map);
    }

}
