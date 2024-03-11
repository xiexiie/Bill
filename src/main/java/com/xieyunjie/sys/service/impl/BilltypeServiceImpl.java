package com.xieyunjie.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xieyunjie.sys.domain.Billtype;
import com.xieyunjie.sys.mapper.BilltypeMapper;
import com.xieyunjie.sys.service.BilltypeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BilltypeServiceImpl extends ServiceImpl<BilltypeMapper, Billtype> implements BilltypeService {


    @Override
    public Billtype getById(Serializable id){
        return super.getById(id);
    }
}
