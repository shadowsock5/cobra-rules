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

eg:
- [CVE-2021-30179: Pre-auth RCE via arbitrary bean manipulation in the Generic filter](https://securitylab.github.com/research/apache-dubbo/)
- [CVE-2021-32824: Pre-auth RCE via arbitrary bean manipulation in the Telnet handler](https://securitylab.github.com/research/apache-dubbo/)

