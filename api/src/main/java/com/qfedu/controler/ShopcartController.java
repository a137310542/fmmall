package com.qfedu.controler;

import com.qfedu.fmmall.utils.Base64Utils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口",tags = "购物车管理")
public class ShopcartController {

    @GetMapping("/list")
    @ApiImplicitParam(dataType = "String",name="token",value = "授权令牌",required = true)
    public ResultVo listCarts(String token){
        //1.获取token
        //2.校验token
        String decode=Base64Utils.decode(token);
        if(token==null){
            return new ResultVo(ResStatus.no,"请先登录",null);
        }else{
            JwtParser parser= Jwts.parser();
            parser.setSigningKey("yyp666");//解析token的喝生成时候一样
           try {
               //如果token(密码正确,有效期类)则正常执行否则抛出异常
               Jws<Claims> claimsJws = parser.parseClaimsJws(token);
               Claims body=claimsJws.getBody(); //获取token中用户数据
                String subject=body.getSubject(); //获取生成token设置的subject
               Object v1=body.get("key1",String.class);//获取存储的时候map的值
               return new ResultVo(ResStatus.ok,"success",null);
           }catch (Exception e){
               return new ResultVo(ResStatus.no,"登录过期请重新登录",null);
           }





        }
        }

}
