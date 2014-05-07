package test.threads.producersconsumers;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class Consumer extends Thread {

    private MyQueue<String> myQueue;

    public Consumer(String name, MyQueue<String> myQueue) {
        super(name);
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {

                String task = myQueue.pop();

                System.out.println(task + ":" + myQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
