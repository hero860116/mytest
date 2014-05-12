package test.threads.task_5_pc_moretask;

import test.common.bean.ItemDO;
import test.threads.task_0_service.ItemAO;

import java.util.List;
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


        ArrayBlockingQueue<List<ItemDO>> myQueue = new ArrayBlockingQueue<List<ItemDO>>(10);

        for (int i = 0; i < 10; i++) {
            new Task2Consumer(myQueue).start();
        }

        itemAO.findItems(myQueue);
    }
}