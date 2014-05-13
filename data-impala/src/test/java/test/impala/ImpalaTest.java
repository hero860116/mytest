package test.impala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * User: weilin.li
 * Date: 14-5-13
 * Time: 上午10:53
 */
public class ImpalaTest {

    private static final String IMPALAD_HOST = "10.200.187.87";
    private static final String IMPALAD_JDBC_PORT = "21050";
    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/lwl_test;auth=noSasl";
    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    private Connection con;

    private Statement stmt;

    @Before
    public void init() {

        try {
            Class.forName(JDBC_DRIVER_NAME);
            con = DriverManager.getConnection(CONNECTION_URL);
            stmt = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testData() throws SQLException{

        String select = "show databases";
        ResultSet rs = stmt.executeQuery(select);

        System.out.println("\n== Begin Query Results ======================");

        // print the results to the console
        while (rs.next()) {
            // the example query returns one String column
            System.out.println(rs.getString(1));
        }

        System.out.println("== End Query Results =======================\n\n");
    }


    @Test
    public void testSelect() throws SQLException{

        String select = "select name from t_user where name = '李卫林'";
        ResultSet rs = stmt.executeQuery(select);

        System.out.println("\n== Begin Query Results ======================");

        // print the results to the console
        while (rs.next()) {
            // the example query returns one String column
            System.out.println(rs.getString(1));
        }

        System.out.println("== End Query Results =======================\n\n");
    }

    @Test
    public void testInsert() throws SQLException {

        stmt.executeUpdate("insert into t_user values (1,'abc')");

        //中文存储会得乱码，单查询出来变为中文，同时也可以根据中文查询
        //stmt.executeUpdate("insert into t_user values (1,'李卫林')");
    }

    @Test
    public void testPrepardInsert() throws SQLException{
        PreparedStatement ptmt = con.prepareStatement("insert into t_user values (?,?)");
        ptmt.setString(1, "10");
        ptmt.setString(2, "liguang001");

        ptmt.execute();
    }

    @Test
    public void testLoadData() throws SQLException {
        stmt.execute("load data inpath '/user/hive/t_user.txt' OVERWRITE into table  t_user");
    }


    @After
    public void destory() {
        try {
            con.close();
        } catch (Exception e) {
            // swallow
        }
    }
}
