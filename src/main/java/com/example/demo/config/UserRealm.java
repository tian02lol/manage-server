package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.biz.UserBiz;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

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
        String jwt = JWT.create().withAudience(userEntity.getId() + "").sign(Algorithm.HMAC256(userEntity.getUserPassword()));

        return new SimpleAuthenticationInfo(jwt,userEntity.getUserPassword(),"");
    }
}
