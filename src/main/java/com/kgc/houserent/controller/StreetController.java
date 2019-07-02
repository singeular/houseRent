package com.kgc.houserent.controller;

import com.kgc.houserent.entity.Street;
import com.kgc.houserent.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StreetController {

    @Autowired
    private StreetService streetService;
    @RequestMapping("/showStreet")
    @ResponseBody
    public List<Street> selectStreetByID(Integer did){
        List<Street> streetList=streetService.selectStreetByID(did);
        return streetList;
    }
}
