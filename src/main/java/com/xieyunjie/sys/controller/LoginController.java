package com.xieyunjie.sys.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xieyunjie.common.ResultObj;
import com.xieyunjie.sys.domain.User;
import com.xieyunjie.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆控制
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    //判断是否登录成功
    @RequestMapping("login")
    @ResponseBody
    public ResultObj login(String loginname,String pwd,String code,HttpSession session) {
        //获取状态码
        Object codeSession = session.getAttribute("code");
        //状态码不为空而且相等进行判断账号和密码
        if(code!=null&&code.equals(codeSession)) {
            //从queryWrapper对象里面取出账号和密码
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("loginname", loginname);
            queryWrapper.eq("pwd", pwd);
            //从数据库里面进行查询
            User user = userService.getOne(queryWrapper);
            if(null!=user) {
                session.setAttribute("user", user);
                return new ResultObj(200, "登陆成功");
            }else {
                return new ResultObj(-1, "用户名或密码不正确");
            }
        }else {
            return new ResultObj(-1, "验证码错误");
        }

    }

    //生成验证码
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse resp,HttpSession session) throws IOException {
        //HuTool插件，直接创建一个验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        //得到code
        String code = captcha.getCode();
        System.out.println(code);
        //放到session
        session.setAttribute("code", code);
        //得到他的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }
}

