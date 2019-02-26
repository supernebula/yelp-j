package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.business.AdminSearchParam;
import com.yelp.business.PhotoSearchParam;
import com.yelp.entity.Admin;
import com.yelp.entity.Photo;

public interface AdminService {

    /**
     * 搜索照片
     * @param param 搜索条件
     * @return
     */
    PageInfo<Admin> Search(AdminSearchParam param);

    public Admin getAdminByUsername(String username);

    public Admin getAdmin(String id);

    public Admin getAdminByPwd(String username, String password);

    public Admin save(Admin admin);

    public void deleteById(String id);


}
