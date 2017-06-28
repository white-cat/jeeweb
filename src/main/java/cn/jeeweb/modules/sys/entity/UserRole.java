package cn.jeeweb.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.AbstractEntity;

import java.lang.String;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2017-02-16 14:19:35
 * @version V1.0
 *
 */
@Entity
@Table(name = "sys_user_role", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class UserRole extends AbstractEntity<String> {

	/** 编号 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	/** 用户编号 */
	@Column(name = "user_id", nullable = false, length = 32)
	private String userId;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
	private User user;
	/** 角色编号 */
	@Column(name = "role_id", nullable = false, length = 32)
	private String roleId;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false, updatable = false, insertable = false)
	private Role role;

	/**
	 * 获取 id
	 * 
	 * @return: String 编号
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
