package com.fengs.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengs.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by fengs on 2018/1/28.
 * 商品名称和类目
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categorytype;
    @JsonProperty("foods")
    private List<ProductInfoVo>  productInfoVoList;

}
