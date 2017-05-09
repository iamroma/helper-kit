package com.roma.helper.common;

import com.roma.helper.Helper;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by roma on 08/05/2017.
 */
public class ArgsHelper implements Helper {

    private ArgsHelper() {}

    public static void main(String[] args) {
        System.out.println(ArgsHelper.isNull(23));
        System.out.println(ArgsHelper.isNullOrEmpty());
        System.out.println(ArgsHelper.isNull(Collections.EMPTY_LIST));
        System.out.println(ArgsHelper.isNullOrEmpty(Collections.EMPTY_LIST));
    }

    public static boolean isNull(Object... objects) {
        if (objects == null || objects.length < 1) return true;
        for (Object object : objects) {
            if (object == null) return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(Object... objects) {
        if (objects == null || objects.length < 1) return true;
        for (Object object : objects) {
            if (object instanceof String) {
                if (object == null || "".equals(((String) object).trim())) return true;
            } else if (object instanceof Collection) {
                if (object == null || ((Collection) object).size() < 1) return true;
            } else if (object instanceof Map) {
                if (object == null || ((Map) object).size() < 1) return true;
            } else {
                if (object == null) return true;
            }
        }
        return false;
    }
}
