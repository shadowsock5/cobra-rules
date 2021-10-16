### org.apache.dubbo:dubbo, org.apache.dubbo.common.utils.PojoUtils.realize()

```java
args = PojoUtils.realize(args, params, method.getGenericParameterTypes());
```


eg:
- [CVE-2021-30179: Pre-auth RCE via arbitrary bean manipulation in the Generic filter](https://securitylab.github.com/research/apache-dubbo/)
- [CVE-2021-32824: Pre-auth RCE via arbitrary bean manipulation in the Telnet handler](https://securitylab.github.com/research/apache-dubbo/)
