package test.threads.threaddispatch;

/**
 * User: weilin.li
 * Date: 14-5-4
 * Time: 下午6:52
 */
public class CalculationBlockThread extends Thread {
    private long start;
    private long end;
    private long startTime;

    public CalculationBlockThread(long start, long end, long startTime) {
        this.start = start;
        this.end = end;
        this.startTime = startTime;
    }

    @Override
    public void run() {

        long result = 0;
        for (long i = start; i < end;i++) {
            result += i;
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long s = System.currentTimeMillis();
        System.out.println("Calculation end costTime:" + (s - startTime) + "ms");
    }

    public static void main(String[] args) {
        System.out.println("Calculation start");


        CalculationBlockThread[] threads = new CalculationBlockThread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] =  new CalculationBlockThread(1,10000000, 0);
        }

        long s = System.currentTimeMillis();
        for (CalculationBlockThread thread : threads) {
            thread.setStartTime(s);
            thread.start();
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
