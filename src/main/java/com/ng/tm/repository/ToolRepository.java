package com.ng.tm.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ng.tm.domain.Tool;

@Repository
public interface ToolRepository extends
		PagingAndSortingRepository<Tool, String> {

	@Override
	List<com.ng.tm.domain.Tool> findAll();
}
