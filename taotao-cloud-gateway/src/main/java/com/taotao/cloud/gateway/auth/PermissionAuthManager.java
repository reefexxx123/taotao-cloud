package com.taotao.cloud.gateway.auth;

import com.taotao.cloud.auth.model.SecurityMenu;
import com.taotao.cloud.auth.service.impl.AbstractPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * url权限认证
 *
 * @author dengtao
 * @date 2020/4/29 22:10
 */
@Slf4j
@Component
public class PermissionAuthManager extends AbstractPermissionService implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            ServerWebExchange exchange = authorizationContext.getExchange();
            ServerHttpRequest request = exchange.getRequest();
//            boolean isPermission = super.hasPermission(auth, request.getMethodValue(), request.getURI().getPath());
            return new AuthorizationDecision(true);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public List<SecurityMenu> findMenuByRoleCodes(String roleCodes) {
        return new ArrayList<>();
    }
}
