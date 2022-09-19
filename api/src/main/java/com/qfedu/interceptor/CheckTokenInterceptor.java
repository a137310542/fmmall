package com.qfedu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String method=request.getMethod();
                if("OPTIONS".equals(method)){
                    return true;
                }
        System.out.println("----------"+token);
        if (token == null) {
       ResultVo resultVo=new ResultVo(ResStatus.no,"请先登录!",null);
       doResponse(response,resultVo);
            return false;
        } else {
            try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey("yyp666");//解析token的喝生成时候一样
                //如果token(密码正确,有效期类)则正常执行否则抛出异常
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) {
                ResultVo resultVo=new ResultVo(ResStatus.no,"请先登录!",null);
                doResponse(response,resultVo);
            } catch (UnsupportedJwtException e) {
                ResultVo resultVo=new ResultVo(ResStatus.no,"token不合法!",null);
                doResponse(response,resultVo);
            } catch (Exception e) {
                ResultVo resultVo=new ResultVo(ResStatus.no,"请先登录!",null);
                doResponse(response,resultVo);
            }
            return false;
        }

    }
    private void doResponse(HttpServletResponse response, ResultVo resultVo) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String s= new ObjectMapper().writeValueAsString(new ResultVo(ResStatus.no,"请先登录",null));
        out.print(s);
        out.flush();
        out.close();
    }
}