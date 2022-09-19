package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductImgMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.dao.ProductParamsMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
@Service
public class ProductServiceImpl  implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;
    @Override
    public ResultVo listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        ResultVo resultVo = new ResultVo(ResStatus.ok, "success", productVOS);
        return resultVo;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo getProductBasicInfo(String productId) {
        //1.商品信息
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("productStatus", 1);
        List<Product> list = productMapper.selectByExample(example);
        if (list.size() > 0) {
            //2.商品图片
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1=example1.createCriteria();
            criteria1.andEqualTo("itemId",productId);
            List list1 = productImgMapper.selectByExample(example1);
            //3.商品套餐
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId",productId);
            criteria2.andEqualTo("status",1);
            List list2 = productSkuMapper.selectByExample(example2);
            HashMap<String,Object> basicInfo=new HashMap<>();
            basicInfo.put("product",list.get(0));
            basicInfo.put("productImgs",list1);
            basicInfo.put("productSkus",list2);
            return new ResultVo(ResStatus.ok,"success",basicInfo);

        } else {
            return new ResultVo(ResStatus.no, "查询商品不存在！", null);
        }
    }

    @Override
    public ResultVo getProductParamsById(String productId) {
        Example example = new Example(ProductParams.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
       List<ProductParams> list= productParamsMapper.selectByExample(example);
       if(list.size()>0){
           return new ResultVo(ResStatus.ok,"success",list.get(0));
       }else {
           return new ResultVo(ResStatus.no,"此商品为三五产品",null);
       }

    }

}