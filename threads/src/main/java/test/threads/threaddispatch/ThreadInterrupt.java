package test.threads.threaddispatch;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午2:09
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
         MyThread myThread = new MyThread();

        myThread.start();

        Thread.sleep(2000);

        myThread.interrupt();
    }

}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println(this.isInterrupted());

        try {
            System.out.println("sleep....");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("异常捕获了"+this.isInterrupted());
        }
    }
}
