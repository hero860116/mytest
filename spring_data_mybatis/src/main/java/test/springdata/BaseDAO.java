package test.springdata;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午12:57
 */
public class BaseDAO {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
}
