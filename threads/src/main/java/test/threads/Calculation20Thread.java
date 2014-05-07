package test.threads;

/**
 * User: weilin.li
 * Date: 14-5-4
 * Time: 下午6:52
 */
public class Calculation20Thread implements Runnable {
    private long startTime;

    private long start;
    private long end;

    public Calculation20Thread(long start, long end, long startTime) {
        this.start = start;
        this.end = end;
        this.startTime = startTime;
    }

    @Override
    public void run() {

        for (int j = 0; j < 20; j++) {

            long result = 0;
            for (long i = start; i < end;i++) {
                result += i;
            }

            long p = System.currentTimeMillis();
            System.out.println(p-startTime);
        }


        long s = System.currentTimeMillis();
        System.out.println("Calculation end costTime:" + (s - startTime) + "ms");
    }

    public static void main(String[] args) {
        new Thread(new Calculation20Thread(1,10000000, System.currentTimeMillis())).start();
    }
}
