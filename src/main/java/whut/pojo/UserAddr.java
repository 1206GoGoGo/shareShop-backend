package whut.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址
 * @author wangql
 *
 */
public class UserAddr implements Serializable{
    private Integer userAddrId;  //用户地址ID

    private Integer userId;  //用户id

    private Integer postalCode;  //邮编
    
    private String phone; //收货人电话

    private String state;  //地区所在州

    private String city;  //地区所在市

    private String firstAddr; //第一地址

    private String secondAddr;  //第二地址

    private Byte isDefault;  //是否默认

    private Date modifiedTime;  //最后修改时间

    public Integer getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Integer userAddrId) {
        this.userAddrId = userAddrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFirstAddr() {
        return firstAddr;
    }

    public void setFirstAddr(String firstAddr) {
        this.firstAddr = firstAddr == null ? null : firstAddr.trim();
    }

    public String getSecondAddr() {
        return secondAddr;
    }

    public void setSecondAddr(String secondAddr) {
        this.secondAddr = secondAddr == null ? null : secondAddr.trim();
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}