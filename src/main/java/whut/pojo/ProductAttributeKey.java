package whut.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品属性key表
 * @author wangql
 *
 */
public class ProductAttributeKey implements Serializable{
    private Integer keyId; //属性keyID

    private Integer categoryId;//商品分类id

    private String attrName;//属性名称

    private Byte nameSort;//属性名称排序

    private Date createTime;//创建时间

    private Date updateTime;//修改时间
    
    private Byte status;//状态

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public Byte getNameSort() {
        return nameSort;
    }

    public void setNameSort(Byte nameSort) {
        this.nameSort = nameSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}