package com.blogsearch.lib.searchword.repository;

import com.blogsearch.lib.searchword.model.entity.Searchword;
import com.blogsearch.lib.searchword.model.entity.SearchwordRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchwordRepository extends JpaRepository<Searchword, Long> {


    Searchword findBySearchword(String searchword);

    @Query(nativeQuery = true
            , value =   "SELECT d.searchword, d.count, d.rank, d.min_reg_dt as minDatetime, d.max_reg_dt as maxDatetime " +
                        "FROM ( SELECT c.searchword, c.count, c.min_reg_dt, c.max_reg_dt, DENSE_RANK() OVER (ORDER BY c.count DESC) rank " +
                        "       FROM (SELECT a.searchword, count(1) as count, min(b.reg_dt) as min_reg_dt, max(b.reg_dt) as max_reg_dt " +
                        "               FROM searchword a, searchword_log b " +
                        "               WHERE a.searchword_id = b.searchword_id " +
                        "               GROUP BY a.searchword" +
                        "            ) c " +
                        "   ) d " +
                        "WHERE d.rank < :rank + 1 " +
                        "ORDER BY d.rank asc ")
    List<SearchwordRank> findSearchwordRank(@Param("rank") int rank);
}
