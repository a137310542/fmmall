package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVo;

public interface UserService {
    //用户登录
public ResultVo checkLogin(String name, String pwd);
//用户注册
public ResultVo userResgit(String name, String pwd);

}
