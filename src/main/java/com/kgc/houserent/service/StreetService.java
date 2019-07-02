package com.kgc.houserent.service;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.District;
import com.kgc.houserent.entity.Street;

import java.util.List;

public interface StreetService {
    PageInfo<Street> selectStreet(Integer pageIndex, Integer pageSize,Integer DisID);

    List<Street> selectStreetByID(Integer DisID);
}
