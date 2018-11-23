package com.zk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zk.model.BatchInfo;

@Component
public class BatchInfoServiceImpl implements BatchInfoService {
	
	List<BatchInfo> bathInfoList=new ArrayList<>();
	
	public List<BatchInfo> getBathInfoList() {
		return bathInfoList;
	}

	public void setBathInfoList(List<BatchInfo> bathInfoList) {
		this.bathInfoList = bathInfoList;
	}
	
	@Override
	public BatchInfoService addBatch(BatchInfo batchInfo) {
		this.bathInfoList.add(batchInfo);
		return this;
	}
	
	@Override
	public List<BatchInfo> batchInfos() {
		return bathInfoList;
	}

}
