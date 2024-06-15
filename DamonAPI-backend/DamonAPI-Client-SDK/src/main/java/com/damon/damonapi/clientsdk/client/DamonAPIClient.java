package com.damon.damonapi.clientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.damon.damonapi.clientsdk.model.Dog;
import com.damonapi.common.model.entity.SDG;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import static com.damon.damonapi.clientsdk.utils.SignUtils.genSign;


@Data
public class DamonAPIClient {
    private static final String GATEWAY_HOST = "http://localhost:8123";

    private String accessKey;

    private String secretKey;

    public DamonAPIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST + "/api/name", paramMap);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

    public String getDogNameByPost(Dog dog) {
        String json = JSONUtil.toJsonStr(dog);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/dog")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
//        System.out.println(result);
        return result;
    }


    public String getSDG(SDG sdg) {
        String json = JSONUtil.toJsonStr(sdg);
        System.out.println(json);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/SDG/post")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
