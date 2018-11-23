package com.zk.service;

import java.util.List;

import com.zk.model.Participator;

public interface PaticipatorRepo {
	void clearAllPaticipators();
	void savePaticipators(List<Participator> participators);
}
