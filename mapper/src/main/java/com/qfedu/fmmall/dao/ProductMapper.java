package com.qfedu.fmmall.dao;

import com.qfdu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.Product;
import com.qfedu.fmmall.entity.ProductVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapper extends GeneralDao<Product> {
    public List<ProductVO> selectRecommendProducts();
    //查询一级类别下销量最高的商品
    public List<ProductVO> selectTop6ByCategory(int cid);

}