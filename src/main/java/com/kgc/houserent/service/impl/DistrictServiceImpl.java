package com.kgc.houserent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.District;
import com.kgc.houserent.entity.DistrictExample;
import com.kgc.houserent.mapper.DistrictMapper;
import com.kgc.houserent.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public PageInfo<District> selectDistrict(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<District> districtList=districtMapper.selectDistrict();
        PageInfo<District> pageInfo=new PageInfo<>(districtList);
        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insert(district);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public int deleteOne(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<District> selectAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
