package cn.jeeweb.modules.charts.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.controller.BaseController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;

@Controller
@RequestMapping("${admin.url.prefix}/charts/echarts")
public class EchartsController extends BaseController {
	/**
	 * 
	 * @title: echart
	 * @description: 统计
	 * @return
	 * @return: String
	 */
	@RequestMapping("/chart/{charttype}")
	public String chart(@PathVariable("charttype") String charttype, HttpServletRequest request) {
		request.setAttribute("charttype", charttype);
		return display(charttype);
	}
}
