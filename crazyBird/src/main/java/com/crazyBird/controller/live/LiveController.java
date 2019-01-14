package com.crazyBird.controller.live;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.live.model.LiveGiftModel;
import com.crazyBird.controller.live.model.LiveOrderModel;
import com.crazyBird.controller.live.model.LivePlayUrlDetailModel;
import com.crazyBird.controller.live.model.LivePlayUrlModel;
import com.crazyBird.controller.live.param.LiveGiftOrderParam;


/**
 * 直播功能
 * @author zzc
 * 
 */
@Controller
@RequestMapping("/live")
public class LiveController {
	@Autowired
	private LiveProcess liveProcess;
	/*public SimpleFlagModel getPushUrl(PushUrlParam param) {
		SimpleFlagModel  model = new SimpleFlagModel();
		
		Date time=DateUtil.getStringToDate("2018-12-25 18:00:00", DateUtil.DATE_FORMAT_YMDHMS);
		long txTime = time.getTime()/1000;
		System.out.println(txTime);
		String pushUrl=LiveUtils.getPushUrl(param.getDomain(),param.getKey(),param.getStreamId(),txTime);
		List<String> liveUrlList=LiveUtils.getLiveUrl(param.getDomain(), param.getStreamId());
		model.setMessage(pushUrl);
		for (String string : liveUrlList) {
			System.out.println(string);
		}
		return model;
		
	}*/
	/**
	 * 得到直播列表
	 * @return
	 */
	@RequestMapping(value="/getPlayUrl",method = RequestMethod.GET)
	@ResponseBody
	public LivePlayUrlModel getPlayUrl() {
		
		return liveProcess.getPlayUrl();	
	}
	/**
	 * 得到直播详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPlayUrl/{id}",method = RequestMethod.GET)
	@ResponseBody
	public LivePlayUrlDetailModel getPlayUrl(@PathVariable Integer id) {
		
		return liveProcess.getPlayUrlDetail(id);
	}
	/**
	 * 得到直播统计数据
	 * @return
	 */
	@RequestMapping(value="/getPlayStatic",method = RequestMethod.GET)
	@ResponseBody
	public SimpleFlagModel getPlayStatic() {
		
		return liveProcess.getPlayStatic();
	}
	/**
	 * 得到礼物列表
	 */
	@RequestMapping(value="/getGiftList",method = RequestMethod.GET)
	@ResponseBody
	public LiveGiftModel getLiveGiftList() {	
		return liveProcess.getLiveGiftList();
	}
	/**
	 * 下单
	 */
	@RequestMapping(value="/createGiftList",method = RequestMethod.POST)
	@ResponseBody
	public LiveOrderModel createGiftOrder(@RequestBody LiveGiftOrderParam param) {
		return liveProcess.createGiftOrder(param);
		
	}
	
}
