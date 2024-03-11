package com.xieyunjie.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xieyunjie.sys.domain.Bills;
import com.xieyunjie.sys.mapper.BillsMapper;
import com.xieyunjie.sys.service.BillsService;
import org.springframework.stereotype.Service;

@Service
public class BillsServiceImpl extends ServiceImpl<BillsMapper, Bills> implements BillsService {
}
