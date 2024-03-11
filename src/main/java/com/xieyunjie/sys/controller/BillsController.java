package com.xieyunjie.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xieyunjie.common.DataGridView;
import com.xieyunjie.common.ResultObj;
import com.xieyunjie.sys.domain.Bills;
import com.xieyunjie.sys.domain.Billtype;
import com.xieyunjie.sys.service.BillsService;
import com.xieyunjie.sys.service.BilltypeService;
import com.xieyunjie.sys.vo.BillsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    private BillsService billsService;

    @Autowired
    private BilltypeService billtypeService;

    /**
     * 跳转到系统主页
     */
    @RequestMapping("toBillsList")
    public String toBillsList(){
        return "list";
    }

    /**
     *加载账单数据
     */
    @RequestMapping("loadAllBills")
    @ResponseBody
    public DataGridView loadAllBills(BillsVo billsVo){
        //这里使用分页插件
        IPage<Bills> page=new Page<>(billsVo.getPage(),billsVo.getLimit());
        //查询条件
        QueryWrapper<Bills> queryWrapper=new QueryWrapper<>();
        //等于
        queryWrapper.eq(null!=billsVo.getTypeid()&&billsVo.getTypeid()!=0,"typeid",billsVo.getTypeid());
        //大于
        queryWrapper.ge(billsVo.getStartDate()!=null,"billtime",billsVo.getStartDate());
        //小于
        queryWrapper.le(billsVo.getEndDate()!=null,"billtime",billsVo.getEndDate());
        //排序（降序）
        queryWrapper.orderByDesc("billtime");
        billsService.page(page,queryWrapper);

        List<Bills> records=page.getRecords();
        for (Bills bills:records){
            Billtype billtype=this.billtypeService.getById(bills.getTypeid());
            bills.setTypeName(billtype.getName());
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加账单
     */
    @RequestMapping("addBills")
    @ResponseBody
    public ResultObj addBills(BillsVo billsVo){
        try {
            this.billsService.save(billsVo);
            return new ResultObj(200, "录入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(-1, "录入失败");
        }
    }
}
