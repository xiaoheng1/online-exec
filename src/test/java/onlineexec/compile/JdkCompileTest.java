package onlineexec.compile;

import onlineexec.common.ExecSystem;
import onlineexec.compile.support.JdkCompiler;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class JdkCompileTest {
    @Test
    public void testCompileSimpleJavaCode() throws Exception {
        String code = "public class Main {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.println(\"hello world\");\n" +
                "\t}\n" +
                "}";
        JdkCompiler compiler = new JdkCompiler();
        Class<?> compile = compiler.compile(code, null);
        Object o = compile.newInstance();
        Method main = compile.getMethod("main", Class.forName("[Ljava.lang.String;"));
        Object result = main.invoke(o, new String[1]);
    }

    @Test
    public void testCompileSimpleJavaCode2() throws Exception {
        String code = "import onlineexec.common.ExecSystem;\n" +
                "public class Main {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tExecSystem.out.println(\"hello world\");\n" +
                "\t}\n" +
                "}";
        JdkCompiler compiler = new JdkCompiler();
        Class<?> compile = compiler.compile(code, null);
        Object o = compile.newInstance();
        Method main = compile.getMethod("main", Class.forName("[Ljava.lang.String;"));
        Object result = main.invoke(o, new String[1]);

        String bufferString = ExecSystem.getBufferString();
        Assert.assertEquals("hello world".trim(), bufferString.trim());

    }

    @Test
    public void testExecOutputStream(){
        ExecSystem.out.println("hello world");
        String bufferString = ExecSystem.getBufferString();
        Assert.assertEquals("hello world".trim(), bufferString.trim());
    }
}
