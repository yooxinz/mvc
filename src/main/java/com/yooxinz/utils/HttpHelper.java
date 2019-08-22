package com.yooxinz.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-08-22
 */
@Slf4j
public class HttpHelper {

    private static final String ENCODING_UTF8 = "utf-8";

    public static String doGet(String url)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException {
        CloseableHttpClient client = getHttpClient();
        return doGet(url, client, null, null, ENCODING_UTF8);
    }

    public static String doGet(String url, Map<String, String> auth)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException {
        CloseableHttpClient client = getHttpClient();
        return doGet(url, client, null, auth, ENCODING_UTF8);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> auth)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException {
        CloseableHttpClient client = getHttpClient();
        return doGet(url, client, param, auth, ENCODING_UTF8);
    }

    public static String doPost(String url, Map<String, String> param, Map<String, String> auth)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException {
        CloseableHttpClient client = getHttpClient();
        return doPost(url, client, param, auth, ENCODING_UTF8);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> auth,
                               String encoding) throws NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, IOException {
        CloseableHttpClient client = getHttpClient();
        return doGet(url, client, param, auth, encoding);
    }

    public static String doPost(String url, Map<String, String> param, Map<String, String> auth,
                                String encoding) throws NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, IOException {
        CloseableHttpClient client = getHttpClient();
        return doPost(url, client, param, auth, encoding);
    }

    public static String doGet(String url, CloseableHttpClient client, Map<String, String> param,
                               Map<String, String> auth) throws NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, IOException {
        return doGet(url, client, param, auth, ENCODING_UTF8);
    }

    public static String doGet(String url, CloseableHttpClient client, Map<String, String> auth)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException {
        return doGet(url, client, null, auth, ENCODING_UTF8);
    }

    public static String doPost(String url, CloseableHttpClient client, Map<String, String> param,
                                Map<String, String> auth) throws NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, IOException {
        return doPost(url, client, param, auth, ENCODING_UTF8);
    }

