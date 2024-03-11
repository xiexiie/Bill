package com.xieyunjie.sys.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;
    private Integer id;
    private String username;
    private String loginname;
    private String pwd;
    private String sex;
}
