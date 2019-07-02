package com.kgc.houserent.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.District;
import com.kgc.houserent.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/DistrictList")
    @ResponseBody
    public Map<String,Object>selectByExample(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<District> PageInfo=districtService.selectDistrict(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",PageInfo.getTotal());
        map.put("rows",PageInfo.getList());
        map.put("pageSize",PageInfo.getPageSize());
        return map;
    }

    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int i=districtService.addDistrict(district);
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("/editDistrict")
    @ResponseBody
    public String updateDistrict(District district){
        int i=districtService.updateDistrict(district);
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public String deleteOne(Integer id){
        int i=districtService.deleteOne(id);
        return "{\"result\":"+i+"}";
    }
}
