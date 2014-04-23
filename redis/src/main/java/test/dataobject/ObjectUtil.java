package test.dataobject;

import java.io.*;

/**
 * User: weilin.li
 * Date: 13-11-28
 * Time: 下午2:07
 */
public class ObjectUtil {

    public static byte[] objectToByte(Serializable value) {
        byte[] bytes = new byte[0];
        try  {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(value);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        }
        catch(Exception e){
            System.out.println("translation"+e.getMessage());
            e.printStackTrace();
        }

        return bytes;
    }

    public static Object byteToObject(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        Object obj = new Object();
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();

            bi.close();
            oi.close();
        }
        catch(Exception e){
            System.out.println("translation"+e.getMessage());
            e.printStackTrace();
        }

        return obj;
    }
}
