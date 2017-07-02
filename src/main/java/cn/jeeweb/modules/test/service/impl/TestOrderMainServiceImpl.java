package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.entity.TestOrderMainEntity;
import cn.jeeweb.modules.test.service.ITestOrderMainService;
import cn.jeeweb.modules.test.entity.TestOrderTicketEntity;
import cn.jeeweb.modules.test.service.ITestOrderTicketService;
import cn.jeeweb.modules.test.entity.TestOrderCustomerEntity;
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
 * @date 2017-06-30 16:31:40
 * @version V1.0   
 *
 */
@Transactional
@Service("testOrderMainService")
public class TestOrderMainServiceImpl  extends CommonServiceImpl<TestOrderMainEntity> implements  ITestOrderMainService {
	@Autowired
	private ITestOrderTicketService testOrderTicketService;
	@Autowired
	private ITestOrderCustomerService testOrderCustomerService;
	
	@Override
	public void save(TestOrderMainEntity testOrderMain) {
		// 保存主表
		super.save(testOrderMain);
		// 保存机票信息
		String testOrderTicketListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderTicketListJson"));
		List<TestOrderTicketEntity> testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson, TestOrderTicketEntity.class);
		for (TestOrderTicketEntity testOrderTicket : testOrderTicketList) {
			// 保存字段列表
			testOrderTicket.setOrder(testOrderMain);
			testOrderTicketService.save(testOrderTicket);
		}
		// 保存客户信息
		String testOrderCustomerListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderCustomerListJson"));
		List<TestOrderCustomerEntity> testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson, TestOrderCustomerEntity.class);
		for (TestOrderCustomerEntity testOrderCustomer : testOrderCustomerList) {
			// 保存字段列表
			testOrderCustomer.setOrder(testOrderMain);
			testOrderCustomerService.save(testOrderCustomer);
		}
	}
	
	@Override
	public void update(TestOrderMainEntity testOrderMain) {
		try {
			// 获得以前的数据
			List<TestOrderTicketEntity> oldTestOrderTicketList = testOrderTicketService.list("order", testOrderMain);
			// 字段
			String testOrderTicketListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderTicketListJson"));
			List<TestOrderTicketEntity> testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson,
					TestOrderTicketEntity.class);
			// 获得以前的数据
			List<TestOrderCustomerEntity> oldTestOrderCustomerList = testOrderCustomerService.list("order", testOrderMain);
			// 字段
			String testOrderCustomerListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("testOrderCustomerListJson"));
			List<TestOrderCustomerEntity> testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson,
					TestOrderCustomerEntity.class);
			// 更新主表
			super.update(testOrderMain);
			testOrderTicketList = JSONObject.parseArray(testOrderTicketListJson, TestOrderTicketEntity.class);
			List<String> newsTestOrderTicketIdList = new ArrayList<String>();
			// 保存或更新数据
			for (TestOrderTicketEntity testOrderTicket : testOrderTicketList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(testOrderTicket.getId())) {
					// 保存字段列表
					testOrderTicket.setOrder(testOrderMain);
					testOrderTicketService.save(testOrderTicket);
				} else {
					TestOrderTicketEntity oldTicket = testOrderTicketService.get(testOrderTicket.getId());
					MyBeanUtils.copyBeanNotNull2Bean(testOrderTicket, oldTicket);
					testOrderTicketService.update(oldTicket);
				}
				newsTestOrderTicketIdList.add(testOrderTicket.getId());
			}

			// 删除老数据
			for (TestOrderTicketEntity testOrderTicket : oldTestOrderTicketList) {
				String testOrderTicketId = testOrderTicket.getId();
				if (!newsTestOrderTicketIdList.contains(testOrderTicketId)) {
					testOrderTicketService.deleteById(testOrderTicketId);
				}
			}
			testOrderCustomerList = JSONObject.parseArray(testOrderCustomerListJson, TestOrderCustomerEntity.class);
			List<String> newsTestOrderCustomerIdList = new ArrayList<String>();
			// 保存或更新数据
			for (TestOrderCustomerEntity testOrderCustomer : testOrderCustomerList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(testOrderCustomer.getId())) {
					// 保存字段列表
					testOrderCustomer.setOrder(testOrderMain);
					testOrderCustomerService.save(testOrderCustomer);
				} else {
					TestOrderCustomerEntity oldTicket = testOrderCustomerService.get(testOrderCustomer.getId());
					MyBeanUtils.copyBeanNotNull2Bean(testOrderCustomer, oldTicket);
					testOrderCustomerService.update(oldTicket);
				}
				newsTestOrderCustomerIdList.add(testOrderCustomer.getId());
			}

			// 删除老数据
			for (TestOrderCustomerEntity testOrderCustomer : oldTestOrderCustomerList) {
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
