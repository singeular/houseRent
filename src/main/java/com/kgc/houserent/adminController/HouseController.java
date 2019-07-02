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

    @RequestMapping("/getHouseNoPass")
    @ResponseBody
    public Map<String,Object>getHouseNoPass(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<House> pageInfo=houseService.selectHousePass(0,page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/getHousePass")
    @ResponseBody
    public Map<String,Object>getHousePass(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<House> pageInfo=houseService.selectHousePass(1,page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
