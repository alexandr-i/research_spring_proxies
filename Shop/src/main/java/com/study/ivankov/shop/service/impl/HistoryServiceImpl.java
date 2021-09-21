package com.study.ivankov.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.ivankov.shop.domain.HistoricalAction;
import com.study.ivankov.shop.jpa.HistoryRepository;
import com.study.ivankov.shop.service.HistoryService;

/**
 * @author Ivankov_A
 *
 */
@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryRepository historyRepository;
	
	@Override
	public void save(HistoricalAction action) {
		historyRepository.save(action);
	}

}
