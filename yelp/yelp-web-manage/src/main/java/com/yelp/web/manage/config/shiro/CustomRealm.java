package com.yelp.web.manage.config.shiro;

import com.yelp.entity.Admin;
import com.yelp.entity.Permission;
import com.yelp.entity.Role;
import com.yelp.service.AdminService;
import com.yelp.service.PermissionService;
import com.yelp.service.RoleService;
import evol.security.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

    private AdminService adminService;
    private RoleService roleService;
    private PermissionService permissionService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomRealm(RoleService roleService, PermissionService permissionService, AdminService adminService){
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.adminService = adminService;
    }

    private Set<String> getRolesByUsername(String username){
        Set<String> roleSet = new HashSet<>();
        List<Role> roleList = roleService.getRolesByAdminUsername(username);
        for (Role role : roleList) {
            roleSet.add(role.getName());
        }
        return roleSet;
    }

    private Set<String> getPermissionsByUsername(String username){
        Set<String> permissionSet = new HashSet<>();
        List<Permission> permissionList = permissionService.getPermissionsByAdminUsername(username);
        for (Permission p : permissionList) {
            permissionSet.add(p.getName());
        }
        return permissionSet;
    }

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentLoginUsername = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roleSet = getRolesByUsername(currentLoginUsername);
        Set<String> permissionSet = getPermissionsByUsername(currentLoginUsername);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleSet);
        info.addStringPermissions(permissionSet);

        logger.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());

        return info;
    }

    /**
     * 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = (String) token.getPrincipal();
        String inputPwd = token.getPassword().toString();
        Admin admin = adminService.getAdminByUsername(username);
        String digestPwd = MD5Util.MD5(token.getPassword().toString(), admin.getSalt());
        ByteSource salt = ByteSource.Util.bytes(admin.getSalt());
        if (!admin.getPassword().equals(digestPwd)){
            throw new AuthenticationException("输入密码不正确！");
        }
//        if (admin.getIsFrozen() == 0){
//            throw new AuthenticationException("用户已冻结！");
//        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, token.getPassword(), salt, this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(admin.getSalt()));
        return info;
    }
}
