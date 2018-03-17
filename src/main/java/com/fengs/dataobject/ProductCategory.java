package com.fengs.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by fengs on 2018/1/19.\
 * 类目表对应实体
 */
@Entity
@DynamicUpdate//自动更新注解
@Data
public class ProductCategory {
    ProductCategory(){

    }
    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer  categoryType;
    private Date  createTime;
    private Date  updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
