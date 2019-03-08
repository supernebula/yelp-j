//package com.yelp.web.manage.config;
//
//import com.yelp.entity.Admin;
//import com.yelp.service.AdminService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class MyShiroRelam extends AuthorizingRealm {
//
//    @Autowired
//    private AdminService adminService;
//
//    public MyShiroRelam(AdminService adminService){
//        this.adminService = adminService;
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("用户权限配置。。。。。。。。。。");
//        //访问@RequirePermission注解的url时触发
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Admin userInfo  = (Admin)principals.getPrimaryPrincipal();
//        //获得用户的角色，及权限进行绑定
//        for(Role role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRolename());
//            for(Permission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
//    }
//
//    //验证用户登录信息
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("验证用户登录信息");
//        String username = (String)token.getPrincipal();
//        System.out.println("登录用户名： "+username);
//        System.out.println(token.getCredentials());
//        //从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
//        User user = adminService.getAdminByUsername(username);
//        if(null == user) return null;
//        System.out.println("username: "+user.getUsername()+" ; password : "+user.getPassword());
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user, //用户名
//                user.getPassword(), //密码
//                getName()  //realm name
//        );
//        return authenticationInfo;
//    }
//}
