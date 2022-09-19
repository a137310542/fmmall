package com.qfedu;

import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {
    @Resource
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
@Autowired
private ProductCommentsMapper productCommentsMapper;
    @Test
    void contextLoads() {
        List<categoryVO> categoryVOS=categoryMapper.selectAllCategories2(0);
        for(categoryVO c1:categoryVOS){
            System.out.println(c1);
            for(categoryVO c2:c1.getCategories()){
                System.out.println("\t"+c2);
                for(categoryVO c3:c2.getCategories()){
                    System.out.println("\t\t"+c3);
                }
            }
        }
    }
 @Test
    public void testRecommand(){
        List<ProductVO> productVOS=productMapper.selectRecommendProducts();
        for(ProductVO p:productVOS){
            System.out.println(p);
        }
}
@Test
    public void testSelectFirstLevelCategory() {
    List<categoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
    for (categoryVO c : categoryVOS) {
        System.out.println(c);
    }
}
   // @Test
   // public void testSelectFirstLLevelCategory() {
        //List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId("3");
    //    for (ProductCommentsVO c : productCommentsVOS) {
      //      System.out.println(c);
       // }
    }


