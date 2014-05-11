package test.common.bean;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.Serializable;

/**
 * User: weilin.li
 * Date: 14-4-28
 * Time: 下午3:20
 */
public class BaseDO implements Serializable{


    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
