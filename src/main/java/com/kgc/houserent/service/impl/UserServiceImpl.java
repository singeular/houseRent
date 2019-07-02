package com.kgc.houserent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.Users;
import com.kgc.houserent.entity.UsersExample;
import com.kgc.houserent.mapper.UsersMapper;
import com.kgc.houserent.service.UserService;
import com.kgc.houserent.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> selectUsers(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Users> usersList=usersMapper.selectAll();
        PageInfo<Users> pageInfo=new PageInfo(usersList);
        return pageInfo;
    }

    @Override
    public int checkName(String userName) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andNameEqualTo(userName);
        criteria.andIsadminEqualTo(0);
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        return usersList.size()==0?0:1;
    }

    @Override
    public int addUser(Users users) {
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users selectUser(String name, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        criteria.andIsadminEqualTo(0);
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        if(usersList.size()==1){
            return usersList.get(0);
        }
        return null;
    }
}
