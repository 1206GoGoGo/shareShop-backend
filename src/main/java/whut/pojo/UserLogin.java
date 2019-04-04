package whut.pojo;

import java.io.Serializable;

/**
 * 用户登录
 * @author wangql
 *
 */
public class UserLogin implements Serializable{
    private Integer userId;  //用户ID

    private String username;  //用户登录名

    private String password;  //密码

    private Byte status;  //用户状态

    private Integer level;  //用户级别

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}