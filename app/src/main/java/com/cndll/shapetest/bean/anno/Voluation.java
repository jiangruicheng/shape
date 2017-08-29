package com.cndll.shapetest.bean.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Voluation<V, T> {
    public T getVari(V a, Class<T> t) {
        T b = null;
        try {
            b = t.newInstance();
            for (Field f : a.getClass().getFields()) {
                if (f.isAnnotationPresent(Variable.class)) {
                    Variable v = f.getAnnotation(Variable.class);
                    if (f.get(a) != null) {
                        b.getClass().getField(v.variable()).set(b, f.get(a));
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return b;
    }

    public T setVari(V a, T t) {
        try {

            for (Field f : a.getClass().getFields()) {
                if (f.isAnnotationPresent(Variable.class)) {
                    Variable v = f.getAnnotation(Variable.class);
                    if (f.get(a) != null) {
                        t.getClass().getField(v.variable()).set(t, f.get(a));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return t;
    }

    public T setVariBind(V a, T t) {
        try {

            for (Field f : a.getClass().getFields()) {
                if (f.isAnnotationPresent(Variable.class)) {
                    Variable v = f.getAnnotation(Variable.class);
                    if (f.get(a) != null) {
                        t.getClass().getMethod("set" + upperCase(v.variable()), f.getType()).invoke(t, f.get(a));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    public String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
