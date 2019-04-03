package whut.pojo;

import java.io.Serializable;

/**
 * 城市表
 * @author wangql
 *
 */
public class City implements Serializable{
    private Integer cityId; //市id

    private String city;//城市名称

    private Integer stateId;//州id

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
}