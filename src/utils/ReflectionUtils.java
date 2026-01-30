package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {
    public static void printClassInfo(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return;
        }

        Class<?> clazz = obj.getClass();
        System.out.println("Class: " + clazz.getName());

        System.out.println("Fields:");
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            System.out.println("  (no declared fields)");
        } else {
            for (Field f : fields) {
                System.out.println("  " + Modifier.toString(f.getModifiers())
                        + " " + f.getType().getSimpleName()
                        + " " + f.getName());
            }
        }

        System.out.println("Methods:");
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            System.out.println("  (no declared methods)");
        } else {
            for (Method m : methods) {
                System.out.println("  " + Modifier.toString(m.getModifiers())
                        + " " + m.getReturnType().getSimpleName()
                        + " " + m.getName()
                        + "(" + m.getParameterCount() + " params)");
            }
        }
    }
}
