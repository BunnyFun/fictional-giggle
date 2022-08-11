package cn.workcenter.common.response;

import cn.hutool.core.util.ObjectUtil;
import java.util.List;

public class AjaxResult<T> {

	private int status;

	private String message;

	private String info;

	private List<T> result;

	private T object;

	public AjaxResult(){
	}

	public AjaxResult(int status, String message){
		this.status = status;
		this.message = message;
	}

	public AjaxResult(int status, String message, String info) {
		this.status = status;
		this.message = message;
		if(ObjectUtil.isNotNull(info)) {
			this.info = info;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static <T> AjaxResult<T> success(T o){
		AjaxResult<T> ajaxResult = new AjaxResult<>();
		ajaxResult.setStatus(200);
		ajaxResult.setObject(o);
		return ajaxResult;
	}
}
