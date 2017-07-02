package cn.jeeweb.modules.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.common.controller.BaseTreeController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.test.entity.TestTreeEntity;

/**   
 * @Title: 测试数
 * @Description: 测试数
 * @author jeeweb
 * @date 2017-06-28 23:30:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/test/testtree")
@RequiresPathPermission("test:testtree")
public class TestTreeController extends BaseTreeController<TestTreeEntity, String> {

}
