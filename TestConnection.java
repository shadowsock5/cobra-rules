import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.imageio.ImageIO;

public class TestConnection{
        public static void main(String[] args){
                try{
                String url = "http://716c0ig815i2dsupouqecmfn4ea4yt.burpcollaborator.net/test_ssrf";
                URL u = new URL(url);
                //URLConnection urlConnection = u.openConnection();  // 并不发起连接
                //urlConnection.connect();                         // 发起DNS请求
                //urlConnection.getInputStream();                  // 发起HTTP请求
                //u.openStream();                                    // 发起HTTP请求
                ImageIO.read(u);                                    // 发起DNS/HTTP请求
                }catch(java.io.IOException e){
                        e.printStackTrace();
                }
        }
}
