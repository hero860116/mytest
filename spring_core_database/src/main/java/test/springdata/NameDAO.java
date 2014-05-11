package test.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
@Component
public class NameDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getName() {

        return jdbcTemplate.queryForObject("select name from t_user where id = 1", String.class);
    }
}
