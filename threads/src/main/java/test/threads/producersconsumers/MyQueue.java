package test.threads.producersconsumers;

import java.util.ArrayList;
import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:30
 */
public class MyQueue<T> {

    private List<T> arrs = new ArrayList<T>();

    private int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void push(T t) throws InterruptedException {
        while (arrs.size() == maxSize) {
            wait();
        }

        arrs.add(t);
        notify();
    }

    public synchronized T pop() throws InterruptedException {
        while (arrs.size() == 0) {
            wait();
        }

        T t = arrs.remove(arrs.size() - 1);
        notify();

        return t;
    }

    public synchronized int size() {
        return arrs.size();
    }
}
