package com.qfedu.fmmall.service;

        import com.qfedu.fmmall.vo.ResultVo;

public interface ProductService {
    public ResultVo listRecommendProducts();
    public ResultVo getProductBasicInfo(String productId);
    public ResultVo getProductParamsById(String productId);
}
