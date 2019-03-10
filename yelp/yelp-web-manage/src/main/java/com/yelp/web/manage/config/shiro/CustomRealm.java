package com.yelp.web.manage.config.shiro;

import com.yelp.entity.Admin;
import com.yelp.entity.Role;
import com.yelp.service.AdminService;
import com.yelp.service.PermissionService;
import com.yelp.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {

    private AdminService adminService;
    private RoleService roleService;
    private PermissionService permissionService;

    public CustomRealm(RoleService roleService, PermissionService permissionService){
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    private Set<String> getRoleByUsername(String username){
        Set<String> roleSet = new HashSet<>();
        List<Role> roleList = roleService.getRolesByAdminUsername(username);
        for (Role role : roleList) {
            roleSet.add(role.getName());
        }
        return roleSet;
    }

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roleSet = getRoleByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleSet);
        return null;
    }

    /**
     * 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Admin admin = adminService.getAdminByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, admin.getPassword(), this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(admin.getSalt()));
        return info;
    }
}
