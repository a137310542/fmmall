package com.qfedu.fmmall.dao;

import com.qfdu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.ProductImg;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductImgMapper extends GeneralDao<ProductImg> {
    //根据商品id查询商品信息
    public List<ProductImg> selectProductImgByProductId(int productId);
}