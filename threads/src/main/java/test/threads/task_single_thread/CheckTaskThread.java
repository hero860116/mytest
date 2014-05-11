package test.threads.task_single_thread;

/**
 * Created by lwl on 14-5-10.
 */
public class CheckTaskThread extends Thread {
    @Override
    public void run() {

        while (true) {
            //访问数据库

            CheckTaskAO checkTaskAO = (CheckTaskAO)Main.applicationContext.getBean("checkTaskAO");

            checkTaskAO.test();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
