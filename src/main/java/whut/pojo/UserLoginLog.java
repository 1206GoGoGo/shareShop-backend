package whut.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户登陆日志
 * @author wangql
 *
 */
public class UserLoginLog implements Serializable {
    private Integer logId;  //登录日志ID

    private Date loginTime;  //用户登录时间

    private String loginIp;  //登录IP

    private Byte loginType;  //是否登录成功

    private Integer userId;  //登录用户ID

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Byte getLoginType() {
        return loginType;
    }

    public void setLoginType(Byte loginType) {
        this.loginType = loginType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}