package com.fengs.buyerController;

import com.fengs.Vo.ProductInfoVo;
import com.fengs.Vo.ProductVO;
import com.fengs.Vo.Result;
import com.fengs.dataobject.ProductCategory;
import com.fengs.dataobject.ProductInfo;
import com.fengs.service.CategoryService;
import com.fengs.service.ProductInfoService;
import com.fengs.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fengs on 2018/1/28.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;//注入
    @GetMapping("/list")
    public Result list()
    {
        //获取所有上架商品
       List<ProductInfo> productInfoList= productInfoService.findUpAll();
       //查询类目
        List<Integer> catgorylist=new ArrayList<>();
        for(ProductInfo productInfo:productInfoList)//把prouctInfoList遍历
        {
            catgorylist.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(catgorylist);
        //数据拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList)
        {
            ProductVO productVO=new ProductVO();
            productVO.setCategorytype(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
           // productVO.setProductInfoVoList(productInfoList);
            List<ProductInfoVo> productInfoVoList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList)
            {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                  //  productInfoVo.setProductName(productInfo.getProductName());
                  //  productInfoVo.setProductDescription(productInfo.getProductDescription());
                  //  productInfoVo.setProductPrice(productInfo.getProductPrice());
                   // productInfoVo.setProductIcon(productInfo.getProductIcon());
                   // productInfoVo.setProductId(productInfo.getProductId());
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }

            productVO.setProductInfoVoList(productInfoVoList);
            productVOList.add(productVO);
        }
       // for(P)
        //api最外层对象
        //Result result=new Result();
      //  ProductVO productVO=new ProductVO();//第2层
     //   ProductInfoVo productInfoVo=new ProductInfoVo();//第3层
       // productVO.setProductInfoVoList(Arrays.asList(productInfoVo));//将第3层对象插入第2层对象
       // result.setData(Arrays.asList(productVO)); //将第2层对象插入第一层对象中
      //  result.setData(productVOList);
      // result.setCode(0);
      //  result.setMsg("成功");
        return ResultVoUtil.sucess(productVOList);


    }
}
