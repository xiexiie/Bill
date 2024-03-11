package com.xieyunjie.sys.controller;

import com.xieyunjie.common.DataGridView;
import com.xieyunjie.sys.service.BilltypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/billtype")
public class BilltypeController {
	
	@Autowired
	private BilltypeService billtypeService;

	
	@RequestMapping("loadAllBillType")
	public DataGridView loadAllBillType() {
		return new DataGridView(0L, billtypeService.list());
	}

}

