package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.biz.UserBiz;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义Realm
 */
@Setter
@Getter
@ConfigurationProperties(prefix="jwt.config")
public class UserRealm extends AuthorizingRealm {

    private String timeout;

    @Autowired
    private UserBiz userBiz;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        com.example.demo.entity.UserEntity userEntity = userBiz.selectByUserName(token.getUsername());
        if(userEntity == null){
            return null;
        }
        String jwt = JWT.create()
                //.withExpiresAt(new Date(System.currentTimeMillis() + timeout))
                .withAudience(userEntity.getId() + "").sign(Algorithm.HMAC256(userEntity.getUserPassword()));

        return new SimpleAuthenticationInfo(jwt,userEntity.getUserPassword(),"");
    }
}
