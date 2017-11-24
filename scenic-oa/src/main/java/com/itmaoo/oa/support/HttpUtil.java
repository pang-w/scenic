package com.itmaoo.oa.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class HttpUtil {

    final static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attrs) {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        return null;
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (null != getRequest()) {
            return request.getSession();
        }
        return null;
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (null == ip || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUrlReferrer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    public static String getBrowserInfo(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getHost(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return uri.getHost();
    }

    public static String getSchemeAndHost(String url) throws URISyntaxException {
        URI uri = new URI(url);
        int port = uri.getPort();
        if (port != 80 && port > 0) {
            return uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort();
        }
        return uri.getScheme() + "://" + uri.getHost();
    }

    public static Map<String, String> getUrlParamsToLowerCase(String params, String encode)
            throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(params)) {
            return map;
        }

        String[] paramPairArr = params.split("&");
        for (String paramPair : paramPairArr) {
            String[] paramArr = paramPair.split("=");
            if (paramArr.length == 2) {
                map.put(paramArr[0].toLowerCase(), URLDecoder.decode(paramArr[1], encode));
            }
        }

        return map;
    }

    public static String doGet(String url) throws IOException {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
        String line = "";
        String respInfo = "";
        while ((line = reader.readLine()) != null) {
            respInfo += line;
        }
        reader.close();
        connection.disconnect();
        return respInfo;
    }

    public static void doPostNoWait(String url, Integer timeout, String content)
            throws UnsupportedEncodingException, IOException {
        HttpURLConnection connection = doPostConn(url, timeout, content);
        connection.getInputStream();
        connection.disconnect();
    }

    public static String doPost(String url, String content) throws UnsupportedEncodingException, IOException {
        HttpURLConnection connection = doPostConn(url, null, content);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
        String line = "";
        String respInfo = "";
        while ((line = reader.readLine()) != null) {
            respInfo += line;
        }
        reader.close();
        connection.disconnect();
        return respInfo;
    }

    public static String doPost(String url, Integer timeout, String content)
            throws UnsupportedEncodingException, IOException {
        HttpURLConnection connection = doPostConn(url, timeout, content);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
        String line = "";
        String respInfo = "";
        while ((line = reader.readLine()) != null) {
            respInfo += line;
        }
        reader.close();
        connection.disconnect();
        return respInfo;
    }

    private static HttpURLConnection doPostConn(String url, Integer timeout, String content)
            throws UnsupportedEncodingException, IOException {
        URL serviceUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if (null != timeout) {
            connection.setConnectTimeout(timeout);
        }
        connection.connect();
        OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
        stream.write(content);
        stream.flush();
        stream.close();
        return connection;
    }

    public static String doPost(String url, Map<String, String> postParams) throws Exception {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        post.addHeader(HttpHeaders.ACCEPT, "application/xml");

        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if (postParams != null) {
            Set<String> set = postParams.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = postParams.get(key);
                if (value != null) {
                    formparams.add(new BasicNameValuePair(key.toString(), value.toString()));
                }
            }
        }
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;

        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            post.setEntity(uefEntity);
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                String lines;
                StringBuffer sb = new StringBuffer();
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                reader.close();
                instream.close();
                return sb.toString();
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtil.doPost error:", e);
        } finally {
            response.close();
        }
        return null;
    }

    public static String notHeadPost(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<BasicNameValuePair> paramList = new ArrayList<BasicNameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    BasicNameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}
