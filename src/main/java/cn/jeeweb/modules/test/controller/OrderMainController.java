package cn.jeeweb.modules.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.test.entity.OrderMainEntity;

/**   
 * @Title: 订单主菜单
 * @Description: 订单主菜单
 * @author jeeweb
 * @date 2017-06-03 12:05:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/test/ordermain")
@RequiresPathPermission("test:ordermain")
public class OrderMainController extends BaseCRUDController<OrderMainEntity, String> {

}