    /**
     * 模拟请求
     *
     * @param url        资源地址
     * @param encoding    编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String doPost(String url, CloseableHttpClient client, Map<String, String> param,
                                Map<String, String> auth, String encoding)
            throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException,
            IOException, IOException, KeyStoreException {
        String body = "";
        //        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000)
                .setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();
        httpPost.setConfig(requestConfig);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (param != null) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        System.out.println("请求地址：" + url);
        System.out.println("请求参数：" + nvps.toString());

        String authStr = DatatypeConverter.printBase64Binary(
                (auth.get("username") + ":" + auth.get("password")).getBytes("UTF-8"));

        httpPost.setHeader("Authorization", "Basic " + authStr);

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * 模拟请求
     *
     * @param url        资源地址
     * @param encoding    编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String doGet(String url, CloseableHttpClient client, Map<String, String> param,
                               Map<String, String> auth, String encoding)
            throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException,
            IOException, IOException, KeyStoreException {
        String body = "";

        //创建post方式请求对象
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000)
                .setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();
        httpGet.setConfig(requestConfig);

        if (param != null) {
            //装填参数
            StringBuffer urlParam = new StringBuffer("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                urlParam.append(entry.getKey() + "=" + entry.getValue() + "&&");
            }
            url = url + urlParam.toString();
        }

        System.out.println("请求地址：" + url);

        if(auth != null){
            String authStr = DatatypeConverter.printBase64Binary(
                    (auth.get("username") + ":" + auth.get("password")).getBytes("UTF-8"));
            httpGet.setHeader("Authorization", "Basic " + authStr);

        }

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpGet.setHeader("Content-type", "application/x-www-form-urlencoded");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        } else {
            body = response.getStatusLine() + ":no entity";
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLConnectionSocketFactory createIgnoreVerifySSL()
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContextBuilder builder = new SSLContextBuilder();
        // 全部信任 不做身份鉴定
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {
                return true;
            }
        });

        return new SSLConnectionSocketFactory(builder.build(),
                new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null,
                NoopHostnameVerifier.INSTANCE);
    }

    public static CloseableHttpClient getHttpClient()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //采用绕过验证的方式处理https请求
        SSLConnectionSocketFactory sslConnectionSocketFactory = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);

        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();

        return client;
    }

    /**
     * 功能：将string数据写入请求体中
     * @param url
     * @param stringEntity
     * @param charset
     * @param readTimeout
     * @param connectTimeout
     * @return
     * @throws Exception
     */
    public static String sendPostRequest(String url, String stringEntity, String charset,
                                         int readTimeout, int connectTimeout) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout)
                .setConnectTimeout(connectTimeout).build();
        post.setConfig(requestConfig);
        //post.setHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Linux; Android 4.4.4; SAMSUNG-SM-N900A Build/tt)");
        //	post.setHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        post.setHeader("Content-Type", "application/json");
        //	post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(new StringEntity(stringEntity, charset));
        try {
            HttpResponse response = client.execute(post);
            //	assertStatus(response);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String returnStr = EntityUtils.toString(entity, charset);
                return returnStr;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            //关闭流并释放资源
            try {
                client.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 功能：将string数据写入请求体中
     * @param url
     * @param stringEntity
     * @return
     * @throws Exception
     */
    public static String sendPostRequest(String url, String stringEntity) {
        return sendPostRequest(url, stringEntity, ENCODING_UTF8, 30000, 30000);
    }

    /**
     * 功能：上传富文本内容，可上传多个文件 和参数
     * @param url
     * @param params
     * @param files
     * @return
     * @throws Exception
     */
    public static String postMultiParams(String url, Map<String, String> params,
                                         Map<String, File> files) throws Exception {
        String respStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            if (files != null) {
                for (String fileName : files.keySet()) {
                    multipartEntityBuilder.addPart(fileName, new FileBody(files.get(fileName)));
                }
            }
            // 设置上传的其他参数
            setUploadParams(multipartEntityBuilder, params, ENCODING_UTF8);
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                //				log.info("uploadFile" + response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                respStr = getRespString(resEntity);
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        //		logger.info("resp=" + respStr);
        return respStr;

    }

    /**
     * 上传文件
     *
     * @param url
     * @param filesMap
     * @return
     * @throws Exception
     */
    public static String postFile(String url, Map<String, InputStream> filesMap) throws Exception {
        log.info("[url={}][params={}]", url);
        String result = null;
        try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            if (filesMap != null) {
                filesMap.forEach((name, inputStream) -> {
                    multipartEntityBuilder.addBinaryBody(name, inputStream);
                });
            }

            HttpEntity reqEntity = multipartEntityBuilder.build();
            httppost.setEntity(reqEntity);
            try (CloseableHttpResponse response = httpclient.execute(httppost);) {
                HttpEntity resEntity = response.getEntity();
                result = getRespString(resEntity);
                EntityUtils.consume(resEntity);
            }
        }
        log.info("[url={}][params={}][result={}]", url, result);

        return result;
    }

    /**
     * 设置上传文件时所附带的其他参数
     * @param multipartEntityBuilder
     * @param params
     */
    private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,
                                        Map<String, String> params, String charset) {
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                multipartEntityBuilder.addPart(key,
                        new StringBody(params.get(key), ContentType.create("text/plain", charset)));
            }
        }
    }

    /**
     * 将返回结果转化为String
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private static String getRespString(HttpEntity entity) throws Exception {
        if (entity == null) {
            return null;
        }
        InputStream is = entity.getContent();
        StringBuffer strBuf = new StringBuffer();
        byte[] buffer = new byte[4096];
        int r = 0;
        while ((r = is.read(buffer)) > 0) {
            strBuf.append(new String(buffer, 0, r, ENCODING_UTF8));
        }
        return strBuf.toString();
    }

}