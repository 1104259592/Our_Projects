package com.hq.app.mylibrary.utils;

public class DataUtil {

    /*
      con为null时，返回type
    */
    public static String NoNull(String con, String type) {
        if (con == null || "".equals(con)) {
            con = type;
        }
        return con;
    }

    /**
     * Utf8URL编码
     * @param text
     * @return
     */
    public static String Utf8URLencode(String text) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            }else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                }catch (Exception ex) {
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }

}
