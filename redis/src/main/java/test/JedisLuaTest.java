package test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import test.dataobject.ObjectUtil;
import test.dataobject.UserDO;

import java.util.ArrayList;

/**
 * User: weilin.li
 * Date: 14-4-24
 * Time: 上午10:00
 */
public class JedisLuaTest {


    @Test
    public void testLuaOneLine() {
        Jedis jedis = new Jedis("10.200.190.13");
        jedis.del("nameList");

        for (int i = 0; i < 10; i++) {
            jedis.rpush("nameList", "name" + i);
        }


        //lua脚本，支持函数
        jedis.eval("return redis.call('lrem', 'nameList', 1, redis.call('lindex','nameList', 4))");

        System.out.println(jedis.lrange("nameList", 0, 100));
    }

    @Test
    public void testLuaMuiLine() {
        Jedis jedis = new Jedis("10.200.190.13");
        jedis.del("nameList");

        for (int i = 0; i < 10; i++) {
            jedis.rpush("nameList", "name" + i);
        }


        //lua脚本，多行；定义变量要使用local标识符
        jedis.eval("local ind = redis.call('lindex','nameList', 4);return redis.call('lrem', 'nameList', 1, ind)");

        System.out.println(jedis.lrange("nameList", 0, 100));
    }


}
