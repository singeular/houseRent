package com.kgc.houserent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.House;
import com.kgc.houserent.mapper.HouseMapper;
import com.kgc.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> selectAllUserHouse(Integer userId,Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<House> houseList=houseMapper.selectAllUserHouse(userId);
        PageInfo<House> pageInfo=new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public House selectHouseAndDid(String id) {
        House house=houseMapper.selectHouseAndDid(id);
        System.out.println(house.toString());
        return house;
    }

    @Override
    public int updateHouseMsg(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleteHouse(String id){
        House house=new House();
        house.setId(id);
        house.setIsdel(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> selectHousePass(Integer isPass,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<House> houseList=houseMapper.selectHousePass(isPass);
        PageInfo<House> pageInfo=new PageInfo<>(houseList);
        return  pageInfo;
    }

    @Override
    public int updateIsPass(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int updateNoPass(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(0);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
}
