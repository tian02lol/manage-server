package com.example.demo.action;

import com.example.demo.biz.RoleBiz;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.RoleEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色
 */
@Controller
@RequestMapping("/role")
public class RoleAction {

    @Autowired
    private RoleBiz roleBiz;
    /**
     * 获取角色列表
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult<PageInfo<RoleEntity>> list(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        PageHelper.startPage(Integer.parseInt(httpServletRequest.getParameter("pageNum")),Integer.parseInt(httpServletRequest.getParameter("pageSize")));
        List users = roleBiz.selectAll();
        PageInfo<RoleEntity> pageInfo = new PageInfo<RoleEntity>(users);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 添加角色
     * @param RoleEntity
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@ModelAttribute @Valid RoleEntity RoleEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return AjaxResult.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            roleBiz.insertSelective(RoleEntity);
            return AjaxResult.success();
        }
    }
}
