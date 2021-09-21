package com.study.ivankov.shop.jpa;

import com.study.ivankov.shop.domain.HistoricalAction;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Ivankov_A
 *
 */
public interface HistoryRepository extends PagingAndSortingRepository<HistoricalAction, Integer> {

}
