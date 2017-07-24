package cn.jeeweb.modules.sys.entity;

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
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.jeeweb.core.common.entity.AbstractEntity;
import java.lang.String;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "sys_log", schema = "")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Log extends AbstractEntity<String> {

	// 日志类型（1：接入日志；2：错误日志）
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";

	/** 编号 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 64)
	private String id;
	/** 用户代理 */
	@Column(name = "user_agent", nullable = true, length = 255)
	private String userAgent;
	/** 请求URI */
	@Column(name = "request_uri", nullable = true, length = 255)
	private String requestUri;
	/** 日志标题 */
	@Column(name = "title", nullable = true, length = 255)
	private String title;
	/** 日志类型 */
	@Column(name = "type", nullable = true, length = 1)
	private String type;
	/** 操作方式 */
	@Column(name = "method", nullable = true, length = 5)
	private String method;
	/** 异常信息 */
	@Column(name = "exception", nullable = true, length = 65535)
	private String exception;
	/** 日志内容 */
	@Column(name = "content", nullable = true, length = 1000)
	private String content;
	/** 操作方式 */
	@Column(name = "logtype", nullable = true, length = 4)
	private String logtype;
	/** 创建时间 */
	@Column(name = "create_date", nullable = true, length = 19)
	private Date createDate;
	/** 操作提交的数据 */
	@Column(name = "params", nullable = true, length = 65535)
	@Type(type = "cn.jeeweb.core.repository.hibernate.type.ObjectSerializeUserType")
	private Map<String, String[]> params;
	/** 操作IP地址 */
	@Column(name = "remote_addr", nullable = true, length = 255)
	private String remoteAddr;
	/** 创建者 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	@JsonIgnore
	private User createBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

}
