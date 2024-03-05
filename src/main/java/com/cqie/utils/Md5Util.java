package com.cqie.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    // 实现一个md5加解密

//    public final static String md5Key = "hahahhaxixixix";

    /**
     *
     * @param strPwd 明文密码
     * @param key 秘钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String strPwd, String key) throws Exception{
        // 获取加密后的字符串
        String encodeStr = DigestUtils.md5Hex(strPwd+key);
        return encodeStr;
    }

    /**
     * 用户登录，密码验证
     * @param pwdStr 明文字符串
     * @param md5Str 密文字符串
     * @return
     */
    public static boolean passwordVerify(String pwdStr,String md5Str) throws Exception {
        String md5Pwd= md5(pwdStr,md5Str);
        if (md5Pwd.equalsIgnoreCase(md5Str)){
            return true;
        }
        return false;
    }
}
