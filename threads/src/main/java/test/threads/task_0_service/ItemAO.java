package test.threads.task_0_service;

import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.common.bean.ItemDO;
import test.common.entity.BaseUrlManager;
import test.threads.task_6_pc_command.ITask;
import test.threads.task_6_pc_command.ItemTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lwl on 14-5-10.
 */

@Component
public class ItemAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public void findItems(ArrayBlockingQueue<List<ItemDO>> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<ItemDO> itemDOs = sqlSessionTemplate.selectList("item.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = itemDOs.size();

            if (currentSize > 0) {

                List<ItemDO> itemDOList = new ArrayList<ItemDO>();
                for (ItemDO itemDO : itemDOs) {

                    itemDOList.add(itemDO);
                    try {

                        if (itemDOList.size() == 100) {
                            myQueue.put(itemDOList);

                            itemDOList = new ArrayList<ItemDO>();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = itemDOs.get(currentSize - 1).getId();
            }

            System.out.println("hander item a batch size:" + currentSize);

        } while (currentSize == 500);
    }

    public void handler(List<ItemDO> itemDOs) {

        for (int i = 0; i < 5; i++) {
            BaseUrlManager.getJson("http://10.200.190.13:8080/send");
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void distributionItemTask(ArrayBlockingQueue<ITask> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<ItemDO> itemDOs = sqlSessionTemplate.selectList("item.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = itemDOs.size();

            if (currentSize > 0) {

                List<ItemDO> itemDOList = new ArrayList<ItemDO>();
                for (ItemDO itemDO : itemDOs) {

                    itemDOList.add(itemDO);
                    try {

                        if (itemDOList.size() == 100) {
                            myQueue.put(new ItemTask(itemDOList));

                            itemDOList = new ArrayList<ItemDO>();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = itemDOs.get(currentSize - 1).getId();
            }

            System.out.println("hander item a batch size:" + currentSize);

        } while (currentSize == 500);
    }
}
