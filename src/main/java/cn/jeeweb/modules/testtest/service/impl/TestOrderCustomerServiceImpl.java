package cn.jeeweb.modules.testtest.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.testtest.entity.TestOrderCustomerEntity;
import cn.jeeweb.modules.testtest.service.ITestOrderCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 客户信息
 * @Description: 客户信息
 * @author jeeweb
 * @date 2017-06-17 20:13:28
 * @version V1.0   
 *
 */
@Transactional
@Service("testOrderCustomerService")
public class TestOrderCustomerServiceImpl  extends CommonServiceImpl<TestOrderCustomerEntity> implements  ITestOrderCustomerService {

}
