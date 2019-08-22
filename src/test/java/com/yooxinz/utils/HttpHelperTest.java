package com.yooxinz.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HttpHelperTest {

    @Test
    public void sendGetRequestTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String requestUrl = "https://blog.yooxinz.com/post/hugosearch/";
        for(int i=0;i<10;i++){
            HttpHelper.doGet(requestUrl);
        }
    }
}
