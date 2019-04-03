package whut.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品收藏
 * @author wangql
 *
 */
public class UserCollect implements Serializable{
    private Integer collectId; //收藏ID

    private Integer userId;  //用户id

    private Integer productId;  //商品id

    private Date collectTime;  //收藏时间

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}