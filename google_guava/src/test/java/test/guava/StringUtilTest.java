package test.guava;

import com.google.common.base.Splitter;

/**
 * User: weilin.li
 * Date: 14-5-11
 * Time: 下午2:10
 */
public class StringUtilTest {
    public void testSplitter() {
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
    }
}
