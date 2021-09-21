package com.study.ivankov.shop.service;

import com.study.ivankov.shop.domain.HistoricalAction;

/**
 * @author Ivankov_A
 *
 */
public interface HistoryService {

	void save (HistoricalAction action);
}
