package cn.jeeweb.modules.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.AbstractEntity;

/**   
 * @Title: 最后在线情况
 * @Description: 最后在线情况
 * @author jeeweb
 * @date 2017-05-15 08:18:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sys_user_last_online")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class UserLastOnlineEntity extends AbstractEntity<Long> {

	/**login_count*/
	@Column(name ="login_count",nullable=true,length=19,scale=0)
	private Long loginCount;
	/**total_online_time*/
	@Column(name ="total_online_time",nullable=true,length=19,scale=0)
	private Long totalOnlineTime;
	/**host*/
	@Column(name ="host",nullable=true,length=100,scale=0)
	private String host;
	/**user_agent*/
	@Column(name ="user_agent",nullable=true,length=200,scale=0)
	private String userAgent;
	/**system_host*/
	@Column(name ="system_host",nullable=true,length=100,scale=0)
	private String systemHost;
	/**username*/
	@Column(name ="username",nullable=true,length=100,scale=0)
	private String username;
	/**last_stop_timestamp*/
	@Column(name ="last_stop_timestamp",nullable=false,length=19,scale=0)
	private Date lastStopTimestamp;
	/**last_login_timestamp*/
	@Column(name ="last_login_timestamp",nullable=false,length=19,scale=0)
	private Date lastLoginTimestamp;
	/**uid*/
	@Column(name ="uid",nullable=true,length=100,scale=0)
	private String uid;
	/**id*/
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=19,scale=0)
	private Long id;
	/**user_id*/
	@Column(name ="user_id",nullable=true,length=19,scale=0)
	private Long userId;
	/**
	 * 获取  loginCount
	 *@return: Long  login_count
	 */
	public Long getLoginCount(){
		return this.loginCount;
	}

	/**
	 * 设置  loginCount
	 *@param: loginCount  login_count
	 */
	public void setLoginCount(Long loginCount){
		this.loginCount = loginCount;
	}
	/**
	 * 获取  totalOnlineTime
	 *@return: Long  total_online_time
	 */
	public Long getTotalOnlineTime(){
		return this.totalOnlineTime;
	}

	/**
	 * 设置  totalOnlineTime
	 *@param: totalOnlineTime  total_online_time
	 */
	public void setTotalOnlineTime(Long totalOnlineTime){
		this.totalOnlineTime = totalOnlineTime;
	}
	/**
	 * 获取  host
	 *@return: String  host
	 */
	public String getHost(){
		return this.host;
	}

	/**
	 * 设置  host
	 *@param: host  host
	 */
	public void setHost(String host){
		this.host = host;
	}
	/**
	 * 获取  userAgent
	 *@return: String  user_agent
	 */
	public String getUserAgent(){
		return this.userAgent;
	}

	/**
	 * 设置  userAgent
	 *@param: userAgent  user_agent
	 */
	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}
	/**
	 * 获取  systemHost
	 *@return: String  system_host
	 */
	public String getSystemHost(){
		return this.systemHost;
	}

	/**
	 * 设置  systemHost
	 *@param: systemHost  system_host
	 */
	public void setSystemHost(String systemHost){
		this.systemHost = systemHost;
	}
	/**
	 * 获取  username
	 *@return: String  username
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * 设置  username
	 *@param: username  username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	/**
	 * 获取  lastStopTimestamp
	 *@return: Date  last_stop_timestamp
	 */
	public Date getLastStopTimestamp(){
		return this.lastStopTimestamp;
	}

	/**
	 * 设置  lastStopTimestamp
	 *@param: lastStopTimestamp  last_stop_timestamp
	 */
	public void setLastStopTimestamp(Date lastStopTimestamp){
		this.lastStopTimestamp = lastStopTimestamp;
	}
	/**
	 * 获取  lastLoginTimestamp
	 *@return: Date  last_login_timestamp
	 */
	public Date getLastLoginTimestamp(){
		return this.lastLoginTimestamp;
	}

	/**
	 * 设置  lastLoginTimestamp
	 *@param: lastLoginTimestamp  last_login_timestamp
	 */
	public void setLastLoginTimestamp(Date lastLoginTimestamp){
		this.lastLoginTimestamp = lastLoginTimestamp;
	}
	/**
	 * 获取  uid
	 *@return: String  uid
	 */
	public String getUid(){
		return this.uid;
	}

	/**
	 * 设置  uid
	 *@param: uid  uid
	 */
	public void setUid(String uid){
		this.uid = uid;
	}
	/**
	 * 获取  id
	 *@return: Long  id
	 */
	public Long getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 获取  userId
	 *@return: Long  user_id
	 */
	public Long getUserId(){
		return this.userId;
	}

	/**
	 * 设置  userId
	 *@param: userId  user_id
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
}
