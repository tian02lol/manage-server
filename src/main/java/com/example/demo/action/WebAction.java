package com.example.demo.action;

import com.example.demo.Ann.NoToken;
import com.example.demo.entity.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WebAction {

    @PostMapping("/login")
    @ResponseBody
    @NoToken
    public AjaxResult login(@ModelAttribute com.example.demo.entity.UserEntity userEntity){
        if(StringUtils.isEmpty(userEntity.getUserName()) || StringUtils.isEmpty(userEntity.getUserPassword())){
            return AjaxResult.failure("用户名或密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userEntity.getUserName(),userEntity.getUserPassword());
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            //登陆失败：用户名不存在
            return AjaxResult.failure("用户名不存在");
        }catch (IncorrectCredentialsException e){
            return AjaxResult.failure("密码错误");
        }
        return AjaxResult.success(subject.getPrincipal());
    }
}
