package com.example.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * role
 * @author 
 */
@Data
public class RoleEntity implements Serializable {
    private Integer id;

    @NotEmpty(message = "角色名称不允许为空")
    @Length(max = 12,message = "最多输入12个字符")
    private String roleName;

    @Length(max = 30,message = "最多输入30个字符")
    private String roleDescription;

    @NotEmpty(message = "角色标识不允许为空")
    @Length(max = 12,message = "最多输入12个字符")
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