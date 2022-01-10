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


# 各种EL表达式注入的写法
Ref：
- https://github.com/artem-smotrakov/ql/blob/7d2d27394b187bee1dd5e9259a5d010473306535/java/ql/test/experimental/query-tests/security/CWE-094/Jexl2Injection.java

## SpEL的多种写法
```java
  private static final ExpressionParser PARSER = new SpelExpressionParser();

  public void testGetValue(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    ExpressionParser parser = new SpelExpressionParser();
    Expression expression = parser.parseExpression(input);
    expression.getValue();
  }

  public void testGetValueWithChainedCalls(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = new SpelExpressionParser().parseExpression(input);
    expression.getValue();
  }

  public void testSetValueWithRootObject(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = new SpelExpressionParser().parseExpression(input);

    Object root = new Object();
    Object value = new Object();
    expression.setValue(root, value);
  }

  public void testGetValueWithStaticParser(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = PARSER.parseExpression(input);
    expression.getValue();
  }

  public void testGetValueType(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = PARSER.parseExpression(input);
    expression.getValueType();
  }

  public void testWithStandardEvaluationContext(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = PARSER.parseExpression(input);

    StandardEvaluationContext context = new StandardEvaluationContext();
    expression.getValue(context);
  }

  public void testWithSimpleEvaluationContext(Socket socket) throws IOException {
    InputStream in = socket.getInputStream();

    byte[] bytes = new byte[1024];
    int n = in.read(bytes);
    String input = new String(bytes, 0, n);

    Expression expression = PARSER.parseExpression(input);
    SimpleEvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();

    // the expression is evaluated in a limited context
    expression.getValue(context);
  }
```

## JEXL的多种写法
### JXEL2
```java
    private static void runJexlExpression(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(jexlExpr);
        JexlContext jc = new MapContext();
        e.evaluate(jc);
    }

    private static void runJexlExpressionWithJexlInfo(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(
                jexlExpr, new DebugInfo("unknown", 0, 0));
        JexlContext jc = new MapContext();
        e.evaluate(jc);
    }

    private static void runJexlScript(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        Script script = jexl.createScript(jexlExpr);
        JexlContext jc = new MapContext();
        script.execute(jc);
    }

    private static void runJexlScriptViaCallable(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        Script script = jexl.createScript(jexlExpr);
        JexlContext jc = new MapContext();

        try {
            script.callable(jc).call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void runJexlExpressionViaGetProperty(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        jexl.getProperty(new Object(), jexlExpr);
    }

    private static void runJexlExpressionViaSetProperty(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        jexl.setProperty(new Object(), jexlExpr, new Object());
    }

    private static void runJexlExpressionViaUnifiedJEXLParseAndEvaluate(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        UnifiedJEXL unifiedJEXL = new UnifiedJEXL(jexl);
        unifiedJEXL.parse(jexlExpr).evaluate(new MapContext());
    }

    private static void runJexlExpressionViaUnifiedJEXLParseAndPrepare(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        UnifiedJEXL unifiedJEXL = new UnifiedJEXL(jexl);
        unifiedJEXL.parse(jexlExpr).prepare(new MapContext());
    }

    private static void runJexlExpressionViaUnifiedJEXLTemplateEvaluate(String jexlExpr) {
        JexlEngine jexl = new JexlEngine();
        UnifiedJEXL unifiedJEXL = new UnifiedJEXL(jexl);
        unifiedJEXL.createTemplate(jexlExpr).evaluate(new MapContext(), new StringWriter());
    }
```
### JXEL3
```java
    private static void runJexlExpression(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(jexlExpr);
        JexlContext jc = new MapContext();
        e.evaluate(jc);
    }

    private static void runJexlExpressionWithJexlInfo(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(new JexlInfo("unknown", 0, 0), jexlExpr);
        JexlContext jc = new MapContext();
        e.evaluate(jc);
    }

    private static void runJexlScript(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlScript script = jexl.createScript(jexlExpr);
        JexlContext jc = new MapContext();
        script.execute(jc);
    }

    private static void runJexlScriptViaCallable(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlScript script = jexl.createScript(jexlExpr);
        JexlContext jc = new MapContext();

        try {
            script.callable(jc).call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void runJexlExpressionViaGetProperty(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        jexl.getProperty(new Object(), jexlExpr);
    }

    private static void runJexlExpressionViaSetProperty(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        jexl.setProperty(new Object(), jexlExpr, new Object());
    }

    private static void runJexlExpressionViaJxltEngineExpressionEvaluate(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JxltEngine jxlt = jexl.createJxltEngine();
        jxlt.createExpression(jexlExpr).evaluate(new MapContext());
    }

    private static void runJexlExpressionViaJxltEngineExpressionPrepare(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JxltEngine jxlt = jexl.createJxltEngine();
        jxlt.createExpression(jexlExpr).prepare(new MapContext());
    }

    private static void runJexlExpressionViaJxltEngineTemplateEvaluate(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JxltEngine jxlt = jexl.createJxltEngine();
        jxlt.createTemplate(jexlExpr).evaluate(new MapContext(), new StringWriter());
    }

    private static void runJexlExpressionViaCallable(String jexlExpr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(jexlExpr);
        JexlContext jc = new MapContext();

        try {
            e.callable(jc).call();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
```

