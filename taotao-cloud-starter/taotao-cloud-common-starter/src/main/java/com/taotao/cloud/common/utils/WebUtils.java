package com.taotao.cloud.common.utils;

import lombok.val;
import org.omg.CORBA.ServerRequest;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.ByteArrayDecoder;
import org.springframework.http.codec.DecoderHttpMessageReader;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Web 上下文操作
 *
 * @author: dengtao
 * @version: 2019-07-01 17:56
 **/
public class WebUtils {

    private static final ThreadLocal<WebContext> threadContext = new ThreadLocal<WebContext>();

    public static WebContext getContext() {
        WebContext webContext = threadContext.get();
        return webContext;
    }

    public static HttpServletRequest getRequest() {
        WebContext webContext = getContext();
        return webContext == null ? null : webContext.request;
    }

    public static HttpServletResponse getResponse() {
        WebContext webContext = getContext();
        return webContext == null ? null : webContext.response;
    }

    public static void bindContext(HttpServletRequest request, HttpServletResponse response) {
        threadContext.set(new WebContext(request, response));
    }

    public static void clearContext() {
        threadContext.remove();
    }

    public static class WebContext {
        private HttpServletRequest request;
        private HttpServletResponse response;

        public WebContext(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }
    }

    public static String getBaseUrl() {
        if (!ContextUtils.isWeb()) {
            return "";
        }
        val webServer = ContextUtils.getConfigurableWebServerApplicationContext().getWebServer();
        if (webServer == null) {
            return "";
        }
        return "http://" + NetworkUtils.getIpAddress() + ":" + webServer.getPort();
    }

    public static Map<String, String> getAllRequestParam(HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return res;
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String getBodyString(ServerHttpRequest serverHttpRequest) {
        HttpMessageReader<byte[]> httpMessageReader = new DecoderHttpMessageReader(new ByteArrayDecoder());
        ResolvableType resolvableType = ResolvableType.forClass(byte[].class);
        Mono<byte[]> mono = httpMessageReader.readMono(resolvableType, serverHttpRequest, Collections.emptyMap());
        return mono.map(String::new).block();
    }

    public static String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString().trim();
    }


}
