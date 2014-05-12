package test.generate.database;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import test.common.bean.ItemDO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 要编写一个工具，根据正则表达式生成随机字符串
 *
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
@Component
public class GenarateItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void genarateData() {


        long t = -System.currentTimeMillis();

        List<ItemDO> itemDOs = new ArrayList<ItemDO>();

        for (int i = 0; i < 1000000; i++) {
            ItemDO itemDO = new ItemDO();
            itemDO.setTitle(RandomStringUtils.random(10, "赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜"));
            itemDO.setItemUrl(RandomStringUtils.random(50, true, false));
            itemDO.setPrice(Double.parseDouble(RandomStringUtils.random(3, false, true)));
            itemDOs.add(itemDO);
        }


        long s = -System.currentTimeMillis();
        jdbcTemplate.batchUpdate("insert into t_item(title,item_url, price) values (?, ?, ?)", itemDOs, 5000,
                new ParameterizedPreparedStatementSetter<ItemDO>() {
            @Override
            public void setValues(PreparedStatement ps, ItemDO argument) throws SQLException {
                ps.setString(1, argument.getTitle());
                ps.setString(2, argument.getItemUrl());
                ps.setDouble(3, argument.getPrice());
            }
        });

        t += System.currentTimeMillis();
        s += System.currentTimeMillis();

        System.out.println("times:" + t + ",s:"+s);

        //return jdbcTemplate.queryForObject("select name from t_user where id = 1", String.class);
    }
}
