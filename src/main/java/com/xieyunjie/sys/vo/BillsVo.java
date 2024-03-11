package com.xieyunjie.sys.vo;

import com.xieyunjie.sys.domain.Bills;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BillsVo extends Bills {

    private static final long serialVersionUID=1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;//开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;//结束时间

    private Integer page=1;//默认第一页
    private Integer limit=10;//一页里面包含的条数
}
