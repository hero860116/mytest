package test;

import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

/**
 * User: weilin.li
 * Date: 14-4-22
 * Time: 下午2:52
 */
public class JedisStringTest {

    @Test
    public void testAppend() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.append("text", "123");

        System.out.println(jedis.get("text"));

        jedis.append("text", "abc");
        System.out.println(jedis.get("text"));
    }

    @Test
    public void testBitcount() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("text");

        jedis.append("text", "123");

        System.out.println(jedis.bitcount("text"));

        jedis.append("text", "456");
        System.out.println(jedis.bitcount("text"));

        jedis.append("text", "789");
        System.out.println(jedis.bitcount("text"));

        jedis.append("text", "李卫林");
        System.out.println(jedis.bitcount("text"));

        jedis.append("text", "012");
        System.out.println(jedis.bitcount("text"));
    }

    @Test
    //TODO
    public void testBitop() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("text");

        jedis.append("text1", "123");
        jedis.append("text2", "abc");
        jedis.append("text3", "李卫林");

        System.out.println(jedis.bitcount("text1"));
        System.out.println(jedis.bitcount("text2"));
        System.out.println(jedis.bitcount("text3"));

        System.out.println(jedis.bitop(BitOP.OR, "text1", "text2", "text3"));
    }

    @Test
    public void testDecr() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("num");

        jedis.set("num", "11");
        jedis.decr("num") ;

        jedis.decrBy("num", 11);


        System.out.println(jedis.get("num"));
    }

    @Test
    public void testIncr() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("num");

        jedis.set("num", "11");

        jedis.incr("num") ;
        System.out.println(jedis.get("num"));

        jedis.incrBy("num", 11);
        System.out.println(jedis.get("num"));

        jedis.incrByFloat("num", 10.0);
        System.out.println(jedis.get("num"));
    }

    @Test
    //todo
    public void testGetBit() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("text");

        jedis.set("text", "0李lwl");

        System.out.println(jedis.bitcount("text"));

        System.out.println(jedis.getbit("text", 1));
    }

    @Test
    public void testGetSet() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("text");

        jedis.set("text", "0李lwl");

        System.out.println(jedis.getSet("text", "getSet after"));

        System.out.println(jedis.get("text"));
    }

    @Test
    public void testMget() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");
        jedis.del("name2");

        jedis.set("name1", "李卫林");
        jedis.set("name2", "李广");

        System.out.println(jedis.mget("name1", "name2"));
    }

    @Test
    public void testMset() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");
        jedis.del("name2");

        jedis.mset("name1", "李卫林", "name2", "李广");

        jedis.msetnx("name1", "李卫林1", "name2", "李广2", "name3", "test3");

        System.out.println(jedis.mget("name1", "name2"));
    }

    @Test
    public void testSetEX() throws InterruptedException {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");
        jedis.del("name2");

        jedis.setex("name2", 7, "test2");
        jedis.psetex("name1", 5000, "test1");

        System.out.println(jedis.get("name1"));
        System.out.println(jedis.get("name2"));

        Thread.sleep(6000);

        System.out.println("***************************");
        System.out.println(jedis.get("name1"));
        System.out.println(jedis.get("name2"));

        Thread.sleep(2000);
        System.out.println("***************************");
        System.out.println(jedis.get("name1"));
        System.out.println(jedis.get("name2"));
    }

    @Test
    public void testSetNX() throws InterruptedException {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");
        jedis.del("name2");

        jedis.set("name1",  "test1");

        jedis.setnx("name2",  "test2...");
        jedis.setnx("name1",  "test1...");


        System.out.println(jedis.get("name1"));
        System.out.println(jedis.get("name2"));
    }

    @Test
    public void testSetRange() throws InterruptedException {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");

        jedis.set("name1",  "test1");

        jedis.setrange("name1", 3, "rrrrrrrrrrr");

        System.out.println(jedis.get("name1"));
    }

    @Test
    public void testStrlen() throws InterruptedException {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("name1");

        jedis.set("name1",  "test1");

        System.out.println(jedis.strlen("name1"));
    }
}
