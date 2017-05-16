package com.roma.helper.framework;

/**
 * Created by roma on 16/05/2017.
 */
@FunctionalInterface
public interface Factory<R, T> {

    /**
     * Factory Method
     *
     * @param t Parameter Type
     * @return
     */
    R produce(T t);
}
