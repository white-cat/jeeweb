package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.entity.OrderCustomerEntity;
import cn.jeeweb.modules.test.service.IOrderCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 订单客户信息
 * @Description: 订单客户信息
 * @author jeeweb
 * @date 2017-06-03 12:03:29
 * @version V1.0   
 *
 */
@Transactional
@Service("orderCustomerService")
public class OrderCustomerServiceImpl  extends CommonServiceImpl<OrderCustomerEntity> implements  IOrderCustomerService {

}
