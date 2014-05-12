package test.threads.task_6_pc_command;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class TaskConsumer extends Thread {

    private  ArrayBlockingQueue<ITask> myQueue;

    public TaskConsumer(ArrayBlockingQueue<ITask> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {

            ITask task = null;
            try {
                task = myQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.execute();
        }

    }
}
