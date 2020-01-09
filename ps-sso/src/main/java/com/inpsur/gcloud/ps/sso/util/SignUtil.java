package com.inpsur.gcloud.ps.sso.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuqing
 * @Description:获取验签工具类
 * @date 2019年12月19日下午2:56:32
 */
public class SignUtil {

    private static String app_id = "scjdqykbyctxt";
    private static String privatekey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL3kecWhioOX+ltyjMuACCG9J4o4HgZZCJ5UZ2BvS1Jzbef6+Cd9hodLgikCQNfVWDrFFapgas9EXlsUABS5bmlb6K72OMKvtzoN8UkhhHDtTAoCkfjnCSPWu+7E/rkSSGXQ6GCLocaPz7mbWrMChnwnrLe99ioYdgbkUXH90V9LAgMBAAECgYEAg3e0KvSdTmAg78EN4zIf7yPjRRl3CBQKGHv9GlHTRyEtXWdu+QA57F+FnNi3hfZp4D5kspGQQTFnVg0xxSdgXZX3yLTpJ3H9cRwSAAL2GKwATrhFBZmXdiewCHm347V6ZT0ed8E9xo43vfm9UyezKi4Eqt6097dF0kWDwbvJCVECQQDfIcfsaI72B69WlykoOHNm8z1f0DPkv15yxtQcZyMJWMSfz7M2HlSXBAaX4s3FoID4l1Fhui7HFlpFGJWTCRcJAkEA2d0/RBMatRUWlN9XMBY9ambMjbO0WZUt8CnBaEDBze4egYyPtAhuYLuydqrg1j5pLRH5x/uaS2mhLBVKqrEkswJAEFCwBaE/ks3NDE/ROpemGIkAm9jlcnxrs4jGSa1g6nYLKoRNXk+pb8FjmgeDdEfgJk0qLjMm6HfB8ftyNGYdwQJAHmJQ1Hmlm+OZBqIvUrGHVzOHC6woVuYLRhdMtfFyFfpDnuWvlPgoDK2gshQaz0Zc/MApCc75MdNFexRtGWkjKQJAaJtuXSmmyIzTIr48Z0mB9ZwcHLguQZGGLCvo8ap7DXUFEAVq9TL9CDvYVQ/KzWS3MQRQSmLl64oXD0S5Om+xIQ==";
    private static String appword = "rwUxWioq39UPgFFI";
    private static String UUID = "E9UTJmeVf2Yv";
    private static String version = "1.0";
    private static String charset = "UTF-8";
    private static String url = "https://wfw.isdapp.shandong.gov.cn/jmasapi/jmas-api-gateway-server/gateway.do";


    public static Map<String, String> getParamMap(String interfaceId, String bizContent) {
        // 设置参数
        Map<String, String> map = new HashMap<>(16);

        // 应用唯一标识（JMAS平台获取）
        map.put("app_id", app_id);

        // 接口唯一标识（JMAS平台获取）
        map.put("interface_id", interfaceId);

        // 版本号(格式:x.x, 例如:1.0)
        map.put("version", version);
        // 业务参数
        // json格式：{"键":"值", "键":"值"}
        map.put("biz_content", bizContent);

        // 编码格式, 默认UTF-8
        map.put("charset", charset);

        // 时间戳，毫秒为单位
        map.put("timestamp", System.currentTimeMillis() + "");
        System.out.println(System.currentTimeMillis() + "");

        // 来源 0：PC；1：APP；2：支付宝；3：微信
        map.put("origin", "0");
        return map;
    }

    // 获取sign
    public static String createsign(Map<String, String> paramMap) {
        // 应用私钥（在平台应用详情处复制）
        return SignatureUtil.rsaSign(paramMap, privatekey, charset, "RSA");
    }


    public static void main(String[] args) {
        Map<String,Object> data = new HashMap<>();
        data.put("success",true);
        data.put("data","BdHg3ZZi11HR0tEi+7xMjL7QYoMHg/POEtbzrm0ZPrqP4voNTTQRxkRTdkSq+CwoN7JTuCP4s0hs2ZZu2biXHLO7xZkFPYf4pqB0+kjDNxY9mb2pFSvT79g05PVHdC39QroxX49q2CTafd9EUlZbq0GO+/3xo5ZKLSKRwRZV908=");
        data.put("code","200");
        data.put("message",null);
        String postResult = JSON.toJSONString(data);
//        System.out.println(postResult);
//        Map<String, Object> resultMap = JSON.parseObject(postResult, Map.class);
//        System.out.println(resultMap);

        testSign();

    }

    public static void testSign() {
        String ticket = "a8e010d85aac2287e15d387893664c99";
        String interfaceId = "ticketvalidate";
        String servicename = "ticketValidate";
        // 三方接口的参数，json格式：{"键":"值", "键":"值"}
        Map<String, Object> bizContentMap = new HashMap<>(16);
        bizContentMap.put("app_id", app_id);
        bizContentMap.put("servicename", servicename);
        Map<String, String> params = new HashMap<>();
        params.put("ticket", ticket);
        bizContentMap.put("params", params);
        String bizContent = JSON.toJSONString(bizContentMap);
        System.out.println(bizContent);
        Map<String, String> map = getParamMap(interfaceId, bizContent);
        String sign = createsign(map);
        System.out.println(sign);
    }

}
