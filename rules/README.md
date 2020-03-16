### Java
#### CVI-1201XX.xml    SSRF

- CVI-120101.xml    URLConnection/HttpURLConnection的SSRF
- CVI-120102.xml    org.apache.http.client.fluent.Request.Get的SSRF
- CVI-120103.xml    URL#openStream/getContent的SSRF
- CVI-120104.xml    javax.imageio.ImageIO.read的SSRF
- CVI-120105.xml    org.apache.commons.io.IOUtils.toByteArray的SSRF
- CVI-120106.xml    org.apache.http.impl.client.CloseableHttpClient/HttpClients的SSRF
- CVI-120107.xml    com.squareup.okhttp.Request#newCall的SSRF
- CVI-120108.xml    org.springframework.web.client.RestTemplate的SSRF

#### CVI-7700XX.xml    URL跳转

- CVI-770001.xml    setHeader/addHeader("Location" 的SSRF
- CVI-770002.xml    Spring redirect的SSRF
- CVI-770003.xml    sendRedirect的SSRF

#### CVI-7710XX.xml    反序列化/模板注入/RCE

- CVI-771001.xml    XStream反序列化RCE
- CVI-771002.xml    SpEL注入RCE
- CVI-771003.xml    Velocity模板注入RCE
- CVI-771004.xml    Runtime RCE
- CVI-771005.xml    ProcessBuilder RCE
- CVI-771006.xml    fastjson反序列化RCE
- CVI-771007.xml    GroovyShell.evaluate RCE
- CVI-771008.xml    GroovyScriptEngine.run RCE
- CVI-771009.xml    ObjectInputStream 反序列化RCE
- CVI-771010.xml    Yaml.load 反序列化RCE
- CVI-771011.xml    XMLDecoder反序列化RCE
- CVI-771012.xml    Jackson反序列化RCE

//TODO 
2. Groovy 其他反序列化RCE的方法
3. XXE：https://github.com/JoyChou93/java-sec-code/blob/master/src/main/java/org/joychou/controller/othervulns/ooxmlXXE.java
https://github.com/threedr3am/learnjavabug/tree/master/xxe/src/main/java/com/threedr3am/bug/xxe
4. xlsx XXE：https://github.com/JoyChou93/java-sec-code/blob/master/src/main/java/org/joychou/controller/othervulns/xlsxStreamerXXE.java


----
- CVI-772001.xml    MultipartFile/FileItem 文件上传


### 自带的PHP
- CVI-110：配置错误
- CVI-120：SSRF；
- CVI-130：一些硬编码的安全风险；
- CVI-140：各种XSS；
- CVI-160：SQL注入；
- CVI-165：LDAP注入；
- CVI-167：XXE；
- CVI-170：本地文件包含；
- CVI-180：RCE；
- CVI-190：各种信息泄露；
- CVI-200：不安全的随机数（可移除）；
- CVI-210：url跳转；
- CVI-220: HTTP响应拆分,
- CVI-260: 反序列化；
- CVI-999: 引用了存在漏洞的三方组件
