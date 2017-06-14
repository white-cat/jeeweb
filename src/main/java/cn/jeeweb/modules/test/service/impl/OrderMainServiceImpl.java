package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.MyBeanUtils;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.test.entity.OrderMainEntity;
import cn.jeeweb.modules.test.entity.OrderTicketEntity;
import cn.jeeweb.modules.test.service.IOrderCustomerService;
import cn.jeeweb.modules.test.service.IOrderMainService;
import cn.jeeweb.modules.test.service.IOrderTicketService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

/**
 * @Title: 订单主菜单
 * @Description: 订单主菜单
 * @author jeeweb
 * @date 2017-06-03 12:05:40
 * @version V1.0
 *
 */
@Transactional
@Service("orderMainService")
public class OrderMainServiceImpl extends CommonServiceImpl<OrderMainEntity> implements IOrderMainService {
	@Autowired
	private IOrderCustomerService orderCustomerService;
	@Autowired
	private IOrderTicketService orderTicketService;

	@Override
	public void save(OrderMainEntity orderMain) {
		// 保存主表
		super.save(orderMain);

		// 保存
		String orderTicketListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("orderTicketListJson"));
		List<OrderTicketEntity> orderTicketList = JSONObject.parseArray(orderTicketListJson, OrderTicketEntity.class);
		for (OrderTicketEntity orderTicket : orderTicketList) {
			// 保存字段列表
			orderTicket.setOrderMain(orderMain);
			orderTicketService.save(orderTicket);
		}

	}

	@Override
	public void update(OrderMainEntity orderMain) {
		try {
			// 删除已经删除的数据
			List<OrderTicketEntity> oldOrderTicketList = orderTicketService.list("orderMain", orderMain);
			// 字段
			String orderTicketListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("orderTicketListJson"));
			List<OrderTicketEntity> orderTicketList = JSONObject.parseArray(orderTicketListJson,
					OrderTicketEntity.class);

			// 更新主表
			super.update(orderMain);
			orderTicketList = JSONObject.parseArray(orderTicketListJson, OrderTicketEntity.class);
			List<String> newsIdList = new ArrayList<String>();
			// 保存或更新数据
			for (OrderTicketEntity orderTicket : orderTicketList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(orderTicket.getId())) {
					// 保存字段列表
					orderTicket.setOrderMain(orderMain);
					orderTicketService.save(orderTicket);
				} else {
					OrderTicketEntity oldTicket = orderTicketService.get(orderTicket.getId());
					MyBeanUtils.copyBeanNotNull2Bean(orderTicket, oldTicket);
					orderTicketService.update(oldTicket);
				}
				newsIdList.add(orderTicket.getId());
			}

			// 删除老数据
			for (OrderTicketEntity orderTicket : oldOrderTicketList) {
				String orderTicketId = orderTicket.getId();
				if (!newsIdList.contains(orderTicketId)) {
					orderTicketService.deleteById(orderTicketId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
