package com.rt.order.configuration.connection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:config/host.properties")
@Configuration
public class StoreIpConfiguraion {
    private final static Map<String, String> storeIp = new HashMap<>();

    public static String getStoreIp(String storeNo){
        return storeIp.get(storeNo);
    }

    @Bean
    public void setStoreIp(){
        storeIp.put("1",st01Ip);
        storeIp.put("2",st02Ip);
        storeIp.put("3",st03Ip);
        storeIp.put("4",st04Ip);
        storeIp.put("5",st05Ip);
        storeIp.put("6",st06Ip);
        storeIp.put("7",st07Ip);
        storeIp.put("9",st09Ip);
        storeIp.put("10",st10Ip);
        storeIp.put("12",st12Ip);
        storeIp.put("13",st13Ip);
        storeIp.put("14",st14Ip);
        storeIp.put("16",st16Ip);
        storeIp.put("17",st17Ip);
        storeIp.put("18",st18Ip);
        storeIp.put("30",st30Ip);
        storeIp.put("31",st31Ip);
        storeIp.put("32",st32Ip);
        storeIp.put("33",st33Ip);
        storeIp.put("34",st34Ip);
        storeIp.put("36",st36Ip);
        storeIp.put("37",st37Ip);
        storeIp.put("39",st39Ip);
        storeIp.put("96",st96Ip);
        storeIp.put("99",st99Ip);
    }

    @Value("${rt.ip.st01}")
    private String st01Ip;

    @Value("${rt.ip.st02}")
    private String st02Ip;

    @Value("${rt.ip.st03}")
    private String st03Ip;

    @Value("${rt.ip.st04}")
    private String st04Ip;

    @Value("${rt.ip.st05}")
    private String st05Ip;

    @Value("${rt.ip.st06}")
    private String st06Ip;

    @Value("${rt.ip.st07}")
    private String st07Ip;

    @Value("${rt.ip.st09}")
    private String st09Ip;

    @Value("${rt.ip.st10}")
    private String st10Ip;

    @Value("${rt.ip.st12}")
    private String st12Ip;

    @Value("${rt.ip.st13}")
    private String st13Ip;

    @Value("${rt.ip.st14}")
    private String st14Ip;

    @Value("${rt.ip.st16}")
    private String st16Ip;

    @Value("${rt.ip.st17}")
    private String st17Ip;

    @Value("${rt.ip.st18}")
    private String st18Ip;

    @Value("${rt.ip.st30}")
    private String st30Ip;

    @Value("${rt.ip.st31}")
    private String st31Ip;

    @Value("${rt.ip.st32}")
    private String st32Ip;

    @Value("${rt.ip.st33}")
    private String st33Ip;

    @Value("${rt.ip.st34}")
    private String st34Ip;

    @Value("${rt.ip.st36}")
    private String st36Ip;

    @Value("${rt.ip.st37}")
    private String st37Ip;

    @Value("${rt.ip.st39}")
    private String st39Ip;

    @Value("${rt.ip.st96}")
    private String st96Ip;

    @Value("${rt.ip.st99}")
    private String st99Ip;
}