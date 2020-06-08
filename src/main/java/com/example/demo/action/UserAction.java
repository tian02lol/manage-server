package com.example.demo.action;

import com.example.demo.biz.UserBiz;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import com.example.demo.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户
 */
@Controller
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserBiz userBiz;
    /**
     * 获取当前用户信息
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @GetMapping("/info")
    @ResponseBody
    public AjaxResult login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Integer userId = AuthUtils.getUserId(httpServletRequest);
        if(userId > 0){
            UserEntity user = userBiz.selectByPrimaryKey(userId);
            user.setUserPassword("");
            return AjaxResult.success(user);
        } else {
            return AjaxResult.failure("登录失效");
        }
    }
}
