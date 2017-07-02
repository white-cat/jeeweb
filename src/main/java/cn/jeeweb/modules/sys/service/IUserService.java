package cn.jeeweb.modules.sys.service;

import java.util.Set;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.sys.entity.User;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2014-12-20 21:31:50
 * @version V1.0
 *
 */
public interface IUserService extends ICommonService<User> {
	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(String userid, String newPassword);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据Email查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByEmail(String email);

	/**
	 * 根据Email查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByPhone(String phone);

}
