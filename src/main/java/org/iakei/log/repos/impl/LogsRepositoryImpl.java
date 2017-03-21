package org.iakei.log.repos.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.iakei.log.entity.LogsInfo;
import org.iakei.log.repos.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

@Repository
public class LogsRepositoryImpl implements LogsRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void save(LogsInfo logsInfo) {
		mongoTemplate.save(JSON.toJSONString(logsInfo), "iakei_logs_" + getCollectionName());
	}

	private String getCollectionName() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

}
