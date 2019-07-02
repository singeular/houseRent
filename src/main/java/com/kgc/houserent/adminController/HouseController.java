package com.kgc.houserent.adminController;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.House;
import com.kgc.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("HouseController2")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/selectHouseWaitPass")
    @ResponseBody
    public Map<String,Object>selectHouseWaitPass(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<House> pageInfo=houseService.selectHousePass(0,page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/selectHouseHadPass")
    @ResponseBody
    public Map<String,Object>selectHouseHadPass(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<House> pageInfo=houseService.selectHousePass(1,page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/passOne")
    @ResponseBody
    public Map<String,Object> passOne(String id){
        int i=houseService.updateIsPass(id);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }

    @RequestMapping("/cancelPass")
    @ResponseBody
    public Map<String,Object> cancelPass(String id){
        int i=houseService.updateNoPass(id);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }
}
