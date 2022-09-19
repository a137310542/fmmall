package com.qfedu.controler;

import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.vo.ResultVo;
import com.qfedu.fmmall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
@Api(value="提供用户的登录和注册接口",tags="用户管理")
@CrossOrigin
public class UserControl {
    @Resource
    private UserService userService;
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name="username",value = "用户登录账号",required = true),
            @ApiImplicitParam(dataType = "String",name="password",value = "用户登录密码",required = true)
    })
   @GetMapping("/login")
    public ResultVo login(@RequestParam("username") String name,
                          @RequestParam(value="password"
                          ) String pwd){
        ResultVo resultvo= userService.checkLogin(name,pwd);
        return resultvo;
    }
    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
    @ApiImplicitParam(dataType = "String",name="username",value = "用户注册账号",required = true),
    @ApiImplicitParam(dataType = "String",name="password",value = "用户注册密码",required = true)
    })
   @PostMapping("/regist")
    public ResultVo regist(@RequestBody Users user){
      ResultVo resultvo =userService.userResgit(user.getUsername(),user.getPassword());
        return  resultvo;
    }
}
