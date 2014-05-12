package test.threads.task_6_pc_command;

import test.threads.task_0_service.ItemAO;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:49
 */
public class Producer2 extends Thread{

    @Override
    public void run() {
        ItemAO itemAO = (ItemAO) test.threads.task_2_more.Main.applicationContext.getBean("itemAO");


        ArrayBlockingQueue<ITask> myQueue = new ArrayBlockingQueue<ITask>(10);

        for (int i = 0; i < 10; i++) {
            new TaskConsumer(myQueue).start();
        }

        itemAO.distributionItemTask(myQueue);
    }
}