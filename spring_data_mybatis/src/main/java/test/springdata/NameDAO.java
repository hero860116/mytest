package test.springdata;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import test.common.bean.UserDO;
import test.common.entity.MapGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:03
 */
@Component
public class NameDAO extends BaseDAO{

    public UserDO getUser() {
        UserDO userDO = sqlSessionTemplate.selectOne("user.getById", 1);

        return userDO;
    }

    public List<UserDO> getUsers() {
        return sqlSessionTemplate.selectList("user.getByIds", ImmutableMap.of("ids", Arrays.asList(1,2)));
    }
}
