package com.example.demo.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.Ann.NoToken;
import com.example.demo.biz.UserBiz;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.TokenException;
import com.example.demo.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserBiz userBiz;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(NoToken.class)) {
            NoToken passToken = method.getAnnotation(NoToken.class);
            if (passToken.required()) {
                return true;
            }
        } else {
            String token = httpServletRequest.getHeader("token");
            // Token不存在
            if (token == null) {
                throw new TokenException("登录失效",401);
            }
            // 获取用户ID，如果出错抛异常
            Integer userId;
            try {
                userId = AuthUtils.getUserId(httpServletRequest);
            } catch (JWTDecodeException j) {
                throw new TokenException("登录失效",401);
            }
            UserEntity user = userBiz.selectByPrimaryKey(userId);
            if (user == null) {
                throw new TokenException("登录失效",401);
            }
            // 验证Token是否有效
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword() + "")).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new TokenException("登录失效",401);
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
