package com.example.demo.entity;

import java.io.Serializable;

/**
 * role_menu
 * @author 
 */
public class RoleMenu implements Serializable {
    private Integer roleId;

    private Integer menuId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}