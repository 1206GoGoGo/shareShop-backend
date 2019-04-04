package whut.pojo;

import java.io.Serializable;

/**
 * 州表
 * @author wangql
 *
 */
public class State implements Serializable{
    private Integer stateId;//州id

    private String state;//州名称

    private String abbreviation;//州名称缩写

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }
}