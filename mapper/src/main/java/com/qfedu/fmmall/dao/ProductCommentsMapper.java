package com.qfedu.fmmall.dao;

import com.qfdu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.ProductComments;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentsMapper extends GeneralDao<ProductComments> {
    //商品IDproductID start其实索引 limit查询条数
    public List<ProductCommentsVO> selectCommontsByProductId(@Param("productId") String productId, @Param("start")int start,@Param("limit") int limit);
}