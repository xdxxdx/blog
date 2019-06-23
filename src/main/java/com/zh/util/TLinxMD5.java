package com.zh.util;

//~--- JDK imports ------------------------------------------------------------

import java.security.MessageDigest;

//~--- classes ----------------------------------------------------------------

/**
 * Class TLinxMD5
 * Description
 * Create 2016-02-25 01:03:21
 *
 * @author Benny.YEE
 */
public class TLinxMD5 {

    /**
     * Method MD5Encode
     * Description 说明：
     *
     * @param origin 说明：
     * @return 返回值说明：
     */
    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = origin;

            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString = TLinx2Util.byte2hex(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

}


//~ Formatted by Jindent --- http://www.jindent.com
