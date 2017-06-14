package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.entity.OrderTicketEntity;
import cn.jeeweb.modules.test.service.IOrderTicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 订单机票信息
 * @Description: 订单机票信息
 * @author jeeweb
 * @date 2017-06-03 12:03:21
 * @version V1.0   
 *
 */
@Transactional
@Service("orderTicketService")
public class OrderTicketServiceImpl  extends CommonServiceImpl<OrderTicketEntity> implements  IOrderTicketService {

}
