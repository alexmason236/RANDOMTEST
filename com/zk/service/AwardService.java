package com.zk.service;

import java.util.List;

import com.zk.model.Participator;

public interface AwardService {
	List<Participator> getAllParticipators();
	List<Integer> getRandomNumList(int nums,int start,int end);
	List<Integer> sort(List<Integer> list);
	List<Participator> readExcelData(String filePath);
}
