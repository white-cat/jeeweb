package cn.jeeweb.core.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.core.common.entity.MarkDeleteable;
import cn.jeeweb.modules.sys.entity.User;

/**
 * 数据Entity类
 * 
 * @author 王存见
 * @version 2016-12-03
 * @param <ID>
 *            主键类型
 */
@MappedSuperclass
public abstract class DataEntity<ID> extends AbstractEntity<ID> implements MarkDeleteable {

	private static final long serialVersionUID = 1L;

	protected String remarks; // 备注

	protected User createBy; // 创建者
	protected Date createDate; // 创建日期

	protected User updateBy; // 更新者
	protected Date updateDate; // 更新日期

	protected String delFlag = DEL_FLAG_NORMAL; // 删除标记（0：正常；1：删除 ）
	protected Boolean isDelete = Boolean.FALSE;

	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_by")
	@JsonIgnore
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "del_flag", length = 1, nullable = false)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 这表数据是否需要标记删除
	 * 
	 * @param delflag
	 */
	@Transient
	public void markDelete(Boolean isDelete) {
		this.isDelete = isDelete;
		if (this.isDelete) {
			delFlag = DEL_FLAG_DELETE;
		} else {
			delFlag = DEL_FLAG_NORMAL;
		}
	}

	@Transient
	public Boolean markStatus() {
		return this.isDelete;
	}

}
