package com.roma.helper.encrypt;

import com.roma.helper.common.ArgsHelper;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by roma on 16/05/2017.
 */
public class EncryptHelper {

    private EncryptHelper() {}

    public static String md5(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static boolean check(String password, String hash) {
        if (!ArgsHelper.isNullOrEmpty(password, hash)) {
            if (hash.equals(md5(password))) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(EncryptHelper.md5("123456"));
    }
}
