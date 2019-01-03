package com.crazyBird.controller.live;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.socket.TextMessage;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint("/websocket/{roomId}")
public class MyWebSocket {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int ONLINECOUNT = 0;
	private static final Map<Integer, Integer> onlineCountMap = new HashMap<>();
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	// private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new
	// CopyOnWriteArraySet<MyWebSocket>();
	private static final Map<Integer, CopyOnWriteArraySet<MyWebSocket>> rooms = new HashMap<>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	private static Integer roomId;
	private static final Map<Integer, ArrayList<String>> textMap = new HashMap<>();

	// private static final Map<IntegerArrayList<String> textMessage = new
	// ArrayList<String>();
	public MyWebSocket() {

	}

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(@PathParam(value = "roomId") String room, Session session) throws IOException {
		this.session = session;
		this.roomId = Integer.parseInt(room);
		Integer onlineCount = onlineCountMap.get(roomId);
		if (onlineCount == null) {
			onlineCountMap.put(roomId, ONLINECOUNT);
		}
		ArrayList<String> textMessage = textMap.get(roomId);
		if (textMessage == null) {
			textMessage = new ArrayList<>();
			textMap.put(roomId, textMessage);
		}
		CopyOnWriteArraySet<MyWebSocket> webSocketSet = rooms.get(roomId);
		if (webSocketSet == null) {
			synchronized (rooms) {
				if (!rooms.containsKey(roomId)) {
					webSocketSet = new CopyOnWriteArraySet<>();
					rooms.put(roomId, webSocketSet);
				}
			}
		}

		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		sendMessage("{\"title\":\"欢迎进入直播间,当前房间的在线人数为" + getOnlineCount() + "\"}");

		if (textMessage.size() > 100) {
			textMessage.clear();
		} else {

			for (String message : textMessage) {
				sendMessage(message);
			}

		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(@PathParam(value = "roomId") String room) {
		this.roomId = Integer.parseInt(room);
		CopyOnWriteArraySet<MyWebSocket> webSocketSet = rooms.get(roomId);
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(@PathParam(value = "roomId") String room, String message, Session session) {
		this.roomId = Integer.parseInt(room);
		CopyOnWriteArraySet<MyWebSocket> webSocketSet = rooms.get(roomId);
		ArrayList<String> textMessage = textMap.get(Integer.parseInt(room));
		textMessage.add(message);
		// 群发消息
		for (MyWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);

	}

	public static synchronized int getOnlineCount() {
		return onlineCountMap.get(roomId);
	}

	public static synchronized void addOnlineCount() {
		Integer onlineCount = onlineCountMap.get(roomId);
		onlineCount = onlineCount + 1;
		onlineCountMap.put(roomId, onlineCount);
		// MyWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		Integer onlineCount = onlineCountMap.get(roomId);
		onlineCount = onlineCount - 1;
		onlineCountMap.put(roomId, onlineCount);
		// MyWebSocket.onlineCount--;
	}
}
