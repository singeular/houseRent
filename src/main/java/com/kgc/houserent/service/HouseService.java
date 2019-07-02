package com.kgc.houserent.service;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.House;

import java.util.List;

public interface HouseService {

    int addHouse(House house);

    PageInfo<House> selectAllUserHouse(Integer userId,Integer page,Integer rows);

    House selectHouseAndDid(String id);

    int updateHouseMsg(House house);

    int deleteHouse(String id);

    PageInfo<House> selectHousePass(Integer isPass,Integer page,Integer rows);

    int updateIsPass(String id);

    int updateNoPass(String id);
}
