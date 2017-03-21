package org.iakei.log.service;

import org.iakei.log.entity.LogsInfo;

public interface LogsService {
	
	/**
	 * 保存日志文件
	 * @author zhoushaofei   
	 * @Title: save    
	 * @param logsInfo 
	 * @Return: void 返回值
	 */
	public void save(LogsInfo logsInfo);
	
}
