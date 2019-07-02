package com.kgc.houserent.service.impl;

import com.kgc.houserent.entity.Type;
import com.kgc.houserent.entity.TypeExample;
import com.kgc.houserent.mapper.TypeMapper;
import com.kgc.houserent.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> selectAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
