package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.searchParam.AdminSearchParam;
import com.yelp.entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {

    /**
     * 搜索照片
     * @param param 搜索条件
     * @return
     */
    PageInfo<Admin> Search(AdminSearchParam param);

    public Admin getAdminByUsername(String username);

    public Admin getAdmin(String id);

    public Admin login(String username, String password);

    public boolean updateLastloginIp(String id, String ip);

    public Admin getAdminByPwd(String id, String password);

    public boolean insert(Admin admin);

    public boolean udpate(Admin admin);

    public boolean deleteById(String id);


}
