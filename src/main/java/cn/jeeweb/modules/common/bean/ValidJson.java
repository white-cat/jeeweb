package cn.jeeweb.modules.common.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValidJson implements Serializable {
	private Boolean valid = false;

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
