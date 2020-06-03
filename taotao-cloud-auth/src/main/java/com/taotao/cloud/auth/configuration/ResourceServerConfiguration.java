//package com.taotao.cloud.auth.configuration;
//
//import com.taotao.cloud.auth.authentication.mobile.MobileAuthenticationSecurityConfig;
//import com.taotao.cloud.auth.authentication.mobile.MobileFilter;
//import com.taotao.cloud.auth.authentication.openid.OpenIdAuthenticationSecurityConfig;
//import com.taotao.cloud.auth.authentication.third.ThirdAuthenticationSecurityConfig;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.social.security.SpringSocialConfigurer;
//
///**
// * oauth2资源服务器
// *
// * @author dengtao
// * @date 2020/4/29 20:16
// */
//@Primary
//@Slf4j
//@Configuration
//@Order(1800)
//@EnableResourceServer
//@AllArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    private final MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;
//    private final ThirdAuthenticationSecurityConfig thirdAuthenticationSecurityConfig;
//    private final OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
//    private final AuthenticationEntryPoint authenticationEntryPoint;
//    private final AccessDeniedHandler accessDeniedHandler;
//    private final MobileFilter mobileFilter;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .and()
//                .csrf()
//                .disable()
//                .apply(openIdAuthenticationSecurityConfig).and()
//                .apply(thirdAuthenticationSecurityConfig).and()
//                .apply(mobileAuthenticationSecurityConfig).and();
//
//        http.addFilterBefore(mobileFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler);
//    }
//
//}
