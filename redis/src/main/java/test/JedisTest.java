package test;

import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

/**
 * User: weilin.li
 * Date: 14-4-22
 * Time: 下午2:52
 */
public class JedisTest {

    @Test
    public void testPutObject() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("names");

        jedis.rpush("names", "lwl", "ygj") ;
        jedis.linsert("names", BinaryClient.LIST_POSITION.AFTER, "lwl", "liguang");

        //jedis.lrange("names", 0, 1);
        jedis.ltrim("names", 0 ,1);

        System.out.println(jedis.lrange("names", 0, 2));
    }
}
