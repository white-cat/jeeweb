package cn.jeeweb.modules.task.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;

/**
 * @Title: 任务
 * @Description: 任务
 * @author jeeweb
 * @date 2017-05-09 23:22:51
 * @version V1.0
 *
 */
@Entity
@Table(name = "task_schedule_job")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class ScheduleJob extends AbstractEntity<String> {

	/** cron表达式 */
	@Column(name = "cron_expression", nullable = false, length = 255, scale = 0)
	private String cronExpression;
	/** 任务调用的方法名 */
	@Column(name = "method_name", nullable = false, length = 255, scale = 0)
	private String methodName;
	/** 任务是否有状态 */
	@Column(name = "is_concurrent", nullable = true, length = 255, scale = 0)
	private String isConcurrent;
	/** 任务描述 */
	@Column(name = "description", nullable = true, length = 255, scale = 0)
	private String description;
	/** 任务主键 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32, scale = 0)
	private String id;
	/** 更新者 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_by")
	private User updateBy;
	/** 任务执行时调用哪个类的方法 包名+类名 */
	@Column(name = "bean_class", nullable = true, length = 255, scale = 0)
	private String beanClass;
	/** 创建时间 */
	@Column(name = "create_date", nullable = true, length = 19, scale = 0)
	private Date createDate;
	/** 任务状态 */
	@Column(name = "job_status", nullable = true, length = 255, scale = 0)
	private String jobStatus;
	/** 任务分组 */
	@Column(name = "job_group", nullable = true, length = 255, scale = 0)
	private String jobGroup;
	/** 更新时间 */
	@Column(name = "update_date", nullable = true, length = 19, scale = 0)
	private Date updateDate;
	/** 创建者 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	private User createBy;
	/** Spring bean */
	@Column(name = "spring_bean", nullable = true, length = 255, scale = 0)
	private String springBean;
	/** 任务名 */
	@Column(name = "job_name", nullable = true, length = 255, scale = 0)
	private String jobName;

	/**
	 * 获取 cronExpression
	 * 
	 * @return: String cron表达式
	 */
	public String getCronExpression() {
		return this.cronExpression;
	}

	/**
	 * 设置 cronExpression
	 * 
	 * @param: cronExpression
	 *             cron表达式
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 获取 methodName
	 * 
	 * @return: String 任务调用的方法名
	 */
	public String getMethodName() {
		return this.methodName;
	}

	/**
	 * 设置 methodName
	 * 
	 * @param: methodName
	 *             任务调用的方法名
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 获取 isConcurrent
	 * 
	 * @return: String 任务是否有状态
	 */
	public String getIsConcurrent() {
		return this.isConcurrent;
	}

	/**
	 * 设置 isConcurrent
	 * 
	 * @param: isConcurrent
	 *             任务是否有状态
	 */
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	/**
	 * 获取 description
	 * 
	 * @return: String 任务描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置 description
	 * 
	 * @param: description
	 *             任务描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取 id
	 * 
	 * @return: String 任务主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             任务主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 updateBy
	 * 
	 * @return: String 更新者
	 */
	public User getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * 设置 updateBy
	 * 
	 * @param: updateBy
	 *             更新者
	 */
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 获取 beanClass
	 * 
	 * @return: String 任务执行时调用哪个类的方法 包名+类名
	 */
	public String getBeanClass() {
		return this.beanClass;
	}

	/**
	 * 设置 beanClass
	 * 
	 * @param: beanClass
	 *             任务执行时调用哪个类的方法 包名+类名
	 */
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * 获取 createDate
	 * 
	 * @return: Date 创建时间
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置 createDate
	 * 
	 * @param: createDate
	 *             创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取 jobStatus
	 * 
	 * @return: String 任务状态
	 */
	public String getJobStatus() {
		return this.jobStatus;
	}

	/**
	 * 设置 jobStatus
	 * 
	 * @param: jobStatus
	 *             任务状态
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * 获取 jobGroup
	 * 
	 * @return: String 任务分组
	 */
	public String getJobGroup() {
		return this.jobGroup;
	}

	/**
	 * 设置 jobGroup
	 * 
	 * @param: jobGroup
	 *             任务分组
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 获取 updateDate
	 * 
	 * @return: Date 更新时间
	 */
	public Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 设置 updateDate
	 * 
	 * @param: updateDate
	 *             更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 获取 createBy
	 * 
	 * @return: String 创建者
	 */
	public User getCreateBy() {
		return this.createBy;
	}

	/**
	 * 设置 createBy
	 * 
	 * @param: createBy
	 *             创建者
	 */
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取 springBean
	 * 
	 * @return: String Spring bean
	 */
	public String getSpringBean() {
		return this.springBean;
	}

	/**
	 * 设置 springBean
	 * 
	 * @param: springBean
	 *             Spring bean
	 */
	public void setSpringBean(String springBean) {
		this.springBean = springBean;
	}

	/**
	 * 获取 jobName
	 * 
	 * @return: String 任务名
	 */
	public String getJobName() {
		return this.jobName;
	}

	/**
	 * 设置 jobName
	 * 
	 * @param: jobName
	 *             任务名
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
