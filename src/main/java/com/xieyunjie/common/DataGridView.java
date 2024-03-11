package com.xieyunjie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Long code=0L;//状态码
    private String msg="";//消息
    private Long count;//数据的条数
    private Object data;//数据
    public DataGridView(Long count,Object data){
        super();
        this.count=count;
        this.data=data;
    }
}
