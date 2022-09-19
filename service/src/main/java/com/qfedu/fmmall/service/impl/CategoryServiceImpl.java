package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.entity.categoryVO;
import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
//查询分类列表
    public ResultVo listCategoryies(){
      List<categoryVO> list= categoryMapper.selectAllCategories();
        ResultVo resultVo = new ResultVo(ResStatus.ok, "success", list);
        return resultVo;
    }
//查询所有一级分类 ，同时一级分类下销量最高的6个商品
    @Override
    public ResultVo listFisrstLevelCategories() {
        List<categoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        ResultVo resultVo = new ResultVo(ResStatus.ok, "success", categoryVOS);
        return resultVo;
    }
}
