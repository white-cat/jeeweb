package cn.jeeweb.modules.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jeeweb.core.controller.BaseController;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: FormDemoController.java
 * @package cn.jeeweb.modules.demo.controller
 * @description: 编辑器demo
 * @author: 王存见
 * @date: 2017年5月18日 下午6:17:24
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */

@Controller
@RequestMapping("${admin.url.prefix}/demo/form")
public class FormDemoController extends BaseController {

	/**
	 * 
	 * @title: editor
	 * @description: 编辑器
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editor")
	public String editor() {
		return display("editor");
	}

	/**
	 * 
	 * @title: editor
	 * @description: 文件上传
	 * @return
	 * @return: String
	 */
	@RequestMapping("/upload")
	public String upload() {
		return display("upload");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/combox")
	public String combox(HttpServletRequest request) {
		List<Map> dataList = new ArrayList<Map>();
		for (int i = 0; i < 10; i++) {
			Map map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "jeeweb");
			dataList.add(map);
		}
		request.setAttribute("datas", dataList);
		return display("combox");
	}

	@RequestMapping("/ajaxCombox")
	@ResponseBody
	public List ajaxCombox(HttpServletRequest request) {
		String keyword=request.getParameter("keyword");
		logger.info("搜索关键字:----->"+keyword);
		List<Map> dataList = new ArrayList<Map>();
		for (int i = 0; i < 10; i++) {
			Map map = new HashMap<String, Object>();
			map.put("id", i+"40288ab85c33548d015c337aa269002d");
			map.put("name", "jeeweb");
			dataList.add(map);
		}
		return dataList;
	}

}
