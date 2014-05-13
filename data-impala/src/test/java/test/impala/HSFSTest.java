package test.impala;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

import java.io.*;
import java.net.URI;

/**
 * User: weilin.li
 * Date: 14-5-13
 * Time: 上午11:45
 */
public class HSFSTest {

    @Test
    public void testUpload() throws IOException {

        String localSrc = "d://t_user.txt";
        String dst = "hdfs://10.200.187.127:8020/user/hive/t_user.txt";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
