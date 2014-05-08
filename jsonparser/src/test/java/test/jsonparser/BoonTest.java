package test.jsonparser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.boon.json.JsonParser;
import org.boon.json.JsonParserFactory;
import org.boon.json.ObjectMapper;
import org.boon.json.implementation.JsonFastParser;
import org.boon.json.implementation.ObjectMapperImpl;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.util.ObjectUtils;
import test.common.bean.UserDO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-5-8
 * Time: 上午11:37
 */
public class BoonTest {

    public void testBoon() throws IOException {
        UserDO userDO = new UserDO();
        userDO.setName("liweilin");
        userDO.setAge(28);
        userDO.setEmail("xxx@hotmail.com");

        ObjectMapper mapper = new ObjectMapperImpl();
        String str = mapper.writeValueAsString(userDO);

        org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();

        System.out.println(str);

        String userJson = "{\"name\":\"liweilin\",\"age\":28,\"email\":\"xxx@hotmail.com\"}";

        long s = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserDO user1 = (UserDO)mapper.fromJson(userJson, UserDO.class);
        }
        s += System.currentTimeMillis();
        System.out.println(s);

        s = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserDO user2 = objectMapper.readValue(userJson, UserDO.class);
        }
        s += System.currentTimeMillis();
        System.out.println(s);


        s = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserDO user2 = (UserDO)JSON.parse(userJson);
        }
        s += System.currentTimeMillis();
        System.out.println(s);



    }

    @Test
    public void testPerformanceJackson() throws IOException {
        org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
        ObjectMapper mapper = new ObjectMapperImpl();

        List<String> jsonStrList = new ArrayList<String>();
        String email = "xxx@hotmail.com";
        String name = "liweilin";
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            jsonStrList.add(mapper.toJson(userDO));
        }

        long times = -System.currentTimeMillis();
        List<UserDO> userDOs = new ArrayList<UserDO>();
        for (String s : jsonStrList) {
            UserDO userDO1 = objectMapper.readValue(s, UserDO.class);
            userDOs.add(userDO1);
        }

        times += System.currentTimeMillis();
        System.out.println(times);


    }

    @Test
    public void testPerformanceBoonJson() {
        ObjectMapper mapper = new ObjectMapperImpl();
        JsonParser parser = new JsonParserFactory().create ();

        List<String> jsonStrList = new ArrayList<String>();
        String email = "xxx@hotmail.com";
        String name = "liweilin";
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            jsonStrList.add(mapper.toJson(userDO));
        }

        JsonFastParser jsonFastParser = new JsonFastParser();
        long times = -System.currentTimeMillis();
        List<UserDO> userDOs = new ArrayList<UserDO>();
        for (String s : jsonStrList) {
            //Object obj = parser.parse(s);
            UserDO userDO1 = mapper.readValue(s, UserDO.class);
            userDOs.add(userDO1);
        }

        times += System.currentTimeMillis();
        System.out.println(times);
    }

    @Test
    public void testPerformanceBoonJson1() {
        ObjectMapper mapper = new ObjectMapperImpl();

        long times = 0;

        String email = "xxx@hotmail.com";
        String name = "liweilin";
        for (int i = 0; i < 1; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);
            String str = mapper.writeValueAsString(userDO);

            long s = -System.currentTimeMillis();

            mapper.readValue(str, UserDO.class);

            s += System.currentTimeMillis();

            times += s;

        }

        System.out.println(times);
    }

    @Test
    public void testPerformanceFastJson() {
        List<String> jsonStrList = new ArrayList<String>();
        String email = "xxx@hotmail.com";
        String name = "liweilin";
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            jsonStrList.add(JSON.toJSONString(userDO, SerializerFeature.WriteClassName));
        }

        long times = -System.currentTimeMillis();
        List<UserDO> userDOs = new ArrayList<UserDO>();
        for (String s : jsonStrList) {
            //JSON.parse()
            UserDO userDO1 = (UserDO) JSON.parse(s);
            userDOs.add(userDO1);
        }
        times += System.currentTimeMillis();
        System.out.println(times);
    }

    @Test
    public void testPerformanceFastJson1() {
        String email = "xxx@hotmail.com";
        String name = "liweilin";
        long times = 0;
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            byte[] str = JSON.toJSONBytes(userDO, SerializerFeature.WriteClassName);


            long s = -System.currentTimeMillis();

            UserDO userDO1 = (UserDO)JSON.parse(str);

            s += System.currentTimeMillis();

            times += s;
        }

        System.out.println(times);
    }

    @Test
    public void testPerformanceFastString() {
        String email = "xxx@hotmail.com";
        String name = "liweilin";

        String str = JSON.toJSONString(name);
        String str1 = (String)JSON.parse(name);

    }

    @Test
    public void testPerformanceSeraible() {
        Converter<Object, byte[]> serializer = new SerializingConverter();
        Converter<byte[], Object> deserializer = new DeserializingConverter();

        List<byte[]> jsonStrList = new ArrayList<byte[]>();
        String email = "xxx@hotmail.com";
        String name = "liweilin";
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            jsonStrList.add(serializer.convert(userDO));

        }

        long times = -System.currentTimeMillis();
        List<UserDO> userDOs = new ArrayList<UserDO>();
        for (byte[] s : jsonStrList) {
            //JSON.parse()
            UserDO userDO1 = (UserDO) deserializer.convert(s);
            userDOs.add(userDO1);
        }
        times += System.currentTimeMillis();
        System.out.println(times);
    }

    @Test
    public void testPerformanceSeraible1() {
        Converter<Object, byte[]> serializer = new SerializingConverter();
        Converter<byte[], Object> deserializer = new DeserializingConverter();

        String email = "xxx@hotmail.com";
        String name = "liweilin";
        long timeCost = 0;
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(name + i);
            userDO.setAge(28);
            userDO.setEmail(email + i);

            byte[] bytes = serializer.convert(userDO);

            long s = -System.currentTimeMillis();

            deserializer.convert(bytes);

            s += System.currentTimeMillis();

            timeCost += s;
        }

        System.out.println(timeCost);
    }

    public void testFastJson() {
        UserDO userDO = new UserDO();
        userDO.setName("liweilin");
        userDO.setAge(28);
        userDO.setEmail("xxx@hotmail.com");

        String str = JSON.toJSONString(userDO, SerializerFeature.WriteClassName);


        UserDO userDO1 = (UserDO) JSON.parse(str);

        System.out.println(userDO1);

    }
}
