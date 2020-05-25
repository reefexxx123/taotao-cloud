package com.taotao.cloud.gateway.filter.gateway;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.auth.exception.ValidateCodeException;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.redis.template.RedisRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.ByteArrayDecoder;
import org.springframework.http.codec.DecoderHttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * 图形验证码验证
 *
 * @author dengtao
 * @date 2020/4/29 22:13
 */
@Slf4j
@Component
public class ImageCodeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstant.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }
            validateCode(request);
            return chain.filter(exchange);
        };
    }

    @SneakyThrows
    private void validateCode(ServerHttpRequest request) {
        MultiValueMap<String, String> params = request.getQueryParams();
        // 验证码
        String code = params.getFirst("code");
        // 随机标识
        String t = params.getFirst("t");
        // 验证验证码流程
        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        String key = CommonConstant.TAOTAO_CAPTCHA_KEY + t;
        if (!redisRepository.exists(key)) {
            throw new ValidateCodeException("验证码不合法");
        }

        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object captcha = redisRepository.get(key);
        if (captcha == null) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!code.toLowerCase().equals(captcha)) {
            throw new ValidateCodeException("验证码错误");
        }
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        DecoderHttpMessageReader<byte[]> httpMessageReader = new DecoderHttpMessageReader(new ByteArrayDecoder());
        ResolvableType resolvableType = ResolvableType.forClass(byte[].class);
        Mono<byte[]> mono = httpMessageReader.readMono(resolvableType, serverHttpRequest, Collections.emptyMap());
        return mono.map(String::new).block();
    }

}
