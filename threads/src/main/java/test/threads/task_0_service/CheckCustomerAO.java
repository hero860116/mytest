package test.threads.task_0_service;

import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.common.bean.CustomerDO;
import test.common.entity.BaseUrlManager;
import test.threads.producersconsumers.MyQueue;
import test.threads.task_6_pc_command.CustomerTask;
import test.threads.task_6_pc_command.ITask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lwl on 14-5-10.
 */

@Component
public class CheckCustomerAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public void checkCustomer() {

        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<CustomerDO> customerDOList = sqlSessionTemplate.selectList("customer.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = customerDOList.size();

            if (currentSize > 0) {
                for (CustomerDO customerDO : customerDOList) {
                     BaseUrlManager.getJson("http://10.200.190.13:8080/send");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = customerDOList.get(currentSize - 1).getId();
            }

            System.out.println("hander a batch size:" + currentSize);

        } while (currentSize == 500);

    }

    public void getCustomers(MyQueue<CustomerDO> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<CustomerDO> customerDOList = sqlSessionTemplate.selectList("customer.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = customerDOList.size();

            if (currentSize > 0) {

                for (CustomerDO customerDO : customerDOList) {

                    try {
                        myQueue.push(customerDO);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = customerDOList.get(currentSize - 1).getId();
            }

            System.out.println("hander a batch size:" + currentSize);

        } while (currentSize == 500);
    }

    public void handlerCustomer(CustomerDO customerDO) {

        long s = -System.currentTimeMillis();
        BaseUrlManager.getJson("http://10.200.190.13:8080/send");

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s += System.currentTimeMillis();

       // System.out.println("costTime:" + s);
    }

    public void getBatchCustomers(MyQueue<List<CustomerDO>> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<CustomerDO> customerDOList = sqlSessionTemplate.selectList("customer.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = customerDOList.size();

            if (currentSize > 0) {

                List<CustomerDO> customerDOs = new ArrayList<CustomerDO>();
                for (CustomerDO customerDO : customerDOList) {

                    customerDOs.add(customerDO);
                    try {

                        if (customerDOs.size() == 100) {
                            myQueue.push(customerDOs);

                            customerDOs = new ArrayList<CustomerDO>();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = customerDOList.get(currentSize - 1).getId();
            }

            System.out.println("hander a batch size:" + currentSize);

        } while (currentSize == 500);
    }

    public void handlerBatchCustomer(List<CustomerDO> customerDOs) {

        long s = -System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            BaseUrlManager.getJson("http://10.200.190.13:8080/send");
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s += System.currentTimeMillis();

        // System.out.println("costTime:" + s);
    }

    public void getBatchCustomers3(ArrayBlockingQueue<List<CustomerDO>> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<CustomerDO> customerDOList = sqlSessionTemplate.selectList("customer.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = customerDOList.size();

            if (currentSize > 0) {

                List<CustomerDO> customerDOs = new ArrayList<CustomerDO>();
                for (CustomerDO customerDO : customerDOList) {

                    customerDOs.add(customerDO);
                    try {

                        if (customerDOs.size() == 100) {
                            myQueue.put(customerDOs);

                            customerDOs = new ArrayList<CustomerDO>();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = customerDOList.get(currentSize - 1).getId();
            }

            System.out.println("hander customer a batch size:" + currentSize);

        } while (currentSize == 500);
    }

    public void handlerBatchCustomer3(List<CustomerDO> customerDOs) {

        long s = -System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            BaseUrlManager.getJson("http://10.200.190.13:8080/send");
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s += System.currentTimeMillis();

        // System.out.println("costTime:" + s);
    }

    public void distributionCustomerTask(ArrayBlockingQueue<ITask> myQueue) {
        long maxId = 0;
        int batch = 500;
        int currentSize = 0;

        do {
            List<CustomerDO> customerDOList = sqlSessionTemplate.selectList("customer.getGtId", ImmutableMap.of("id", maxId, "limit", batch));
            currentSize = customerDOList.size();

            if (currentSize > 0) {

                List<CustomerDO> customerDOs = new ArrayList<CustomerDO>();
                for (CustomerDO customerDO : customerDOList) {

                    customerDOs.add(customerDO);
                    try {

                        if (customerDOs.size() == 100) {
                            myQueue.put(new CustomerTask(customerDOs));

                            customerDOs = new ArrayList<CustomerDO>();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                maxId = customerDOList.get(currentSize - 1).getId();
            }

            System.out.println("hander customer a batch size:" + currentSize);

        } while (currentSize == 500);
    }
}
