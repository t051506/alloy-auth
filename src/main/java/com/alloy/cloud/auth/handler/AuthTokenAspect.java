package com.alloy.cloud.auth.handler;

import com.alloy.cloud.common.core.base.R;
import com.alloy.cloud.common.core.constant.CommonConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 * oauth/token 认证返回格式处理
 * @Author: tankechao
 * @Date: 2020/10/27 9:15
 */
@Component
@Aspect
public class AuthTokenAspect {

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        // 放行
        R response = new R();
        Object proceed = pjp.proceed();
        if (proceed != null) {
            ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>) proceed;
            OAuth2AccessToken body = responseEntity.getBody();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                response.setCode(CommonConstants.SUCCESS);
                response.setMsg("");
                response.setData(body);
            } else {
                response.setCode(CommonConstants.FAIL);
                response.setMsg("获取授权码失败");
                response.setData("");
            }
        }
        return ResponseEntity.status(200).body(response);
    }
}
