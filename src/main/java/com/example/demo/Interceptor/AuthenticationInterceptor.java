package com.example.demo.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.Ann.NoToken;
import com.example.demo.biz.UserBiz;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import com.example.demo.utils.AuthUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
                return printContent(httpServletResponse,"token认证异常",401);
            }
            // 获取用户ID，如果出错抛异常
            Integer userId;
            try {
                userId = AuthUtils.getUserId(httpServletRequest);
            } catch (JWTDecodeException j) {
                return printContent(httpServletResponse,"token认证异常",401);
            }
            UserEntity user = userBiz.selectByPrimaryKey(userId);
            if (user == null) {
                return printContent(httpServletResponse,"token认证异常",401);
            }
            // 验证Token是否有效
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword() + "")).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                return printContent(httpServletResponse,"token认证失效",401);
            }
            return true;
        }
        return true;
    }

    /**
     * 返回前端状态码
     * @param response
     * @param content
     */
    private static boolean printContent(HttpServletResponse response, String content,Integer code) {
        AjaxResult result = new AjaxResult();
        result.setMsg(content);
        result.setCode(code);
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(JSONObject.fromObject(result).toString());
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return false;
        }
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
