package com.crazyBird.controller.live;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;



/**
 * 
 * @author vimmone 消息处理中心
 *
 */
@Service
public class WebSocketHander implements WebSocketHandler {
	@Autowired
	private UserHandler userHandler;
	//private static final Logger logger = Logger.getLogger(WebSocketHander.class);

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//logger.debug("链接成功......");
		userHandler.addUser(session);

	}

	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		userHandler.handleMessage(webSocketSession, webSocketMessage);

	}

	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		if (webSocketSession.isOpen()) {
			webSocketSession.close();
		}
		//logger.debug("链接出错，关闭链接......");
		userHandler.error(webSocketSession);

	}

	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
		//logger.debug("链接关闭......" + closeStatus.toString());
		try {
			userHandler.error(webSocketSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

}