package com.taotao.cloud.gateway.filter.gateway;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * 图形验证码
 *
 * @author dengtao
 * @date 2020/4/29 22:13
 */
@Slf4j
@Component
@AllArgsConstructor
public class ImageCodeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private static final String PARAM_CODE = "code";
    private static final String PARAM_T = "t";

    private static final String NOT_CODE_NULL = "验证码不能为空";
    private static final String NOT_LEGAL = "验证码不合法";
    private static final String INVALID = "验证码已失效";
    private static final String ERROR = "验证码错误";

    private final RedisRepository redisRepository;

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
        String code = params.getFirst(PARAM_CODE);
        String t = params.getFirst(PARAM_T);
//        if (StrUtil.isBlank(code)) {
//            throw new ValidateCodeException(NOT_CODE_NULL);
//        }
//        String key = CommonConstant.TAOTAO_CAPTCHA_KEY + t;
//        if (!redisRepository.exists(key)) {
//            throw new ValidateCodeException(NOT_LEGAL);
//        }

//        Object captcha = redisRepository.get(key);
//        if (captcha == null) {
//            throw new ValidateCodeException(INVALID);
//        }
//        if (!code.toLowerCase().equals(captcha)) {
//            throw new ValidateCodeException(ERROR);
//        }
    }
}
