package com.example.demo.action;

import com.example.demo.biz.MenuBiz;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单
 */
@Controller
@RequestMapping("/menu")
public class MenuAction {

    @Autowired
    private MenuBiz menuBiz;

    /**
     * 获取菜单列表
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult<PageInfo<MenuEntity>> list(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        List menus = menuBiz.selectAll();
        PageInfo<MenuEntity> pageInfo = new PageInfo<MenuEntity>(menus);
        return AjaxResult.success(pageInfo);
    }

    @Test
    public void test() {
        UserEntity user = new UserEntity();
        exit(user);
        System.out.print(user.getUserName());
    }

    public void exit(UserEntity a){
        a.setUserName("123");
    }
}
