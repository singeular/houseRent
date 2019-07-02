package com.kgc.houserent.service;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.Users;

public interface UserService {

    PageInfo<Users> selectUsers(Integer pageIndex, Integer pageSize);

    int checkName(String userName);

    int addUser(Users users);

    Users selectUser(String name,String password);
}
