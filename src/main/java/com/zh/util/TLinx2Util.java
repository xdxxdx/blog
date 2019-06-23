package com.zh.util;

//~--- non-JDK imports --------------------------------------------------------

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//~--- JDK imports ------------------------------------------------------------

//~--- classes ----------------------------------------------------------------

/**
 * Class TLinx2Util
 * Description
 * Create 2017-03-07 14:01:23
 *
 * @author Benny.YEE
 */
public class TLinx2Util {


    /**
     * 签名
     *
     * @param postMap
     * @return
     */
    public static String sign(Map<String, String> postMap) {
        String sign = null;

        try {

            /**
             * 1 A~z排序(加上open_key)
             */
            String sortStr = sort(postMap);
            System.out.println("====排序后的待签名字符串= " + sortStr);
            /**
             * 2 sha1加密(小写)
             */
            String sha1 = TLinxSHA1.SHA1(sortStr).toLowerCase();
            System.out.println("====sha1加密后的待签名字符串= " + sha1);
            /**
             * 3 md5加密(小写)
             */
            sign = TLinxMD5.MD5Encode(sha1).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sign;
    }

    /**
     * 验签
     *
     * @param respObject
     * @return
     */
    public static Boolean verifySign(JSONObject respObject) {
        String respSign = respObject.get("sign").toString();

        respObject.remove("sign");    // 删除sign节点
        respObject.put("open_key", TestParams.OPEN_KEY);
        System.out.println("==========开始验签==========");
        String veriSign = sign(JSONObject.toJavaObject(respObject, Map.class));    // 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名

        if (respSign.equals(veriSign)) {
            System.out.println("==========验签成功==========");

            return true;
        }

        return false;
    }

    /**
     * AES加密，再二进制转十六进制(bin2hex)
     *
     * @param postmap 说明：
     * @throws Exception
     */
    public static void handleEncrypt(TreeMap<String, ?> datamap, TreeMap<String, String> postmap) throws Exception {

        JSONObject dataobj = JSONObject.parseObject(JSON.toJSONString(datamap));
        String data = TLinxAESCoder.encrypt(dataobj.toString(), TestParams.OPEN_KEY);    // AES加密，并bin2hex
        System.out.println("====加密后的data= " + data);
        postmap.put("data", data);
    }

    /**
     * 签名
     *
     * @param postmap
     */
    public static void handleSign(TreeMap<String, String> postmap) {
        Map<String, String> veriDataMap = new HashMap<String, String>();

        veriDataMap.putAll(postmap);
        veriDataMap.put("open_key", TestParams.OPEN_KEY);

        // 签名
        String sign = sign(veriDataMap);

        System.out.println("====已签名字符串= " + sign);
        postmap.put("sign", sign);
    }

    /**
     * 签名（商户对账单下载接口）
     *
     * @param postmap
     */
    public static void handleSign(TreeMap<String, String> postmap, String pass) {
        Map<String, String> veriDataMap = new HashMap<String, String>();

        veriDataMap.putAll(postmap);
        String sha1Str = TLinxSHA1.SHA1(pass);
        String md5Str = TLinxMD5.MD5Encode(sha1Str).toLowerCase();
        System.out.println("====sha1Str= " + sha1Str);
        System.out.println("====md5Str= " + md5Str);
        veriDataMap.put("pass", md5Str);

        // 签名
        String sign = sign(veriDataMap);

        System.out.println("====已签名字符串= " + sign);
        postmap.put("sign", sign);
    }

    /**
     * 根据返回格式来选择post请求处理方式
     *
     * @param postmap
     * @param interfaceName
     * @param tarType
     * @return
     */
    public static String handlePostbyTarType(TreeMap<String, String> postmap, String interfaceName, String tarType) {
        if ("gzip".equals(tarType)) {
            return handlePostGZIP(postmap, interfaceName);
        } else {
            return handlePost(postmap, interfaceName);
        }
    }

    /**
     * 请求接口
     *
     * @param postmap
     * @return 响应字符串
     */
    public static String handlePost(TreeMap<String, String> postmap, String interfaceName) {
        String url = TestParams.OPEN_URL + interfaceName;
        System.out.println("====请求地址= " + url);
        if (url.contains("https")) {
            return HttpsUtil.httpMethodPost(url, postmap, "UTF-8");
        } else {
            return HttpUtil.httpMethodPost(url, postmap, "UTF-8");
        }
    }

    public static String handlePostGZIP(TreeMap<String, String> postmap, String interfaceName) {
        String url = TestParams.OPEN_URL + interfaceName;
        if (url.contains("https")) {
            return HttpsUtil.httpMethodPostGZIP(url, postmap, "UTF-8");
        } else {
            return HttpUtil.httpMethodPostGZIP(url, postmap, "UTF-8");
        }
    }

    /**
     * 十六进制字符串转byte数组
     */
    public static byte[] hex2byte(String strhex) {
        if (strhex == null)
            return null;

        int l = strhex.length();
        if (l % 2 == 1)
            return null;

        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; ++i)
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);

        return b;
    }

    /**
     * byte数组转十六进制字符串
     */
    public static String byte2hex(byte[] result) {
        StringBuffer sb = new StringBuffer(result.length * 2);
        for (int i = 0; i < result.length; i++) {
            int hight = ((result[i] >> 4) & 0x0f);
            int low = result[i] & 0x0f;
            sb.append(hight > 9 ? (char) ((hight - 10) + 'a') : (char) (hight + '0'));
            sb.append(low > 9 ? (char) ((low - 10) + 'a') : (char) (low + '0'));
        }
        return sb.toString();
    }

    /**
     * 排序
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String sort(Map paramMap) throws Exception {
        String sort = "";
        TLinxMapUtil signMap = new TLinxMapUtil();
        if (paramMap != null) {
            String key;
            for (Iterator it = paramMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                String value = ((paramMap.get(key) != null) && (!("".equals(paramMap.get(key).toString())))) ? paramMap.get(key).toString() : "";
                signMap.put(key, value);
            }
            signMap.sort();
            for (Iterator it = signMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                sort = sort + key + "=" + signMap.get(key).toString() + "&";
            }
            if ((sort != null) && (!("".equals(sort)))) {
                sort = sort.substring(0, sort.length() - 1);
            }
        }
        return sort;
    }

    /**
     * unicode转中文
     *
     * @param unicode
     * @return
     */
    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicode);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicode = unicode.replace(matcher.group(1), ch + "");
        }
        return unicode;
    }

    /**
     * Method main
     * Description 说明：
     *
     * @param args 说明：
     */
    public static void main(String[] args) {

    }
}


//~ Formatted by Jindent --- http://www.jindent.com
