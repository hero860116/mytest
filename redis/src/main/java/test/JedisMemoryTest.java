package test;

import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import test.dataobject.ObjectUtil;
import test.dataobject.UserDO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: weilin.li
 * Date: 14-4-22
 * Time: 下午2:52
 */
public class JedisMemoryTest {

    @Test
    public void testBatchAddObjectHash() {
        Jedis jedis = new Jedis("10.200.190.13");

        for (int i = 0; i < 5000; i++) {
            ArrayList<UserDO> userDOList = new ArrayList<UserDO>();
            String dpId = (10000 + i) + "";
            for (int j = 0; j < 100; j++) {

                int cr = i * 100 + j;

                UserDO userDO = new UserDO();
                userDO.setName("李卫林" + cr);
                userDO.setDpId(dpId);
                userDO.setActivityId("10000001" + cr);
                userDO.setCondition("1" + cr);
                userDO.setCoverURL("http://gi1.md.alicdn.com/bao/uploaded/i1/17138030376818300/T1zOMVFgVbXXXXXXXX_!!0-item_pic.jpg_460x460q90.jpg");
                userDO.setDenominations("2000001" + cr);
                userDO.setDiscountRate("1000");
                userDO.setMoney("100");
                userDO.setNumLimit("10");
                userDO.setPlatId("1");
                userDO.setType(111);

                userDOList.add(userDO);
            }

            jedis.hset("dpawards".getBytes(), dpId.getBytes(), ObjectUtil.objectToByte(userDOList));
        }

        byte[] bytes = jedis.hget("dpawards".getBytes(), "10000".getBytes());
        ArrayList<UserDO> userDOList = (ArrayList)ObjectUtil.byteToObject(bytes);

        //System.out.println(userDOList);
    }

    @Test
    public void testBatchAddList() {
        Jedis jedis = new Jedis("10.200.190.13");

        for (int i = 0; i < 5000; i++) {
            ArrayList<byte[]> userDOList = new ArrayList<byte[]>();
            String dpId = (10000 + i) + "";
            for (int j = 0; j < 100; j++) {

                int cr = i * 100 + j;

                UserDO userDO = new UserDO();
                userDO.setName("李卫林" + cr);
                userDO.setDpId(dpId);
                userDO.setActivityId("10000001" + cr);
                userDO.setCondition("1" + cr);
                userDO.setCoverURL("http://gi1.md.alicdn.com/bao/uploaded/i1/17138030376818300/T1zOMVFgVbXXXXXXXX_!!0-item_pic.jpg_460x460q90.jpg");
                userDO.setDenominations("2000001" + cr);
                userDO.setDiscountRate("1000");
                userDO.setMoney("100");
                userDO.setNumLimit("10");
                userDO.setPlatId("1");
                userDO.setType(111);

                userDOList.add(ObjectUtil.objectToByte(userDO));
            }

            jedis.lpush(dpId.getBytes(), userDOList.toArray(new byte[][]{}));

            //jedis.hset("dpawards".getBytes(), dpId.getBytes(), ObjectUtil.objectToByte(userDOList));
        }


        byte[] bytes = jedis.lindex("10000".getBytes(), 0);
        UserDO userDO = (UserDO)ObjectUtil.byteToObject(bytes);
        long len = jedis.llen("10000");

        jedis.lrem("10000".getBytes(), 1, bytes);

        long len2 = jedis.llen("10000");

        System.out.println("len:" + len + ", userDO:" + userDO.getName() + ",len2:" + len2);
    } 
    
    @Test
    public void testRemList() {
        Jedis jedis = new Jedis("10.200.190.13");


        byte[] bytes = jedis.lindex("10000".getBytes(), 0);
        UserDO userDO = (UserDO)ObjectUtil.byteToObject(bytes);
        long len = jedis.llen("10000");

        jedis.lrem("10000".getBytes(), 1, bytes);

        long len2 = jedis.llen("10000");

        System.out.println("len:" + len + ", userDO:" +userDO.getName() + ",len2:" + len2);
    }

    @Test
    public void clear() {
        Jedis jedis = new Jedis("10.200.190.13");

        jedis.del("dpawards");
    }
}
