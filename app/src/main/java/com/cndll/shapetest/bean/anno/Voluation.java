package com.cndll.shapetest.bean.anno;

import java.lang.reflect.Field;

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
}
