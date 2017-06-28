package cn.jeeweb.modules.charts.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.common.controller.BaseController;

@Controller
@RequestMapping("${admin.url.prefix}/charts/echarts")
public class EchartsController extends BaseController {
	/**
	 * 
	 * @title: chart
	 * @description: 统计
	 * @param charttype
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/chart/{charttype}")
	public String chart(@PathVariable("charttype") String charttype, HttpServletRequest request) {
		request.setAttribute("charttype", charttype);
		return display(charttype);
	}
}
