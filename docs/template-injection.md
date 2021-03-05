## Velocity模板注入
1、任意模板执行
```java
VelocityContext context = new VelocityContext();
StringWriter swOut = new StringWriter();
String template = "file:///etc/passwd";
Velocity.evaluate(context, swOut, "test", template);     // 第四个参数是用户控制的Velocity模板内容
```
本质：
```
org.apache.velocity.app.Velocity.evaluate(context, swOut, "test", template);
```

2、任意文件读取(通过file协议)
```java
String payload = "file:///etc/hosts";
Template a = VelocityManager.getInstance().getVelocityEngine().getTemplate(payload);
System.out.println(a.data);
```
本质：
```java
org.apache.velocity.app.VelocityEngine#getTemplate(String )
```


3、任意文件读取
```
org.apache.velocity.runtime.resource.ResourceManager#getResource(payload, 1, encoding)
```
其中ResourceManager是一个接口，其实现类至少有：
- com.atlassian.confluence.util.velocity.CompatibleVelocityResourceManager
- com.atlassian.confluence.util.velocity.ConfigurableResourceManager
- com.atlassian.confluence.util.velocity.ConfluenceVelocityResourceManager
- org.apache.velocity.runtime.resource.ResourceManagerImpl

### 排查方法
搜索`org.apache.velocity.app`。
