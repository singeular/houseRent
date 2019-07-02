package com.kgc.houserent.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.*;
import com.kgc.houserent.mapper.DistrictMapper;
import com.kgc.houserent.mapper.TypeMapper;
import com.kgc.houserent.service.DistrictService;
import com.kgc.houserent.service.HouseService;
import com.kgc.houserent.service.StreetService;
import com.kgc.houserent.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;


    @RequestMapping("/showTypes")
    public String SelectType(Model model){
        List<Type> typeList=typeService.selectAllType();
        List<District> districtList=districtService.selectAllDistrict();
        model.addAttribute("types",typeList);
        model.addAttribute("districts",districtList);

        return "page/fabu";
    }

    @RequestMapping("/addHouse")
    public String addHouse(House house,@RequestParam(value = "image",required = false)CommonsMultipartFile image, HttpSession session) throws Exception{
        String fileName=image.getOriginalFilename();
        String expName=fileName.substring(fileName.lastIndexOf("."));
        String addName=System.currentTimeMillis()+expName;
        File file=new File("F:\\image\\"+addName);
        image.transferTo(file);

        //保存数据库的记录  house已经接收部分表单数据
        //设置编号
        house.setId(System.currentTimeMillis()+"");

        //设置用户编号
        Users user=(Users)session.getAttribute("users");
        house.setUserId(user.getId());

        //设置审核状态 0  如果表中有默认值 可不设
        house.setIspass(0);

        //设置是否删除  0
        house.setIsdel(0);
        //设置图片路径
        house.setPath(addName);

        if(houseService.addHouse(house)>0){ //保存数据
            //调用业务
            //houseService.addHouse(house); //添加信息到数据库
            return "redirect:showUserHouse";  //跳转页面
        }
        else{
            //成功上传的图片删除
            file.delete();
            return "page/guanli";  //跳转页面
        }
    }

    @RequestMapping("/showUserHouse")
    public String selectUserHouseByID(HttpSession session,Model model,Integer page){
        Users users=(Users) session.getAttribute("users");
        PageInfo<House> pageInfo=houseService.selectAllUserHouse(users.getId(),page==null?1:page,2);
        model.addAttribute("pageInfo",pageInfo);
        return "page/guanli";
    }

    @RequestMapping("/updateHouse1")
    public String updateHouse(String id,Model model){
        List<Type> typeList=typeService.selectAllType();
        List<District> districtList=districtService.selectAllDistrict();
        House house=houseService.selectHouseAndDid(id);
        model.addAttribute("types",typeList);
        model.addAttribute("districts",districtList);
        model.addAttribute("house",house);
        return "page/updateHouse";
    }

    @RequestMapping("/updateHouse2")
    public String updateHouse(String oldImg,House house,HttpSession session,@RequestParam(value = "image",required = false)CommonsMultipartFile image) throws IOException {
        File file;
        File newFile=null;
        if(image.getOriginalFilename().equals("")||image.getOriginalFilename()==null){
            System.out.println("不修改图片");
        }else {
            file=new File("F://image//"+oldImg);
            file.delete();

            String fileName=image.getOriginalFilename();
            String expName=fileName.substring(fileName.lastIndexOf("."));
            String addName=System.currentTimeMillis()+expName;
            newFile=new File("F:\\image\\"+addName);
            image.transferTo(newFile);
            house.setPath(addName);
        }
        if(houseService.updateHouseMsg(house)<=0){
            if(newFile!=null)newFile.delete();
        }
        return "redirect:showUserHouse";
    }

    @RequestMapping("/deleteHouse")
    @ResponseBody
    public String deleteHouse(String id){
        int i=houseService.deleteHouse(id);

        return "{\"result\":"+i+"}";
    }
}
