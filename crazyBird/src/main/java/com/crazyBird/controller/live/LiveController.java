package com.crazyBird.controller.live;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.live.model.LivePlayUrlModel;
import com.crazyBird.controller.live.param.PushUrlParam;
import com.crazyBird.service.live.LiveService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.LiveUtils;

/**
 * 
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
	@RequestMapping(value="/getPlayUrl",method = RequestMethod.GET)
	@ResponseBody
	public LivePlayUrlModel getPlayUrl() {
		
		return liveProcess.getPlayUrl();	
	}
	
}
