package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.entity.TestOrderMain;
import cn.jeeweb.modules.test.service.ITestOrderMainService;
import cn.jeeweb.modules.test.entity.TestOrderTicket;
import cn.jeeweb.modules.test.service.ITestOrderTicketService;
import cn.jeeweb.modules.test.entity.TestOrderCustomer;
import cn.jeeweb.modules.test.service.ITestOrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

/**   
 * @Title: 订单主表
 * @Description: 订单主表
 * @author jeeweb
 * @date 2017-07-24 12:10:30
 * @version V1.0   
 *
 */
@Transactional
@Service("testOrderMainService")
public class TestOrderMainServiceImpl  extends CommonServiceImpl<TestOrderMain> implements  ITestOrderMainService {
	@Autowired
	private ITestOrderTicketService testOrderTicketService;
	@Autowired
	private ITestOrderCustomerService testOrderCustomerService;
	
	@Override
	public void save(TestOrderMain testOrderMain) {
		// 保存主表
		super.save(testOrderMain);
		// 保存机票信息
		String testOrderTicketListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderTicketListJson"));
		List<TestOrderTicket> testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson, TestOrderTicket.class);
		for (TestOrderTicket testOrderTicket : testOrderTicketList) {
			// 保存字段列表
			testOrderTicket.setOrder(testOrderMain);
			testOrderTicketService.save(testOrderTicket);
		}
		// 保存客户信息
		String testOrderCustomerListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderCustomerListJson"));
		List<TestOrderCustomer> testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson, TestOrderCustomer.class);
		for (TestOrderCustomer testOrderCustomer : testOrderCustomerList) {
			// 保存字段列表
			testOrderCustomer.setOrder(testOrderMain);
			testOrderCustomerService.save(testOrderCustomer);
		}
	}
	
	@Override
	public void update(TestOrderMain testOrderMain) {
		try {
			// 获得以前的数据
			List<TestOrderTicket> oldTestOrderTicketList = testOrderTicketService.list("order", testOrderMain);
			// 字段
			String testOrderTicketListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderTicketListJson"));
			List<TestOrderTicket> testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson,
					TestOrderTicket.class);
			// 获得以前的数据
			List<TestOrderCustomer> oldTestOrderCustomerList = testOrderCustomerService.list("order", testOrderMain);
			// 字段
			String testOrderCustomerListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderCustomerListJson"));
			List<TestOrderCustomer> testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson,
					TestOrderCustomer.class);
			// 更新主表
			super.update(testOrderMain);
			testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson, TestOrderTicket.class);
			List<String> newsTestOrderTicketIdList = new ArrayList<String>();
			// 保存或更新数据
			for (TestOrderTicket testOrderTicket : testOrderTicketList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(testOrderTicket.getId())) {
					// 保存字段列表
					testOrderTicket.setOrder(testOrderMain);
					testOrderTicketService.save(testOrderTicket);
				} else {
					TestOrderTicket oldTicket = testOrderTicketService.get(testOrderTicket.getId());
					MyBeanUtils.copyBeanNotNull2Bean(testOrderTicket, oldTicket);
					testOrderTicketService.update(oldTicket);
				}
				newsTestOrderTicketIdList.add(testOrderTicket.getId());
			}

			// 删除老数据
			for (TestOrderTicket testOrderTicket : oldTestOrderTicketList) {
				String testOrderTicketId = testOrderTicket.getId();
				if (!newsTestOrderTicketIdList.contains(testOrderTicketId)) {
					testOrderTicketService.deleteById(testOrderTicketId);
				}
			}
			testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson, TestOrderCustomer.class);
			List<String> newsTestOrderCustomerIdList = new ArrayList<String>();
			// 保存或更新数据
			for (TestOrderCustomer testOrderCustomer : testOrderCustomerList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(testOrderCustomer.getId())) {
					// 保存字段列表
					testOrderCustomer.setOrder(testOrderMain);
					testOrderCustomerService.save(testOrderCustomer);
				} else {
					TestOrderCustomer oldTicket = testOrderCustomerService.get(testOrderCustomer.getId());
					MyBeanUtils.copyBeanNotNull2Bean(testOrderCustomer, oldTicket);
					testOrderCustomerService.update(oldTicket);
				}
				newsTestOrderCustomerIdList.add(testOrderCustomer.getId());
			}

			// 删除老数据
			for (TestOrderCustomer testOrderCustomer : oldTestOrderCustomerList) {
				String testOrderCustomerId = testOrderCustomer.getId();
				if (!newsTestOrderCustomerIdList.contains(testOrderCustomerId)) {
					testOrderCustomerService.deleteById(testOrderCustomerId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
}