## MVEL注入
```java
  public static void testWithMvelEval(Socket socket) throws IOException {
    MVEL.eval(read(socket));
  }

  public static void testWithMvelCompileAndExecute(Socket socket) throws IOException {
    Serializable expression = MVEL.compileExpression(read(socket));
    MVEL.executeExpression(expression);
  }

  public static void testWithExpressionCompiler(Socket socket) throws IOException {
    ExpressionCompiler compiler = new ExpressionCompiler(read(socket));
    ExecutableStatement statement = compiler.compile();
    statement.getValue(new Object(), new ImmutableDefaultFactory());
    statement.getValue(new Object(), new Object(), new ImmutableDefaultFactory());
  }

  public static void testWithCompiledExpressionGetDirectValue(Socket socket) throws IOException {
    ExpressionCompiler compiler = new ExpressionCompiler(read(socket));
    CompiledExpression expression = compiler.compile();
    expression.getDirectValue(new Object(), new ImmutableDefaultFactory());
  }

  public static void testCompiledAccExpressionGetValue(Socket socket) throws IOException {
    CompiledAccExpression expression = new CompiledAccExpression(
      read(socket).toCharArray(), Object.class, new ParserContext());
    expression.getValue(new Object(), new ImmutableDefaultFactory());
  }

  public static void testMvelScriptEngineCompileAndEvaluate(Socket socket) throws Exception {
    String input = read(socket);

    MvelScriptEngine engine = new MvelScriptEngine();
    CompiledScript compiledScript = engine.compile(input);
    compiledScript.eval();

    Serializable script = engine.compiledScript(input);
    engine.evaluate(script, new SimpleScriptContext());
  }

  public static void testMvelCompiledScriptCompileAndEvaluate(Socket socket) throws Exception {
    MvelScriptEngine engine = new MvelScriptEngine();
    ExpressionCompiler compiler = new ExpressionCompiler(read(socket));
    ExecutableStatement statement = compiler.compile();
    MvelCompiledScript script = new MvelCompiledScript(engine, statement);
    script.eval(new SimpleScriptContext());
  }

  public static void testTemplateRuntimeEval(Socket socket) throws Exception {
    TemplateRuntime.eval(read(socket), new HashMap());
  }

  public static void testTemplateRuntimeCompileTemplateAndExecute(Socket socket) throws Exception {
    TemplateRuntime.execute(
      TemplateCompiler.compileTemplate(read(socket)), new HashMap());
  }

  public static void testTemplateRuntimeCompileAndExecute(Socket socket) throws Exception {
    TemplateCompiler compiler = new TemplateCompiler(read(socket));
    TemplateRuntime.execute(compiler.compile(), new HashMap());
  }

  public static void testMvelRuntimeExecute(Socket socket) throws Exception {
    ExpressionCompiler compiler = new ExpressionCompiler(read(socket));
    CompiledExpression expression = compiler.compile();
    MVELRuntime.execute(false, expression, new Object(), new ImmutableDefaultFactory());
  }
```

Ref：
- https://github.com/artem-smotrakov/ql/blob/7d2d27394b187bee1dd5e9259a5d010473306535/java/ql/test/experimental/query-tests/security/CWE-094/Jexl2Injection.java
