package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.UsersMapper;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.utils.Base64Utils;
import com.qfedu.fmmall.utils.MD5Utils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import com.qfedu.fmmall.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("UserService")
public class UserServiceimpl implements UserService {
@Resource
private UsersMapper usersMapper;

    @Override
    public ResultVo checkLogin(String name, String pwd) {
        tk.mybatis.mapper.entity.Example example=new tk.mybatis.mapper.entity.Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",name);
        List<Users> users=usersMapper.selectByExample(example);
        if(users.size()==0){
            return new ResultVo(10001,"登录失败，用户不存在",null);
        }else {
            String md5pwd=MD5Utils.md5(pwd);
            if(md5pwd.equals(users.get(0).getPassword())){
                JwtBuilder builder= Jwts.builder();
                HashMap<String,Object>map=new HashMap<>();
                map.put("key1","value1");
                map.put("key2","value2");
              String token=builder.setSubject(name)       //主题token携带的数据
                        .setIssuedAt(new Date())  //设置生成时间
                        .setId(users.get(0).getUserId()+"")
                        .setClaims(map)           //map中
                        .setExpiration(new Date(System.currentTimeMillis()+1*60*60*1000))
                         .signWith(SignatureAlgorithm.HS256,"yyp666").compact();//设置token过期时间
                return new ResultVo(ResStatus.ok,token,users);
            }else {
                return new ResultVo(ResStatus.no,"登录失败",null);
            }
        }
    }

    @Transactional
    public ResultVo userResgit(String name, String pwd) {
        //1.根据用户查询，这个用户是否被注册
       synchronized (this){
           tk.mybatis.mapper.entity.Example example=new tk.mybatis.mapper.entity.Example(Users.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("username",name);
           List<Users> users=usersMapper.selectByExample(example);
           if(users.size()==0){
            String md5Pwd= MD5Utils.md5(pwd);
           Users user=new Users();
            user.setUsername(name);
            user.setPassword(md5Pwd);
            user.setUserRegtime(new Date());
            user.setUserModtime(new Date());
            user.setUserImg("img/default.png");
            int i=usersMapper.insertUseGeneratedKeys(user);
            if(i>0){
                return new ResultVo(ResStatus.ok,"注册成功",user);
            }else {
                return new ResultVo(ResStatus.no,"注册失败",null);
            }
        }else{
            return  new ResultVo(ResStatus.no,"用户名已经注册",null);

        }}

    }
}
