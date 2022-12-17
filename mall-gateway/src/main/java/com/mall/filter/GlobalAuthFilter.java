package com.mall.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * 网关过滤器
 */
@Slf4j
@Component
@Order(1)
public class GlobalAuthFilter implements GlobalFilter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request和response
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //2.设置放行请求 登录和验证码校验
        URI uri = request.getURI();
        log.info(uri.toString());//方便观察请求路径
        if (StrUtil.equals(request.getURI().getPath(), "/admin/login")) {
            return chain.filter(exchange);
        }
        //3.获取token
        String token = request.getHeaders().getFirst("Authorization");
        //4.远程调用发送检验token请求
        Long id = restTemplate.getForObject("http://localhost:18081/admin/" + token, Long.class);
        //5.对id进行判空,虽然sso已经做了异常处理
        if (id == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        } else {
            //验证成功,获取到了当前用户id
            //将id转存header中
            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("userId", id + "");
            }).build();
            //重置请求
            exchange.mutate().request(serverHttpRequest);
            return chain.filter(exchange);
        }
    }
}
