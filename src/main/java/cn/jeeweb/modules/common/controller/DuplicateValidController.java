package cn.jeeweb.modules.common.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.modules.common.bean.DuplicateValid;
import cn.jeeweb.modules.common.bean.ValidJson;
import cn.jeeweb.modules.sys.controller.AdminController;

/**
 * @Title: 重复验证
 * @Description: 重复验证
 * @author 王存见
 * @date 2017-02-23 22:27:30
 * @version V1.0
 */
@Controller
@RequestMapping("/duplicateValid")
public class DuplicateValidController extends AdminController {

	/**
	 * 校验数据是否在系统中是否存在
	 * 
	 * @return
	 */
	@RequestMapping("valid")
	@ResponseBody
	public ValidJson doValid(DuplicateValid duplicateValid, HttpServletRequest request) {

		ValidJson validJson = new ValidJson();
		Integer num = null;
		String sql = "";
		if (!StringUtils.isEmpty(duplicateValid.getDataId())) {
			// [2].编辑页面校验
			sql = "SELECT count(*) FROM " + duplicateValid.getTable() + " WHERE " + duplicateValid.getField() + " ='"
					+ duplicateValid.getVlaue() + "' and id != '" + duplicateValid.getDataId() + "'";
			// num = commonEasyService.countBySql(sql);
		} else {
			// [1].添加页面校验
			sql = "SELECT count(*) FROM " + duplicateValid.getTable() + " WHERE " + duplicateValid.getField() + " ='"
					+ duplicateValid.getVlaue() + "'";
			// num = commonEasyService.countBySql(sql);
		}

		if (num == null || num == 0) {
			// 该值可用
			validJson.setValid(true);
			// j.setMsg("该值可用！");
		} else {
			// 该值不可用
			validJson.setValid(false);
		}
		return validJson;
	}
}
