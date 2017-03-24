package com.common.tool.doc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtil {

    public static void main(String[] args) throws Exception {
        //System.out.println(getAnnotationMethodVal(LogController.class, RequestMapping.class, "download", "value"));
    }

    public static <T> String getAnnotationClassVal(Class className, Class<T> annotationClass, String methodName)
            throws Exception {
        String result = "";
        Annotation annotation = className.getAnnotation(annotationClass);
        // 输出：
        // @com.doctor.spring.core.AnnotationPractice$MyAnnotation(value=AnnotationP,
        // num=12, address=[1, 2])

        Method method = annotation.annotationType().getDeclaredMethod(methodName, null);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        Object invoke = method.invoke(annotation);
        // System.out.println("invoke methd =" + method.getName() +
        // ",result:" + invoke);
        if (invoke.getClass().isArray()) {
            Object[] temp = (Object[]) invoke;
            for (Object o : temp) {
                // System.out.println(o);
                if (o != null) {
                    result = o.toString();
                }
            }
        }
        return result;
    }

    public static <T> String getAnnotationMethodVal(Class className, Class<T> annotationClass, String methodName,
            String annotationName) throws Exception {
        String result = "";

        Method[] methods = className.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {

                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {

                    if (annotation.annotationType().getSimpleName().equals(annotationClass.getSimpleName())) {
                        // 输出：
                        // @com.doctor.spring.core.AnnotationPractice$MyAnnotation(value=AnnotationP,
                        // num=12, address=[1, 2])

                        Method methodTmp = annotation.annotationType().getDeclaredMethod(annotationName, null);
                        if (!methodTmp.isAccessible()) {
                            methodTmp.setAccessible(true);
                        }
                        Object invoke = methodTmp.invoke(annotation);
                        // System.out.println("invoke methd =" +
                        // method.getName() + ",result:" + invoke);
                        if (invoke.getClass().isArray()) {
                            Object[] temp = (Object[]) invoke;
                            for (Object o : temp) {
                                // System.out.println(o);
                                if (o != null) {
                                    result = o.toString();
                                }
                            }
                        }
                    }

                }

            }
        }

        return result;
    }

}
