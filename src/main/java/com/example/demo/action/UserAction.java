package com.example.demo.action;

import com.example.demo.biz.RoleBiz;
import com.example.demo.biz.UserBiz;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import com.example.demo.utils.AuthUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
    /**
     * 获取用户列表
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult<PageInfo<UserEntity>> list(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        PageHelper.startPage(Integer.parseInt(httpServletRequest.getParameter("pageNum")),Integer.parseInt(httpServletRequest.getParameter("pageSize")));
        List users = userBiz.selectAll();
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(users);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 添加用户
     * @param userEntity
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@ModelAttribute @Valid UserEntity userEntity,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return AjaxResult.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            userBiz.insertSelective(userEntity);
            return AjaxResult.success();
        }
    }
    /**
     * 修改用户
     * @param userEntity
     * @param bindingResult
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@ModelAttribute @Valid UserEntity userEntity,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return AjaxResult.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            userBiz.updateByPrimaryKeySelective(userEntity);
            return AjaxResult.success();
        }
    }
}
