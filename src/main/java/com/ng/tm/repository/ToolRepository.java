package com.ng.tm.repository;

import com.ng.tm.domain.Tool;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends PagingAndSortingRepository<Tool, BigInteger> {

    List<com.ng.tm.domain.Tool> findAll();
}
