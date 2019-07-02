package com.kgc.houserent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.Street;
import com.kgc.houserent.entity.StreetExample;
import com.kgc.houserent.mapper.StreetMapper;
import com.kgc.houserent.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> selectStreet(Integer pageIndex, Integer pageSize,Integer DisID) {
        PageHelper.startPage(pageIndex,pageSize);
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(DisID);
        List<Street> streetList=streetMapper.selectByExample(streetExample);
        PageInfo<Street> pageInfo=new PageInfo<>(streetList);
        return pageInfo;
    }

    @Override
    public List<Street> selectStreetByID(Integer DisID) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(DisID);
        List<Street> streetList=streetMapper.selectByExample(streetExample);
        return streetList;
    }
}
