Dubbo的泛化反序列化
### org.apache.dubbo:dubbo, org.apache.dubbo.common.utils.PojoUtils.realize()

```java
args = PojoUtils.realize(args, params, method.getGenericParameterTypes());
```

![43501634365161_ pic_hd](https://user-images.githubusercontent.com/30398606/137576239-6890d558-b108-4647-a510-afc4c8868dc2.jpg)

对应地，其序列化方式是：
```
PojoUtils.generalize()
```
参考：
- [Dubbo 泛化引用和泛化实现](https://developer.aliyun.com/article/724533)
- [telnet调用Dubbo接口的坑](https://www.jianshu.com/p/bbb1c92cb113)

eg:
- [CVE-2021-30179: Pre-auth RCE via arbitrary bean manipulation in the Generic filter](https://securitylab.github.com/research/apache-dubbo/)
- [CVE-2021-32824: Pre-auth RCE via arbitrary bean manipulation in the Telnet handler](https://securitylab.github.com/research/apache-dubbo/)


对客户端telnet命令的响应：
dubbo-2.7.7.jar!/org/apache/dubbo/remoting/telnet/support/TelnetHandlerAdapter.class
![43511634367050_ pic_hd](https://user-images.githubusercontent.com/30398606/137577065-02161900-7d1c-4029-b02b-5b79ef4b9bda.jpg)


找到可用的extension（命令支持）
![43521634367266_ pic_hd](https://user-images.githubusercontent.com/30398606/137577200-a8bf698e-c43b-499a-886d-b762759f7272.jpg)


https://dubbo.apache.org/en/docs/v2.7/user/references/telnet/#invoke

60行在进行json反序列化（可尝试进行fastjson反序列化漏洞利用）
```java
list = JSON.parseArray("[" + args + "]", Object.class);
```
若没成功，在109行进行`PojoUtils.realize`的反序列化操作，是另外一种反序列化漏洞。

![image](https://user-images.githubusercontent.com/30398606/137577763-852d9c6b-33f9-491f-8d9b-8eefa238eaae.png)


判断客户端参数的数量与sayHello的参数数量是否一致。若一致，则对每一个JSONObject都进行反序列化。
JSONObject是一种Map类型，而不是Array或者Collection
![43591634369755_ pic_hd](https://user-images.githubusercontent.com/30398606/137578474-d32fb36c-09ce-4d59-b514-a422569f5455.jpg)

但是它找set方法是按照字母顺序的
