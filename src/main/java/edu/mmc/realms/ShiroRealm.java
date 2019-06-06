package edu.mmc.realms;

import edu.mmc.entity.vo.SystemUserVo;
import edu.mmc.service.ISystemUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm{
    @Autowired
    ISystemUserService sus;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  //授权
        System.out.println("hello zating");
        Set<String> roles = new HashSet<>();
        SystemUserVo user = (SystemUserVo)SecurityUtils.getSubject().getSession().getAttribute("user");
        if(user != null ){
            System.out.println("roleid: "+user.getRoleId().toString());
            roles.add(user.getRoleId().toString());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException { //认证
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        Object principal = upToken.getUsername();
        Object credentials = sus.getUser(principal.toString()).getPassword();
        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(principal);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "1";
        Object js=150210479L;
        Object salt = ByteSource.Util.bytes("150210479");
        int hashIterations = 10;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/pages/user/fotPwd.html", "anon");
        map.put("/pages/login.html", "anon");
        map.put("/user/login", "anon");
        map.put("/pages/statistic/statistic.html", "authc,roles[3]");
        map.put("/pages/statistic/statistic.html", "authc,roles[4]");
        System.out.println("map: "+map);
    }
}
