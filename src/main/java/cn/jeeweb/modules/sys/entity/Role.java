package cn.jeeweb.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


import cn.jeeweb.core.common.entity.DataEntity;

/**
 * @Title: 角色实体
 * @Description: 角色实体
 * @author 王存见
 * @date 2016-12-03 21:33:15
 * @version V1.0
 *
 */
@Entity
@Table(name = "sys_role", schema = "")
@SuppressWarnings("serial")
public class Role extends DataEntity<String> {
	private String id;

	private String name;// 角色名称
	private String code;// 角色编码
	private String isSys;// 是否系统数据
	private String usable;// 是否可用

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "is_sys")
	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	@Column(name = "usable")
	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

}
