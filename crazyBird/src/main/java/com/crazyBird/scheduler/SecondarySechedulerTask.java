package com.crazyBird.scheduler;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.user.UserDao;
import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.BillDO;
import com.crazyBird.utils.RestLogUtils;


@Component
public class SecondarySechedulerTask {
	private static final Logger logger = LoggerFactory.getLogger(SecondarySechedulerTask.class);
	@Autowired 
	private SecondaryOrderDao secondaryOrderDao;
	@Autowired 
	private UserWxPayOrderDao orderDao;
	@Autowired
	private UserDao userDao;
	//每隔一分钟执行一次 
	@Scheduled(cron = "0 */1 * * * ?")
	public void AutoAcceptOrder() {
		List<SecondaryOrderDO> tags = secondaryOrderDao.getAutomaticAcceptList();
		RestLogUtils.writeSchedulerTaskByInfo("二手自动收货开始执行",null);
		if (CollectionUtils.isNotEmpty(tags)) {
			for (SecondaryOrderDO tag : tags) {
				BillDO billDO = new BillDO();
				billDO.setCash(tag.getPrice());
				billDO.setType(3);
				billDO.setUserId(tag.getSellerId());
				int flag = secondaryOrderDao.updateSecondaryOrderAccept(tag);
				if (flag == 1) {
					if (orderDao.insertBill(billDO)) {
						RestLogUtils.writeSchedulerTaskByInfo("二手自动收货执行成功",null);
					} else {
						RestLogUtils.writeSchedulerTaskByInfo("二手自动收货执行失败",billDO);
					}
				} else {
					RestLogUtils.writeSchedulerTaskByInfo("二手自动收货执行失败",tag);
				}

			}
			
		}
	}
	
	@Scheduled(cron = "0 0 */1 * * ?")
	public void AutoDeleteForm() {
		RestLogUtils.writeSchedulerTaskByInfo("二手自动删除过期的formId",null);
		userDao.autoDeleteFormId();
		
	}
}
