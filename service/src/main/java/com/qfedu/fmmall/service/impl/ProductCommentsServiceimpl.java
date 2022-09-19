package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.entity.Product;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.service.ProductCommentsService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class ProductCommentsServiceimpl implements ProductCommentsService {

    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Override
    public ResultVo listCommentsByProductId(String productId,int pageNum,int limit) {
       // List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId(productId);
        //1.根据商品id查询总记录数
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        int count=productCommentsMapper.selectCountByExample(example);
        //2.计算总页数(必须知道每一页显示多少条 pagesize)
        int start=(pageNum-1)*limit;
        int pagecount = count%limit==0? count/limit : count/limit+1;
        //3.查询当前页的数据（因为评论中需要用户信息因此需要连表查询）
        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId(productId,start,limit);
        ResultVo resultVo = new ResultVo(ResStatus.ok, "success", new PageHelper<ProductCommentsVO>(count,pagecount,productCommentsVOS));
        return resultVo;
    }
}
