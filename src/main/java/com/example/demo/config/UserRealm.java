package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.biz.UserBiz;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * 自定义Realm
 */
@Setter
@Getter
@ConfigurationProperties(prefix="jwt.config")
public class UserRealm extends AuthorizingRealm {

    private Long timeout;

    @Autowired
    private UserBiz userBiz;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取安全数据并设置
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        com.example.demo.entity.UserEntity userEntity = userBiz.selectByUserName(token.getUsername());
        if(userEntity == null){
            return null;
        }
        String jwt = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+timeout))
                .withAudience(userEntity.getId() + "").sign(Algorithm.HMAC256(userEntity.getUserPassword()));

        return new SimpleAuthenticationInfo(jwt,userEntity.getUserPassword(),"");
    }
}
