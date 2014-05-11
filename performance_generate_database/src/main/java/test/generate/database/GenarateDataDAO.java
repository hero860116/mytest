package test.generate.database;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import test.common.bean.CustomerDO;
import test.common.bean.UserDO;

import java.awt.font.NumericShaper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 要编写一个工具，根据正则表达式生成随机字符串
 *
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
@Component
public class GenarateDataDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void genarateData() {


        long t = -System.currentTimeMillis();

        List<CustomerDO> customerDOs = new ArrayList<CustomerDO>();

        for (int i = 0; i < 1000000; i++) {
            CustomerDO customerDO = new CustomerDO();
            customerDO.setAge(Integer.parseInt(RandomStringUtils.random(2, false, true)));
            customerDO.setName(RandomStringUtils.random(3, "赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜"));
            customerDO.setEmail(RandomStringUtils.random(7, true, false) + "@hotmail.com");
            customerDO.setIntegral(Integer.parseInt(RandomStringUtils.random(4,false,true)));
            customerDOs.add(customerDO);
        }

        jdbcTemplate.batchUpdate("insert into t_customer(name,age, email, integral) values (?, ?, ?, ?)", customerDOs, 5000, new ParameterizedPreparedStatementSetter<CustomerDO>() {
            @Override
            public void setValues(PreparedStatement ps, CustomerDO argument) throws SQLException {
                ps.setString(1, argument.getName());
                ps.setInt(2, argument.getAge());
                ps.setString(3, argument.getEmail());
                ps.setInt(4, argument.getIntegral());
            }
        });

        t += System.currentTimeMillis();

        System.out.println("times:" + t);

        //return jdbcTemplate.queryForObject("select name from t_user where id = 1", String.class);
    }
}
