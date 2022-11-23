package by.it.khmelov.jd01_02;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@SuppressWarnings("all")

//��������� ������ �� ��������� ������ � ������� Ctrl+Shift+F10
public class Test_jd01_02 {

    @Test(timeout = 5000)
    public void testTaskA() throws Exception {
        System.out.println("\n\n�������� �� ������� � ��������");
        checkMethod("TaskA", "static step1", int[].class);
        run("-1 2 3 4 567 567 4 3 2 -1 4 4").include("-1 567");

        System.out.println("\n\n�������� �� ����� �������� ������ ��������");
        checkMethod("TaskA", "static step2", int[].class);
        run("-1 22 33 44 567 567 44 33 22 -1 4 4")
                .include("-1").include("22").include("33").include("44");

        System.out.println("\n\n�������� �� ������� ��������");
        checkMethod("TaskA", "static step3", int[].class);
        run("-1 22 33 44 567 567 44 33 22 -1 4 4").include("9 0");
    }

    @Test(timeout = 5000)
    public void testTaskAstep1_MinMax__TaskA() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step1", int[].class);
        System.out.println("�������� �� ������� -1, 2, 3, 4, 567, 567, 4, 3, 2, -1, 4, 4");
        m.invoke(null, new int[]{-1, 2, 3, 4, 567, 567, 4, 3, 2, -1, 4, 4});
        ok.include("").include("-1 567");
    }

    @Test(timeout = 5000)
    public void testTaskAstep2_Avg__TaskA() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step2", int[].class);
        System.out.println("�������� �� ������� -1, 22, 30+3, 44, 500+67, 500+67, 44, 30+3, 22, -1, 4, 4");
        m.invoke(null, new int[]{-1, 22, 33, 44, 567, 567, 44, 33, 22, -1, 4, 4});
        ok.include("").include("-1")
                .include("1").include("2").include("3").include("4")
                .include("22").include("33").include("44").exclude("567");
    }

    @Test(timeout = 5000)
    public void testTaskAstep3_IndexMinMax__TaskA() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step3", int[].class);
        System.out.println("�������� �� ������� -1, 22, 33, 44, 567, 567, 44, 33, 22, -1, 4, 4");
        m.invoke(null, new int[]{-1, 22, 33, 44, 567, 567, 44, 33, 22, -1, 4, 4});
        ok.include("").include("9 0");
    }

    @Test(timeout = 5000)
    public void testTaskB() throws Exception {
        System.out.println("\n\n�������� �� ����� ������� 5 x 5");
        checkMethod("TaskB", "step1");
        run("0 1 2 3")
                .include("11 12 13 14 15").include("16 17 18 19 20")
                .include("21 22 23 24 25");
        ;
        System.out.println("\n\n�������� �� ���� ������ ������");
        checkMethod("TaskB", "step2", int.class);
        run("0 2 3 4").include("��� ������ ������");
        run("1 2 3 4").include("������");
        run("2 2 3 4").include("�������");
        run("3 2 3 4").include("����");
        run("4 2 3 4").include("������");
        run("5 2 3 4").include("���");
        run("6 2 3 4").include("����");
        run("7 2 3 4").include("����");
        run("8 2 3 4").include("������");
        run("9 2 3 4").include("��������");
        run("10 2 3 4").include("�������");
        run("11 2 3 4").include("������");
        run("12 2 3 4").include("�������");
        run("13 2 3 4").include("��� ������ ������");

        System.out.println("\n\n�������� �� ������� ����������� ���������");
        checkMethod("TaskB", "step3", double.class, double.class, double.class);
        run("0 2 4 2").include("-1");
        run("0 2 4 0").include("0.0").include("-2.0");
        run("0 2 4 4").include("������ ���");
    }

    @Test(timeout = 5000)
    public void testTaskBstep1_Loop__TaskB() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step1");
        m.invoke(null);
        ok.include("11 12 13 14 15").include("16 17 18 19 20").include("21 22 23 24 25");
    }

    @Test(timeout = 5000)
    public void testTaskBstep2_Switch__TaskB() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step2", int.class);
        m.invoke(null, 0);
        ok.include("��� ������ ������");
        m.invoke(null, 1);
        ok.include("������");
        m.invoke(null, 2);
        ok.include("�������");
        m.invoke(null, 3);
        ok.include("����");
        m.invoke(null, 4);
        ok.include("������");
        m.invoke(null, 5);
        ok.include("���");
        m.invoke(null, 6);
        ok.include("����");
        m.invoke(null, 7);
        ok.include("����");
        m.invoke(null, 8);
        ok.include("������");
        m.invoke(null, 9);
        ok.include("��������");
        m.invoke(null, 10);
        ok.include("�������");
        m.invoke(null, 11);
        ok.include("������");
        m.invoke(null, 12);
        ok.include("�������");
        m.invoke(null, 13);
        ok.include("��� ������ ������");
    }


    @Test(timeout = 5000)
    public void testTaskBstep3_QEquation__TaskB() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step3",
                double.class, double.class, double.class);
        System.out.println("���������� ���������:");
        System.out.println("��� a=2, ��� b=4, ��� c=2, ��������� ���� ������: ����� 1");
        m.invoke(null, 2, 4, 2);
        ok.include("-1");
        System.out.println("��� a=1, ��� b=-1, ��� c=-2, ��������� ��� �����: ����� ���� � ��� ");
        m.invoke(null, 1, -1, -2);
        ok.include("-1").include("2");
        System.out.println("��� a=2, ��� b=4, ��� c=4, ��������� ������� ��� ������");
        m.invoke(null, 2, 4, 4);
        ok.include("������ ���");
    }

    @Test(timeout = 5000)
    public void testTaskC() throws Exception {
        System.out.println("\n\n�������� �� �������� ������� TaskC.step1");
        checkMethod("TaskC", "step1", int.class);
        run("3").include("-3").include("3");
        run("4").include("-4").include("4");
        Test_jd01_02 ok = run("5").include("-5").include("5");

        System.out.println("\n�������� �� ����� ��������� TaskC.step2");
        checkMethod("TaskC", "step2", int[][].class);
        int[][] m4 = {{1, -2, -2, 6}, {-1, 2, -2, 2}, {-2, -2, -6, -2}, {1, 2, -2, 6}};
        Method m = ok.aClass.getDeclaredMethod("step2", m4.getClass());
        int sum = (int) ok.invoke(m, null, new Object[]{m4});
        assertEquals("�������� ����� � step2", -6, sum);

        System.out.println("\n�������� �� �������� ��������� TaskC.step3");
        checkMethod("TaskC", "step3", int[][].class);

        m = ok.aClass.getDeclaredMethod("step3", int[][].class);
        int[][] res = (int[][]) ok.invoke(m, null, new Object[]{m4});
        int[][] expectmas = {{-1, 2, -2}, {-2, -2, -6}};
        assertArrayEquals("�� ������ ��������� ������ {{-1,2,-2},{-2,-2,-6}}", expectmas, res);
    }

    @Test(timeout = 5000)
    public void testTaskCstep1_IndexMinMax__TaskC() throws Exception {
        Test_jd01_02 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step1", int.class);
        int[][] mas = (int[][]) m.invoke(null, 5);
        boolean min = false;
        boolean max = false;
        for (int[] row : mas)
            for (int i : row) {
                if (i == -5) min = true;
                if (i == 5) max = true;
            }
        if (!max || !min) {
            fail("� ������� ��� ��������� 5 ��� �������� -5");
        }

    }


    @Test(timeout = 5000)
    public void testTaskCstep2_Sum__TaskC() throws Exception {
        Test_jd01_02 ok = run("", false);
        int[][] m4 = {{1, -2, -2, 6}, {-1, 2, -2, 2}, {-2, -2, -6, -2}, {1, 2, -2, 6}};
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step2", int[][].class);
        int sum = (int) ok.invoke(m, null, new Object[]{m4});
        assertEquals("�������� ����� � step2", -6, sum);
    }

    @Test(timeout = 5000)
    public void testTaskCstep3_DeleteMax__TaskC() throws Exception {
        Test_jd01_02 ok = run("", false);
        int[][] m4 = {{1, -2, -2, 6}, {-1, 2, -2, 2}, {-2, -2, -6, -2}, {1, 2, -2, 6}};
        Method m = checkMethod(ok.aClass.getSimpleName(), "static step3", int[][].class);
        int[][] actualmas = (int[][]) ok.invoke(m, null, new Object[]{m4});
        int[][] expectmas = {{-1, 2, -2}, {-2, -2, -6}};
        for (int i = 0; i < actualmas.length; i++) {
            System.out.println("�������� ������ " + i);
            System.out.println("         ���������: " + Arrays.toString(expectmas[i]));
            System.out.println("�� ������ ��������: " + Arrays.toString(actualmas[i]));
            assertArrayEquals("����� �������� �����������", expectmas[i], actualmas[i]);
        }
        System.out.println("�������� ��������� �������!");
    }


