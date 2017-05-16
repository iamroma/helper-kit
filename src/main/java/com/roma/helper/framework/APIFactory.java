package com.roma.helper.framework;

/**
 * Created by roma on 16/05/2017.
 */
public class APIFactory implements Factory<Object, Class> {

    @Override
    public Object produce(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Object produce = new APIFactory().produce(String.class);
        System.out.println(produce);
    }
}
