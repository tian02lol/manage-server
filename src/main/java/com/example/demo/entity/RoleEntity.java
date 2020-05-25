package com.example.demo.entity;

import java.io.Serializable;

/**
 * role
 * @author 
 */
public class RoleEntity implements Serializable {
    private Integer id;

    private String roleName;

    private String roleDescription;

    private String roleMdules;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleMdules() {
        return roleMdules;
    }

    public void setRoleMdules(String roleMdules) {
        this.roleMdules = roleMdules;
    }
}