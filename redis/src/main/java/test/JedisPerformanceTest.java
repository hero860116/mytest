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
public class JedisPerformanceTest {


    @Test
    //2m7s
    public void testBatchAdd() {
        Jedis jedis = new Jedis("10.200.190.13");

        for (int i = 0; i < 100000; i++) {
            ArrayList<byte[]> userDOList = new ArrayList<byte[]>();
            String dpId = (10000 + i) + "";
            for (int j = 0; j < 5; j++) {

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
    }

    @Test
    //32s
    public void testPipilineBatchAdd() {
        Jedis jedis = new Jedis("10.200.190.13");
        Pipeline pipeline = jedis.pipelined();

        for (int i = 0; i < 100000; i++) {
            ArrayList<byte[]> userDOList = new ArrayList<byte[]>();
            String dpId = (10000 + i) + "";
            for (int j = 0; j < 5; j++) {

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

            pipeline.lpush(dpId.getBytes(), userDOList.toArray(new byte[][]{}));
        }

        pipeline.sync();
        //pipeline.syncAndReturnAll();
    }

    @Test

    //--34s
    public void testPipilineAdd() {
        Jedis jedis = new Jedis("10.200.190.13");
        Pipeline pipeline = jedis.pipelined();

        for (int i = 0; i < 100000; i++) {
            String dpId = (10000 + i) + "";
            for (int j = 0; j < 5; j++) {

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

                pipeline.lpush(dpId.getBytes(), ObjectUtil.objectToByte(userDO));
            }

        }
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
}
