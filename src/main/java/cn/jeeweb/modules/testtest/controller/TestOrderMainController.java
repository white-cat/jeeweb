package cn.jeeweb.modules.testtest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.testtest.entity.TestOrderMainEntity;

/**   
 * @Title: 订单主表
 * @Description: 订单主表
 * @author jeeweb
 * @date 2017-06-17 20:13:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/testtest/testordermain")
@RequiresPathPermission("testtest:testordermain")
public class TestOrderMainController extends BaseCRUDController<TestOrderMainEntity, String> {

}
