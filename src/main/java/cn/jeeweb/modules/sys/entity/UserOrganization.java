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
@Table(name = "sys_user_organization", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class UserOrganization extends AbstractEntity<String> {

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
	/** 部门编号 */
	@Column(name = "organization_id", nullable = false, length = 32)
	private String organizationId;
	@ManyToOne
	@JoinColumn(name = "organization_id", nullable = false, updatable = false, insertable = false)
	private Organization organization;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
