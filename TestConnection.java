//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.imageio.ImageIO;

/**
* 用于测试具体发起请求的方法
*/

public class TestConnection{
        public static void main(String[] args){
                try{
                String url = "http://xjkz5jjon8wk9v736bqid3xr9if83x.burpcollaborator.net/test_ssrf";
                URL u = new URL(url);
                URLConnection urlConnection = u.openConnection();  // 并不发起连接
                //urlConnection.connect();                         // 发起DNS请求
                //urlConnection.getInputStream();                  // 发起HTTP请求
                //urlConnection.getLastModified();                   // 发起HTTP请求

                //u.openStream();                                    // 发起HTTP请求
                u.getContent();
                //ImageIO.read(u);                                    // 发起DNS/HTTP请求
                //CloseableHttpClient client = HttpClients.createDefault();
                //HttpGet httpGet = new HttpGet(url);
                //client.execute(httpGet); //发起请求
                }catch(java.io.IOException e){
                        e.printStackTrace();
                }
        }
}
