package com.qfedu.fmmall.dao;

import com.qfdu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.Category;
import com.qfedu.fmmall.entity.categoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryMapper extends GeneralDao<Category> {
    //1.连接查询
    public List<categoryVO> selectAllCategories();
   //2.子查询 根据parentId查询子分类
   public List<categoryVO> selectAllCategories2(int parentId);
   //查询一级类别
   public List<categoryVO> selectFirstLevelCategories();
}