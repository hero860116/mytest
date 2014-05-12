package test.threads;


import org.junit.Test;
import test.threads.threaddispatch.CalculationThread;

/**
 * User: weilin.li
 * Date: 14-5-4
 * Time: 下午6:56
 */

public class CalculationThreadTest {

    @Test
    public void test30Thread() {
        Runnable runnable = new CalculationThread(1,1000000, System.currentTimeMillis());

        runnable.run();
    }

    @Test
    public void test1Thread() {
        Runnable runnable = new CalculationThread(1,30000000, System.currentTimeMillis());

        runnable.run();
    }
}
