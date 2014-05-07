package test.other;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import test.common.bean.AddressDO;
import test.common.bean.UserCO;
import test.common.bean.UserDO;

import java.lang.reflect.InvocationTargetException;

/**
 * User: weilin.li
 * Date: 14-5-7
 * Time: 上午9:20
 */
public class TestBean {

    @Test
    public void testCopy() throws InvocationTargetException, IllegalAccessException {
        UserDO userDO = new UserDO();
        userDO.setName("李卫林");
        userDO.setAge(28);
        userDO.setEmail("xxx@hotmail.com");
        userDO.setId(1l);

        AddressDO addressDO = new AddressDO();
        addressDO.setId(1l);
        addressDO.setCity("安徽");
        addressDO.setProvince("安庆");
        addressDO.setAddress("望江县");

        UserCO userCO = new UserCO();

        BeanUtils.copyProperties(userCO, userDO);
        BeanUtils.copyProperties(userCO, addressDO);

        System.out.println(userCO);
    }

    @Test
    public void testCopyPro() throws InvocationTargetException, IllegalAccessException {


        UserCO userCO = new UserCO();

        BeanUtils.copyProperty(userCO, "name", "lwl");

        System.out.println(userCO);
    }

    @Test
    public void testCopyPerformance() throws InvocationTargetException, IllegalAccessException {
        UserDO userDO = new UserDO();
        userDO.setName("李卫林");
        userDO.setAge(28);
        userDO.setEmail("xxx@hotmail.com");
        userDO.setId(1l);

        AddressDO addressDO = new AddressDO();
        addressDO.setId(1l);
        addressDO.setCity("安徽");
        addressDO.setProvince("安庆");
        addressDO.setAddress("望江县");


        long s = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserCO userCO = new UserCO();


            BeanUtils.copyProperties(userCO, userDO);
            BeanUtils.copyProperties(userCO, addressDO);

        }
        s += System.currentTimeMillis();
        System.out.println("time1:" + s);


        s = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserCO userCO = new UserCO();

            userCO.setCity(addressDO.getCity());
            userCO.setAge(userDO.getAge());
            userCO.setName(userDO.getName());


        }
        s += System.currentTimeMillis();
        System.out.println("time2:" + s);

    }
}
