package org.iakei.log.service.impl;

import org.iakei.log.entity.LogsInfo;
import org.iakei.log.repos.LogsRepository;
import org.iakei.log.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsServiceImpl implements LogsService {
	@Autowired
	LogsRepository logsRepository;
	public void save(LogsInfo logsInfo){
		logsRepository.save(logsInfo);
	}
}
