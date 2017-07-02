package cn.jeeweb.modules.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.test.entity.TestOrderMainEntity;

/**   
 * @Title: 订单主表
 * @Description: 订单主表
 * @author jeeweb
 * @date 2017-06-30 16:31:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/test/testordermain")
@RequiresPathPermission("test:testordermain")
public class TestOrderMainController extends BaseCRUDController<TestOrderMainEntity, String> {

}
