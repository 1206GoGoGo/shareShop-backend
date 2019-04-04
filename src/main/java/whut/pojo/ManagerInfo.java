package whut.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员信息
 * @author wangql
 *
 */
public class ManagerInfo implements Serializable{
    private Integer managerInfoId;  //管理员信息id
    
    private Integer userId;//登录用户id

    private String name;  //管理员真实姓名

    private Byte identityCardType;  //证件类型

    private String identityCardNo; //证件号码

    private String phoneNumber;  //手机号

    private String email;  //邮箱

    private String gender; //性别

    private Date birthday; //管理员生日

    private Date registerTime;  //注册时间
    
    private UserLogin userLogin; //用户登录对象

    public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

    public Integer getManagerInfoId() {
        return managerInfoId;
    }

    public void setManagerInfoId(Integer managerInfoId) {
        this.managerInfoId = managerInfoId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIdentityCardType() {
        return identityCardType;
    }

    public void setIdentityCardType(Byte identityCardType) {
        this.identityCardType = identityCardType;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo == null ? null : identityCardNo.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}