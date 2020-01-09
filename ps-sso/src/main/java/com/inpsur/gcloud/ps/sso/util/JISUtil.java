package com.inpsur.gcloud.ps.sso.util;

import com.alibaba.fastjson.JSON;
import com.inpsur.gcloud.ps.sso.exception.SSOException;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CommonsLog
@Component
public class JISUtil {
    @Autowired
    @Qualifier("jisRestTemplate")
    RestTemplate restTemplate;

    private static String app_id = "";
    private static String privatekey = "";
    private static String appword = "";
    private static String UUID = "";
    private static String version = "1.0";
    private static String charset = "UTF-8";
    private static String jisUrl = "";

    String INTERFACEID_TICKETVALIDATE = "";

    /**
     * 通过票据认证获取令牌
     *
     * @param ticket
     * @return
     */
    public Map<String, Object> getToken(String ticket) {
        if (StringUtils.isNotBlank(ticket)) {
            Map<String, Object> bizContentMap = new HashMap<>(16);
            bizContentMap.put("app_id", app_id);
            bizContentMap.put("servicename", "ticketValidate");
            Map<String, String> params = new HashMap<>();
            params.put("ticket", ticket);
            bizContentMap.put("params", params);
            String bizContent = JSON.toJSONString(bizContentMap);
            MultiValueMap<String, Object> httpParamMap = getParamMap(INTERFACEID_TICKETVALIDATE, bizContent);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            HttpEntity httpEntity = new HttpEntity<MultiValueMap<String, Object>>(httpParamMap, headers);

            long startTime = System.currentTimeMillis();//开始时间
            ResponseEntity<String> response = restTemplate.postForEntity(jisUrl, httpEntity, String.class);
            String postResult = response.getBody();
            long endTime = System.currentTimeMillis();//结束时间

            if (log.isDebugEnabled()) {
                log.debug(String.format("调用 通过票据认证获取令牌 接口结束,param=[%s],耗时：[%s ms],返回值：%s", httpParamMap, endTime - startTime, postResult));
            }
            if (StringUtils.isNotBlank(postResult)) {
                try {
                    Map<String, Object> resultMap = JSON.parseObject(postResult, Map.class);
                    if ((boolean) resultMap.get("success") && "200".equals(resultMap.get("code"))) {
                        String data = (String) resultMap.get("data");
                        String plaintext = decrypt(data, privatekey);
                        Map<String, Object> dataMap = JSON.parseObject(plaintext, Map.class);
                        return dataMap;
                    } else {
                        throw new SSOException((String) resultMap.get("code"));
                    }
                } catch (SSOException e) {
                    log.error(String.format("调用 通过票据认证获取令牌 返回失败信息,param=[%s],耗时：[%s ms],返回值：%s", httpParamMap, endTime - startTime, postResult), e);
                    throw e;
                } catch (Exception e) {
                    log.error(String.format("调用 通过票据认证获取令牌 接口失败,param=[%s],耗时：[%s ms],返回值：%s", httpParamMap, endTime - startTime, postResult), e);
                    throw new SSOException(e);
                }
            }
        }
        return null;
    }


    // 设置参数
    private static MultiValueMap<String, Object> getParamMap(String interfaceId, String bizContent) {
        MultiValueMap<String, Object> httpMap = new LinkedMultiValueMap<String, Object>();
        // 设置参数
        Map<String, String> map = new HashMap<>();
        // 应用唯一标识（JMAS平台获取）
        map.put("app_id", app_id);
        // 接口唯一标识（JMAS平台获取）
        map.put("interface_id", interfaceId);
        // 版本号(格式:x.x, 例如:1.0)
        map.put("version", version);
        // 业务参数 json格式：{"键":"值", "键":"值"}
        map.put("biz_content", bizContent);
        // 编码格式, 默认UTF-8
        map.put("charset", charset);
        // 时间戳，毫秒为单位
        map.put("timestamp", System.currentTimeMillis() + "");
        // 来源 0：PC；1：APP；2：支付宝；3：微信
        map.put("origin", "0");
        // 签名
        httpMap.add("sign", createsign(map));
        return httpMap;
    }

    // 获取sign
    private static String createsign(Map<String, String> paramMap) {
        // 应用私钥（在平台应用详情处复制）
        return SignatureUtil.rsaSign(paramMap, privatekey, charset, "RSA");
    }

    /**
     * @param privateKey
     * @param secret
     * @return
     * @throws Exception
     * @Description:解密网关返回的密文
     */
    private static String decrypt(String secret, String privateKey) throws Exception {
        return SignatureUtil.decrypt(secret, SignatureUtil.getPrivateKey(privateKey));
    }
}
