package com.kgc.houserent.mapper;

import com.kgc.houserent.entity.House;
import com.kgc.houserent.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> selectAllUserHouse(Integer userId);

    House selectHouseAndDid(String id);

    List<House> selectHousePass(Integer isPass);
}