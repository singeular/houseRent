package com.kgc.houserent.service;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DistrictService {

    PageInfo<District> selectDistrict(Integer pageIndex, Integer pageSize);

    int addDistrict(District district);

    int updateDistrict(District district);

    int deleteOne(Integer id);

    List<District> selectAllDistrict();
}
