package com.ng.tm.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ng.tm.domain.Profile;

@Repository
public interface ProfileRepository extends
		PagingAndSortingRepository<Profile, String> {

	@Override
	List<com.ng.tm.domain.Profile> findAll();
}
