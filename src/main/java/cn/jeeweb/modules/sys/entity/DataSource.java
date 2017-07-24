package cn.jeeweb.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import cn.jeeweb.core.common.entity.DataEntity;

/**   
 * @Title: 数据源
 * @Description: 数据源
 * @author jeeweb
 * @date 2017-05-10 12:01:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sys_data_source")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class DataSource extends DataEntity<String> {
	/**id*/
	private String id;
	/**索引关键字*/
	private String dbKey;
	/**驱动*/
	private String driverClass;
	/**数据库名称*/
	private String dbName;
	/**密码*/
	private String dbPassword;
	/**数据库类型*/
	private String dbType;
	/**描述*/
	private String description;

	/**URL*/
	private String url;
	/**帐号*/
	private String dbUser;
	/**
	 * 获取  id
	 *@return: String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=36,scale=0)
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  dbKey
	 *@return: String  索引关键字
	 */
	@Column(name ="db_key",nullable=false,length=50,scale=0)
	public String getDbKey(){
		return this.dbKey;
	}

	/**
	 * 设置  dbKey
	 *@param: dbKey  索引关键字
	 */
	public void setDbKey(String dbKey){
		this.dbKey = dbKey;
	}
	/**
	 * 获取  driverClass
	 *@return: String  驱动
	 */
	@Column(name ="driver_class",nullable=false,length=50,scale=0)
	public String getDriverClass(){
		return this.driverClass;
	}

	/**
	 * 设置  driverClass
	 *@param: driverClass  驱动
	 */
	public void setDriverClass(String driverClass){
		this.driverClass = driverClass;
	}
	/**
	 * 获取  dbName
	 *@return: String  数据库名称
	 */
	@Column(name ="db_name",nullable=true,length=50,scale=0)
	public String getDbName(){
		return this.dbName;
	}

	/**
	 * 设置  dbName
	 *@param: dbName  数据库名称
	 */
	public void setDbName(String dbName){
		this.dbName = dbName;
	}
	/**
	 * 获取  dbPassword
	 *@return: String  密码
	 */
	@Column(name ="db_password",nullable=true,length=50,scale=0)
	public String getDbPassword(){
		return this.dbPassword;
	}

	/**
	 * 设置  dbPassword
	 *@param: dbPassword  密码
	 */
	public void setDbPassword(String dbPassword){
		this.dbPassword = dbPassword;
	}
	/**
	 * 获取  dbType
	 *@return: String  数据库类型
	 */
	@Column(name ="db_type",nullable=true,length=50,scale=0)
	public String getDbType(){
		return this.dbType;
	}

	/**
	 * 设置  dbType
	 *@param: dbType  数据库类型
	 */
	public void setDbType(String dbType){
		this.dbType = dbType;
	}
	/**
	 * 获取  description
	 *@return: String  描述
	 */
	@Column(name ="description",nullable=false,length=50,scale=0)
	public String getDescription(){
		return this.description;
	}

	/**
	 * 设置  description
	 *@param: description  描述
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * 获取  url
	 *@return: String  URL
	 */
	@Column(name ="url",nullable=false,length=200,scale=0)
	public String getUrl(){
		return this.url;
	}

	/**
	 * 设置  url
	 *@param: url  URL
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 * 获取  dbUser
	 *@return: String  帐号
	 */
	@Column(name ="db_user",nullable=false,length=50,scale=0)
	public String getDbUser(){
		return this.dbUser;
	}

	/**
	 * 设置  dbUser
	 *@param: dbUser  帐号
	 */
	public void setDbUser(String dbUser){
		this.dbUser = dbUser;
	}
	
}
