package com.zk.service;

import java.util.List;

import com.zk.model.BatchInfo;

public interface BatchInfoService {
	BatchInfoService addBatch(BatchInfo batchInfo);
	List<BatchInfo> batchInfos();
}
