package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-4-22
 * Time: 下午2:52
 */
public class JedisHashTest {

    private Jedis jedis;

    private Map<String, String> hashMap;


    @Before
    public void init() {
        jedis = new Jedis("10.200.190.13");

        jedis.del("userMap");

    }

    @Test
    public void testHsetExpire () {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "name", "李卫林1");
        jedis.hset("userMap", "age", "28");


        jedis.expire("userMap", 5);
        System.out.println(jedis.hgetAll("userMap"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(jedis.hgetAll("userMap"));

    }

    @Test
    public void testHset() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "name", "李卫林1");
        jedis.hset("userMap", "age", "28");


        hashMap = jedis.hgetAll("userMap");
    }

    @Test
    public void testHdel() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");

        jedis.hdel("userMap", "name");
        hashMap = jedis.hgetAll("userMap");
    }

    @Test
    public void testHincr() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");

        jedis.hincrBy("userMap", "age", 2);

        jedis.hincrByFloat("userMap", "age", 3.0);

        hashMap = jedis.hgetAll("userMap");
    }

    @Test
    public void testHexists() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");

        System.out.println(jedis.hexists("userMap", "name"));
        System.out.println(jedis.hexists("userMap", "email"));
    }

    @Test
    public void testHkeys() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");


        System.out.println( jedis.hkeys("userMap"));
    }

    @Test
    public void testHvals() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");


        System.out.println( jedis.hvals("userMap"));
    }

    @Test
    public void testHlen() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");


        System.out.println( jedis.hlen("userMap"));
    }

    @Test
    public void testHmget() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");
        jedis.hset("userMap", "email", "xxx@hotmail.com");

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("name", "李广");
        paramMap.put("age", "30");
        jedis.hmset("userMap", paramMap);


        System.out.println( jedis.hmget("userMap", "name", "email"));
    }

    @Test
    //增量遍历
    public void testHscan() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");
        jedis.hset("userMap", "email", "xxx@hotmail.com");

        ScanResult<Map.Entry<String, String>> result =  jedis.hscan("userMap", "0");

        System.out.println(result.getStringCursor());
        for (Map.Entry<String, String> stringStringEntry : result.getResult()) {
            System.out.println(stringStringEntry.getKey() + " : " + stringStringEntry.getValue());
        }

        jedis.hset("userMap", "name", "李卫林1");
        result =  jedis.hscan("userMap", result.getStringCursor());

        System.out.println("************************************");
        System.out.println(result.getStringCursor());
        for (Map.Entry<String, String> stringStringEntry : result.getResult()) {
            System.out.println(stringStringEntry.getKey() + " : " + stringStringEntry.getValue());
        }

    }

    @Test
    public void testHsetnx() {
        jedis.hset("userMap", "name", "李卫林");
        jedis.hset("userMap", "age", "28");
        jedis.hset("userMap", "email", "xxx@hotmail.com");

        jedis.hsetnx("userMap", "name", "李卫林111");
        jedis.hsetnx("userMap", "sex", "男");

        hashMap = jedis.hgetAll("userMap");
    }

    @After
    public void last() {

        if (hashMap != null) {
            System.out.println(hashMap);
        }

    }
}
