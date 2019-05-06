package whut.utils;

/**
 * 请求返回数据格式封装类
 * @author chen cheng
 *
 */
public class ResponseData {
	private Integer code;
	private String msg;
	private Object data;
	
	public ResponseData(Object data){
		this.code = 200;
		this.msg = "success";
		this.data = data;
	}
	
	public ResponseData(Integer code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResponseData(Integer code, String msg, Object data , int numFound){
		this.code = code;
		this.msg = msg;	
		this.data = new THEDATA(numFound, data);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

class THEDATA {
	private Integer numFound;
	private Object indata;
	
	public THEDATA(Integer numFound, Object indata){
		this.numFound = numFound;
		this.indata = indata;
	}

	public Integer getNumFound() {
		return numFound;
	}

	public void setNumFound(Integer numFound) {
		this.numFound = numFound;
	}

	public Object getIndata() {
		return indata;
	}

	public void setIndata(Object indata) {
		this.indata = indata;
	}
}
