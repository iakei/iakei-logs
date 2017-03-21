package org.iakei.log.entity;

import java.util.Date;

public class LogsInfo {

	private String module;// 服务模块
	private String userId;// 用户id
	private Object content;// 日志内容
	private String createTime;// 创建时间

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
