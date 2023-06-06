package team.sdn.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import team.sdn.exception.NetException;

import java.io.IOException;


/**
 * @author Big-Bai
 **/
@Slf4j
public class HttpSender {
    public static String get(String url){
        return get(url,"xml");
    }
    public static String get(String url,String header) {
        try {

            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials("admin", "admin");
            provider.setCredentials(AuthScope.ANY, credentials);


            //        1、创建HttpClient对象
            HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
//        2、创建请求方式的实例
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept","application/"+header);
//        3、添加请求参数(设置请求和传输超时时间)
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
            httpGet.setConfig(requestConfig);

//
//        4、发送Http请求
            HttpResponse response = httpClient.execute(httpGet);
//        5、获取返回的内容
            String result = null;
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                log.info("请求第三方接口出现错误，状态码为:{}", statusCode);
                return null;
            }
//        6、释放资源
            httpGet.abort();
            httpClient.getConnectionManager().shutdown();
            return result;
        }catch (IOException e){
            throw new NetException("网络错误",e);
        }
    }
}
