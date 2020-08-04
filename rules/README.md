### Java
#### CVI-1201XX.xml    SSRF

- CVI-120101.xml    URLConnection/HttpURLConnection的SSRF
- CVI-120102.xml    org.apache.http.client.fluent.Request的SSRF
- CVI-120103.xml    URL#openStream/getContent的SSRF
- CVI-120104.xml    javax.imageio.ImageIO.read的SSRF
- CVI-120105.xml    org.apache.commons.io.IOUtils.toByteArray的SSRF
- CVI-120106.xml    org.apache.http.impl.client.CloseableHttpClient/HttpClients的SSRF
- CVI-120107.xml    com.squareup.okhttp.Request#newCall的SSRF
- CVI-120108.xml    org.springframework.web.client.RestTemplate的SSRF

#### CVI-7700XX.xml    URL跳转

- CVI-770001.xml    setHeader/addHeader("Location" 的URL跳转
- CVI-770002.xml    Spring redirect的URL跳转
- CVI-770003.xml    sendRedirect的URL跳转

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
- CVI-771013.xml    Freemarker模板注入RCE
- CVI-771014.xml    ScriptEngine注入RCE
- CVI-771015.xml    JEXL注入RCE
- CVI-771016.xml    EL注入RCE

#### CVI-7712XX.xml   各种XXE（太杂了，结合黑盒确认吧）
 
- javax.xml.parsers.DocumentBuilder
- javax.xml.stream.XMLStreamReader
- org.jdom.input.SAXBuilder
- org.jdom2.input.SAXBuilder
- javax.xml.parsers.SAXParser
- org.dom4j.io.SAXReader 
- org.xml.sax.XMLReader
- javax.xml.transform.sax.SAXSource 
- javax.xml.transform.TransformerFactory 
- javax.xml.transform.sax.SAXTransformerFactory 
- javax.xml.validation.SchemaFactory
- javax.xml.bind.Unmarshaller
- javax.xml.xpath.XPathExpression
- org.apache.poi.xssf.usermodel.XSSFWorkbook
- org.apache.poi.ss.usermodel.WorkbookFactory
- org.apache.poi.ss.usermodel.Workbook


//TODO 
1. Groovy 其他RCE的方法

#### CVI-7720XX.xml

- CVI-772001.xml      org.apache.commons.fileupload.FileItem 文件上传
- CVI-772001_1.xml    org.apache.wicket.util.upload.FileItem文件上传
- CVI-772001_2.xml    org.springframework.web.multipart.MultipartFile文件上传
- CVI-772002.xml      javax.servlet.http.Part文件上传
- CVI-772003.xml      org.springframework.web.multipart.MultipartHttpServletRequest文件上传

#### CVI-7730XX.xml

- CVI-773001.xml    路径穿越(文件读/写)


#### CVI-7740XX.xml    Mybatis下的SQL注入
- CVI-774001.xml    通用的检测.java文件中的SQLi，准确率不高，只是定位危险函数
- CVI-774002.xml    精准的检测.java文件中的SQLi，准确率较高
- CVI-774003.xml    精准的检测.xml文件中的SQLi，准确率较高

