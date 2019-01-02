package com.crazyBird.service.live.impl;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.live.LiveDao;
import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.dao.live.dataobject.LiveGiftDO;
import com.crazyBird.service.live.LiveService;
@Component("LiveService")
public class LiveServiceImpl implements LiveService{
	private static final String LIVE_STATISTICAL="http://statcgi.video.qcloud.com/common_access?cmd=%s&interface=%s&Param.n.page_no=%s&Param.n.page_size=%s&t=%s&sign=%s";
	//查询指定直播流的推流和播放信息；
	private static String Get_LiveStat="Get_LiveStat";
	//仅返回推流统计信息以提高查询效率；
	private static String Get_LivePushStat="Get_LivePushStat";
	//仅返回播放统计信息以提高查询效率。
	private static String Get_LivePlayStat="Get_LivePlayStat";
	@Autowired
	private LiveDao liveDao;
	private static CloseableHttpClient httpClient = null;

	private static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClientBuilder.create().build();
		}
		return httpClient;
	}

	@Override
	public List<LiveDO> getPlayList() {
		
		return liveDao.getPlayList();
	}


	@Override
	public LiveDO getPlayUrl(Integer id) {
	
		return liveDao.getPlayUrl(id);
	}


	@Override
	public void liveStatistical() {
		int cmd=1234;
		int page_no=1;
		int page_size=300;
		int tString=1471850187;
		String sign="b17971b51ba0fe5916ddcd96692e9fb3";
		
		String url = String.format(LIVE_STATISTICAL, cmd, Get_LivePlayStat, page_no,page_size,tString,sign);
		URI uri = URI.create(url);
		HttpGet get = new HttpGet(uri);
		HttpResponse response;
		System.out.println(url);
		try {
			response = getHttpClient().execute(get);
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<LiveGiftDO> getLiveGiftList() {
		return liveDao.getLiveGiftList();
	}
	
	
	
}
