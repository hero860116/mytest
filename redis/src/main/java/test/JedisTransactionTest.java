package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-4-22
 * Time: 下午2:52
 */
public class JedisTransactionTest {

    private Jedis jedis;



    @Before
    public void init() {
        jedis = new Jedis("10.200.190.13");

    }

    @Test
    public void testTransaction() {

        jedis.hset("userMap","name", "李卫林");
        jedis.hset("userMap","age", "28");

        Transaction tJedis = jedis.multi();

        Response<String> response = tJedis.hget("userMap", "name");

        tJedis.hset("userMap", "name", "李卫林1");
        tJedis.hset("userMap", "email", "xxx@hotmail.com");

        tJedis.exec();

        Map<String, String> userMap = jedis.hgetAll("userMap");
        System.out.println(userMap);
    }



    @After
    public void last() {



    }
}
