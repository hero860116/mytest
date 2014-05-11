package test.common.entity;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: weilin.li
 * Date: 14-3-5
 * Time: 下午4:22
 */
public class BaseUrlManager {

    /**
     * get请求
     * 根据url获取返回的json窜
     * @param url
     * @return
     */
    public static  void getJson(String url) {
        getJson(url, null, null, null);
    }

    /**
     * get请求
     * 根据url，参数列表、头信息列表，返回json窜
     *
     * @param url
     * @param paramMap
     * @param haderMap
     * @return
     */
    public static <T> void getJson(String url, Map<String, String> paramMap, Map<String, String> haderMap, Class cla) {

        if (paramMap != null && paramMap.size() > 0) {

            List<NameValuePair>params=new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
                params.add(new BasicNameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue()));
            }

            String queryString= URLEncodedUtils.format(params, "utf-8");

            if (url.indexOf('?') > 0) {
                url += "&" + queryString;
            } else {
                url += "?" + queryString;
            }
        }


        try {
            HttpGet httpget = new HttpGet(url);

            if (haderMap != null) {
                for (Map.Entry<String, String> stringStringEntry : haderMap.entrySet()) {
                    httpget.setHeader(stringStringEntry.getKey(), stringStringEntry.getValue());
                }
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                String content = EntityUtils.toString(httpEntity);
            }

        } catch (IOException e) {

        }
    }


}
