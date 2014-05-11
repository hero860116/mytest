package test.common.entity;

import java.util.HashMap;

/**
 * User: weilin.li
 * Date: 14-4-29
 * Time: 下午6:10
 */
public class MapGenerator extends HashMap<String, Object>{
    private String[] keys;

    public static MapGenerator keys(String... keys) {
        MapGenerator mapGenerator = new MapGenerator();
        mapGenerator.setKeys(keys);

        return mapGenerator;
    }


    public MapGenerator vals(Object... vals) {

        for (int i = 0; i < keys.length; i++) {
            put(keys[i], vals[i]);
        }

        return this;
    }


    public void setKeys(String[] keys) {
        this.keys = keys;
    }
}
