package com.blogsearch.lib.searchword.repository;

import com.blogsearch.lib.searchword.model.entity.SearchwordLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchwordLogRepository extends JpaRepository<SearchwordLog, Long> {

}
