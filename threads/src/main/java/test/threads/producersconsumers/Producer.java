package test.threads.producersconsumers;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:49
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {
        MyQueue<String> myQueue = new MyQueue<String>(10);

        for (int i = 0; i < 10; i++) {
            new Consumer("consumer" + i, myQueue).start();
        }

        for (int i = 0; i < 10000; i++) {
            myQueue.push("task" + i);
        }
    }
}
