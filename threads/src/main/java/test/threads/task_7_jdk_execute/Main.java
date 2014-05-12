package test.threads.task_7_jdk_execute;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午8:28
 */
public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,15,0l, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));



    }
}
