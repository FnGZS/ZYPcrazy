package com.crazyBird.controller.live.param;

public class PushUrlParam {
	private String domain;
	private String key;
	private String streamId;
	private long txTime;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public long getTxTime() {
		return txTime;
	}
	public void setTxTime(long txTime) {
		this.txTime = txTime;
	}
	
}
