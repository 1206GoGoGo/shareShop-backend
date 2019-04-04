package whut.pojo;

import java.io.Serializable;

/**
 * 商品分类
 * @author wangql
 *
 */
public class ProductCategory implements Serializable{
    private Integer categoryId;  //分类ID

    private String categoryName;  //分类名称

    private String categoryCode;  //分类编号

    private Integer parentId;  //父分类ID

    private Byte categoryLevel;  //分类层级

    private Byte categoryStatus;  //分类状态
    
    private String picUrl; //商品分类图片

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Byte categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Byte getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Byte categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
    
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
}