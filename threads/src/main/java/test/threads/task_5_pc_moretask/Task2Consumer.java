package test.threads.task_5_pc_moretask;

import test.common.bean.ItemDO;
import test.threads.task_0_service.ItemAO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: weilin.li
 * Date: 14-5-5
 * Time: 下午7:46
 */
public class Task2Consumer extends Thread {

    private  ArrayBlockingQueue<List<ItemDO>> myQueue;

    public Task2Consumer(ArrayBlockingQueue<List<ItemDO>> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {

        while (true) {

            List<ItemDO> itemDOs = null;
            try {
                itemDOs = myQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ItemAO itemAO = (ItemAO) test.threads.task_2_more.Main.applicationContext.getBean("itemAO");

            itemAO.handler(itemDOs);
        }

    }
}
