package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *   订单主表
 * @author wangql
 *
 */
public class OrderMaster implements Serializable{
    private Integer orderId;  //订单id

    private Long orderNumber;  //订单号

    private Integer userId;  //下单用户id

    private String consigneeName;  //收货人姓名

    private String consigneePhone;  //收货人联系电话

    private Integer postalCode;  //收货邮政编码

    private String state;  //收货人所在州

    private String city;  //收货人所在市

    private String firstAddr; //收货人第一地址

    private String secondAddr; //收货人第二地址

    private Byte paymentMode;  //支付方式

    private BigDecimal orderMoeny;  //订单金额

    private BigDecimal discountMoney;  //优惠金额

    private BigDecimal shippingMoney;  //运费

    private BigDecimal paymentMoney;  //支付金额

    private String expressNumber;  //快递号

    private Date createTime;   //下单时间

    private Date payTime;  //支付时间

    private Date shippingTime;  //送货时间

    private Date receiveTime;  //收货时间

    private Byte orderStatus; //订单状态
    
    private List<OrderDetail> orderDetails; //订单中的商品

    public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone == null ? null : consigneePhone.trim();
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
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

    public Byte getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Byte paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getOrderMoeny() {
        return orderMoeny;
    }

    public void setOrderMoeny(BigDecimal orderMoeny) {
        this.orderMoeny = orderMoeny;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getShippingMoney() {
        return shippingMoney;
    }

    public void setShippingMoney(BigDecimal shippingMoney) {
        this.shippingMoney = shippingMoney;
    }

    public BigDecimal getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(BigDecimal paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber == null ? null : expressNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }
}