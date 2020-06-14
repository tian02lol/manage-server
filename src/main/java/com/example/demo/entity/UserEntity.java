package com.example.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
public class UserEntity implements Serializable {
    private Integer id;

    @NotEmpty(message = "用户名不允许为空")
    @Length(max = 12,message = "最多输入12个字符")
    private String userName;

    @NotEmpty(message = "密码不允许为空")
    @Length(max = 12,message = "最多输入12个字符")
    private String userPassword;

    private String userIcon;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

}