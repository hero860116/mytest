package test.threads.task_6_pc_command;

import test.common.bean.ItemDO;
import test.threads.task_0_service.ItemAO;

import java.util.List;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午7:07
 */
public class ItemTask implements ITask{


    public ItemTask(List<ItemDO> itemDOs) {
        this.itemDOs = itemDOs;
    }

    private List<ItemDO> itemDOs;

    public void execute() {

        ItemAO itemAO = (ItemAO) test.threads.task_2_more.Main.applicationContext.getBean("itemAO");

        itemAO.handler(itemDOs);
    }
}
