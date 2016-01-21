package com.jackyshan.www.pregnantmotherate.Utils;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: League
 * Date: 14-10-18
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public class MD5Util {

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }

    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

    /***
     * 加密密码
     * @param pwd
     * @return
     */
    public static String PwdMd5(String pwd) {

        return Encryption(pwd, "MD5");

    }

    public static String PwdSHA256(String pwd) {

        return Encryption(pwd, "SHA-256");

    }

    public static String Encryption(String src, String encName) {

        String pwdaes = null;
        try {
            MessageDigest digest = MessageDigest
                    .getInstance(encName);
            digest.reset();
            digest.update(src.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();

            pwdaes = hexMd5String(messageDigest);

        } catch (Exception e) {
            new MD5Util().logErr(e);
        }
        return pwdaes;



    }

    private static String hexMd5String(byte[] messageDigest) {
        String pwdaes = null;
        try {
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1)
                    hexString.append("0").append(
                            Integer.toHexString(0xFF & messageDigest[i]));
                else
                    hexString.append(Integer
                            .toHexString(0xFF & messageDigest[i]));
//                hexString.append(HEX_DIGITS[(messageDigest[i] & 0xf0) >>>
//                4]);
//                hexString.append(HEX_DIGITS[messageDigest[i] & 0x0f]);

            }
            pwdaes = hexString.toString();
        } catch (Exception e) {
            new MD5Util().logErr(e);
        }
        return pwdaes;
    }

    /***
     * 计算文件md5
     * @param filename
     * @return
     */
    public static String md5sum(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try{
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance("MD5");
            while((numRead=fis.read(buffer)) > 0) {
                md5.update(buffer,0,numRead);
            }
            fis.close();
            return hexMd5String(md5.digest());
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }

}
