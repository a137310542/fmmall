package com.qfedu.controler;

import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/index")
@Api(value = "提供首页数据显示所需要的接口",tags = "首页管理")
public class IndexImgController {
    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/indeximg")
    @ApiOperation("首页轮播图的接口")
    public ResultVo listIndexImgs(){

        return  indexImgService.listIndexImgs();
    }
    @GetMapping("/category-list")
    @ApiOperation("查询商品类的接口")
    public ResultVo listCategory(){
        return categoryService.listCategoryies();
    }
    @GetMapping("/list-Recommends")
    @ApiOperation("新品推荐商品接口")
    public ResultVo listRecommendProducts(){
        return productService.listRecommendProducts();
    }

    @GetMapping("/list-category-recommends")
    @ApiOperation("分类推荐商品接口")
    public ResultVo listRecommendProductsCategory(){
  return  categoryService.listFisrstLevelCategories();
    }
}
