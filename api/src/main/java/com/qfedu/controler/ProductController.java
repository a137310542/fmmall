package com.qfedu.controler;

import com.qfedu.fmmall.service.ProductCommentsService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口",tags = "商品管理")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCommentsService productCommentsService;

    @ApiOperation("商品基本信息查询接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVo getProductParams(@PathVariable("pid") String pid){
        return productService.getProductBasicInfo(pid);
    }
    @ApiOperation("商品参数信息查询接口")
    @GetMapping("/detail-params/{pid}")
    public ResultVo getProductBasicInfo(@PathVariable("pid") String pid){
        return productService.getProductParamsById(pid);
    }
   @ApiOperation("商品评论信息接口")
   @GetMapping("/detail-commonts/{pid}")
    public ResultVo getProductComments(@PathVariable("pid") String pid,int pageNum,int limit){
       return productCommentsService.listCommentsByProductId(pid,pageNum,limit);
   }
}
