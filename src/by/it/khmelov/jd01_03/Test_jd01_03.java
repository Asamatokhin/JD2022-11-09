package by.it.khmelov.jd01_03;

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
public class Test_jd01_03 {

    @Test(timeout = 5000)
    public void testTaskA1__InOut() throws Exception {
        Test_jd01_03 ok = run("", false);
        checkMethod(ok.aClass.getSimpleName(), "static getArray", String.class);
        double[] expArr = {0.1, 0.2, -0.2, -0.1};
        Method m = ok.aClass.getDeclaredMethod("getArray", String.class);
        System.out.println("�������� ����� ��� ������� ����:");
        System.out.println("0.1 0.2 -0.2 -0.1");
        double[] arr = (double[]) ok.invoke(m, null, new Object[]{"0.1 0.2 -0.2 -0.1"});
        assertArrayEquals("������� �������� ����", expArr, arr, 1e-9);
        System.out.println("�������� ����� ��� ������� ��������� �������");
    }

    @Test(timeout = 5000)
    public void testTaskA2_printSimple__InOut() throws Exception {
        Test_jd01_03 ok = run("", false);
        checkMethod(ok.aClass.getSimpleName(), "static printArray", double[].class);
        Method m = ok.aClass.getDeclaredMethod("printArray", double[].class);
        System.out.println("�������� ������ ��� �������:");
        double[] arr = {1, 2, 3, 4};
        ok.invoke(m, null, new Object[]{arr});
        for (double a : arr) {
            //������� � Integer �.�. ������� ���������� � printf ��� ����� ����� ������ �����
            ok.include(Integer.toString((int) a));
        }
        System.out.println("�������� ������ ��� ������� ��������� �������");
    }

    @Test(timeout = 5000)
    public void testTaskA3_printWithNameAndCol__InOut() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static printArray", double[].class, String.class, int.class);
        System.out.println("�������� ������ ��� �������:");
        double[] arr = {0.1, 0.2, -0.2, -0.1, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        ok.invoke(m, null, arr, "Massiv", 2);
        System.out.println("\n\nFind out with printf for:");
        for (double a : arr) {
            //������� � Integer �.�. ������� ���������� � printf ��� ����� ����� ������ �����
            String sf = String.format("%.1f", a);
            System.out.print(sf + " | ");
            ok.include(sf);
        }
        System.out.println("\nCheck printf ok");
        ok.include("\n").include("Massiv");
        System.out.println("�������� ������ ��� ������� ��������� �������");
        //��� ����� �������� �� ����� ������� �������, �� ��� �� ����, ��� ����� ������
    }

    @Test(timeout = 5000)
    public void testTaskB1_findMin__Helper() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static findMin", double[].class);
        double[] arr = {0.1, 0.2, -0.2, -0.1, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        double min = (double) ok.invoke(m, null, arr);
        Arrays.sort(arr);
        assertEquals("������� ������ �������", arr[0], min, 1e-10);
    }

    @Test(timeout = 5000)
    public void testTaskB2_findMax__Helper() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static findMax", double[].class);
        double[] arr = {0.1, 0.2, -0.2, -0.1, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        double min = (double) ok.invoke(m, null, arr);
        Arrays.sort(arr);
        assertEquals("�������� ������ �������", arr[arr.length - 1], min, 1e-10);
    }

    @Test(timeout = 5000)
    public void testTaskB3_sort__Helper() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static sort", double[].class);
        double[] arr = {0.1, 0.2, -0.2, -0.1, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        double[] sorted = {0.1, 0.2, -0.2, -0.1, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        System.out.println("��������  ������:" + Arrays.toString(arr));
        Arrays.sort(sorted);
        System.out.println("��������� ������:" + Arrays.toString(sorted));
        ok.invoke(m, null, arr);
        System.out.println("  ������� ������:" + Arrays.toString(arr));
        assertArrayEquals("������� �������� ����������", sorted, arr, 1e-9);
        System.out.println("OK. ������ ������������");
    }

    @Test(timeout = 5000)
    public void testTaskC1_multiplyMatrixAndVector__Helper() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static multiply", double[][].class, double[].class);
        System.out.println("�������� ������������ ������� � �������");
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        double[] vector = {1, 2, 3};
        double[] res = (double[]) ok.invoke(m, null, matrix, vector);
        double[] exp = {
                1 * 1 + 2 * 2 + 3 * 3,
                4 * 1 + 5 * 2 + 6 * 3
        };
        System.out.println("{{1,2,3},{4,5,6}}*{1,2,3}={14,32}");
        System.out.println("��������� ������ " + Arrays.toString(exp));
        assertArrayEquals("������������ ������� �������", exp, res, 1e-10);
        System.out.println("�������� ��������� �������.");
    }


    @Test(timeout = 5000)
    public void testTaskC2_multiplyMatrixAndMatrix__Helper() throws Exception {
        Test_jd01_03 ok = run("", false);
        Method m = checkMethod(ok.aClass.getSimpleName(), "static multiply", double[][].class, double[][].class);
        System.out.println("�������� ������������ ������� � �������");
        double[][] left = {
                {1, 2, 3},
                {4, 5, 6}
        };

        double[][] right = {
                {1, 2},
                {3, 4},
                {5, 6}
        };

        double[][] res = (double[][]) ok.invoke(m, null, left, right);
        double[][] exp = {
                {22, 28},
                {49, 64}
        };
        System.out.println("{{1,2,3},{4,5,6}}*{{1,2},{3,4},{5,6}}={{22,28},{49,64}}");
        for (int i = 0; i < exp.length; i++) {
            System.out.println("���������:  " + Arrays.toString(exp[i]));
            System.out.println("����������: " + Arrays.toString(res[i]));
            assertArrayEquals("������������ ������� �������", exp[i], res[i], 1e-10);
        }
        System.out.println("\n�������� ��������� �������.");
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
    protected Test_jd01_03 run(String in) {
        return run(in, true);
    }

    private Test_jd01_03 run(String in, boolean runMain) {
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
        return new Test_jd01_03(clName, in, runMain);
    }

    //-------------------------------  ���� ----------------------------------------------------------
    public Test_jd01_03() {
    }

    //���������� �����
    public Class<?> aClass; //����������� �����
    private final PrintStream oldOut = System.out; //�������� ����� ������
    private final PrintStream newOut; //���� ��� ��������� ������ ������
    private final StringWriter strOut = new StringWriter(); //���������� ������ ������

    //�������� ����������� ������
    private Test_jd01_03(String className, String in, boolean runMain) {
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
    private Test_jd01_03 is(String str) {
        assertEquals("ERROR:��������� ����� �����:\n<---������---->\n" + str + "<---�����--->",
                strOut.toString(),
                str);
        return this;
    }

    public Test_jd01_03 include(String str) {
        assertTrue("ERROR:������ �� �������: " + str + "\n",
                strOut.toString().contains(str));
        return this;
    }

    private Test_jd01_03 exclude(String str) {
        assertFalse("ERROR:������ ������ � ������: " + str + "\n",
                strOut.toString().contains(str));
        return this;
    }

    private Test_jd01_03 matches(String regexp) {
        assertTrue("ERROR:����� �� ������������� ��������: " + regexp + "\n",
                strOut.toString().matches(regexp));
        return this;
    }

    private Test_jd01_03 find(String regexp) {
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