/*
===========================================================================================================
���� ��������������� ��� ������. �� ������� � ���� ����� ������.
�� ������� ��� �� �������� - �����, ��� ������ ����� �������.
===========================================================================================================
 */

    //-------------------------------  ������ ----------------------------------------------------------
    private Class<?> findClass(String SimpleName) {
        String full = this.getClass().getName();
        String dogPath = full.replace(this.getClass().getSimpleName(), SimpleName);
        try {
            return Class.forName(dogPath);
        } catch (ClassNotFoundException e) {
            fail("\nERROR:���� �� �������. ����� " + SimpleName + " �� ������.");
        }
        return null;
    }

    protected Method checkMethod(String className, String methodName, Class<?>... parameters) {
        Class<?> aClass = this.findClass(className);
        try {
            methodName = methodName.trim();
            Method m;
            assert aClass != null;
            if (methodName.startsWith("static")) {
                methodName = methodName.replace("static", "").trim();
                m = aClass.getDeclaredMethod(methodName, parameters);
                if (!Modifier.isStatic(m.getModifiers())) {
                    fail("\nERROR:����� " + m.getName() + " ������ ���� �����������");
                }
            } else {
                m = aClass.getDeclaredMethod(methodName, parameters);
            }
            m.setAccessible(true);
            return m;

        } catch (NoSuchMethodException e) {
            System.err.println("\nERROR:�� ������ ����� " + methodName + " ���� � ���� �������� ���������");
            System.err.println("ERROR:��������� �����: " + className);
            System.err.println("ERROR:��������� �����: " + methodName);
            throw new RuntimeException(e);
        }
    }

    private Method findMethod(Class<?> cl, String name, Class<?>... param) {
        try {
            return cl.getDeclaredMethod(name, param);
        } catch (NoSuchMethodException e) {
            fail("\nERROR:���� �� �������. ����� " + cl.getName() + "." + name + " �� ������\n");
        }
        return null;
    }

    private Object invoke(Method method, Object o, Object... value) {
        try {
            method.setAccessible(true);
            return method.invoke(o, value);
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("\nERROR:�� ������� ������� ����� " + method.getName() + "\n");
        }
        return null;
    }


    //����� ������� � ������� ����� ��� ������������
    //�� ����� ����������� ��� ������, testTaskA1 ����� �������� � TaskA1
    protected Test_jd01_02 run(String in) {
        return run(in, true);
    }

    private Test_jd01_02 run(String in, boolean runMain) {
        StackTraceElement element = Stream.of(new Throwable().getStackTrace())
                .filter(e -> e.getMethodName().startsWith("test"))
                .findFirst()
                .orElse(null);
        assert element != null;
        String[] path = element.getClassName().split("\\.");
        String nameTestMethod = element.getMethodName();
        String clName = nameTestMethod.replace("test", "");
        clName = clName.replaceFirst(".+__", "");
        clName = element.getClassName().replace(path[path.length - 1], clName);
        System.out.println("\n---------------------------------------------");
        System.out.println("����� ����� ��� " + clName);
        if (!in.isEmpty()) System.out.println("input:" + in);
        System.out.println("---------------------------------------------");
        return new Test_jd01_02(clName, in, runMain);
    }

    //-------------------------------  ���� ----------------------------------------------------------
    public Test_jd01_02() {
    }

    //���������� �����
    public Class<?> aClass; //����������� �����
    private final PrintStream oldOut = System.out; //�������� ����� ������
    private final PrintStream newOut; //���� ��� ��������� ������ ������
    private final StringWriter strOut = new StringWriter(); //���������� ������ ������

    //�������� ����������� ������
    private Test_jd01_02(String className, String in, boolean runMain) {
        aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("ERROR:�� ������ ����� " + className + "\n");
        }
        InputStream reader = new ByteArrayInputStream(in.getBytes());
        System.setIn(reader);   //�������� ������������ �����
        System.setOut(newOut);  //�������� ������������ ������

        if (runMain) //���� ����� ���������, �� ��������, ����� ������� ������ �����
            try {
                Class<?>[] argTypes = new Class[]{String[].class};
                Method main = aClass.getDeclaredMethod("main", argTypes);
                main.invoke(null, (Object) new String[]{});
                System.setOut(oldOut); //������� ������, �����, ������ ���� ��� ������
            } catch (NoSuchMethodException e) {
                fail("ERROR:� ������ " + aClass.getSimpleName() + " ��� ������ \"public static void main\"");
            } catch (IllegalAccessException | InvocationTargetException e) {
                fail("ERROR:� ������ " + aClass.getSimpleName() + "�� ������� ��������� ����� \"public static void main\"");
            }
    }


    //�������� ������
    private Test_jd01_02 is(String str) {
        assertEquals("ERROR:��������� ����� �����:\n<---������---->\n" + str + "<---�����--->",
                strOut.toString(),
                str);
        return this;
    }

    public Test_jd01_02 include(String str) {
        assertTrue("ERROR:������ �� �������: " + str + "\n",
                strOut.toString().contains(str));
        return this;
    }

    private Test_jd01_02 exclude(String str) {
        assertFalse("ERROR:������ ������ � ������: " + str + "\n",
                strOut.toString().contains(str));
        return this;
    }

    private Test_jd01_02 matches(String regexp) {
        assertTrue("ERROR:����� �� ������������� ��������: " + regexp + "\n",
                strOut.toString().matches(regexp));
        return this;
    }

    private Test_jd01_02 find(String regexp) {
        Matcher matcher = Pattern.compile(regexp).matcher(strOut.toString());
        assertTrue("ERROR:����� �� �������� �������: " + regexp + "\n",
                matcher.find());
        return this;
    }


    //���������� ���� ��������� ������
    {
        newOut = new PrintStream(new OutputStream() {
            private byte[] bytes = new byte[1];
            private int pos = 0;

            @Override
            public void write(int b) {
                if (pos == 0 && b == '\r') //������� \r (����� win mac � linux ��������� ��������
                    return;
                if (pos == 0) { //��������� ��������� https://ru.wikipedia.org/wiki/UTF-8
                    if ((b & 0b11110000) == 0b11110000) bytes = new byte[4];
                    else if ((b & 0b11100000) == 0b11100000) bytes = new byte[3];
                    else if ((b & 0b11000000) == 0b11000000) bytes = new byte[2];
                    else bytes = new byte[1];
                }
                bytes[pos++] = (byte) b;
                if (pos == bytes.length) { //������ �����
                    String s = new String(bytes); //������� ���� ������
                    strOut.append(s); //�������� ����� ��� �����
                    oldOut.append(s); //����� � ������� �����
                    pos = 0; //������� ����� ������
                }

            }
        });
    }
}