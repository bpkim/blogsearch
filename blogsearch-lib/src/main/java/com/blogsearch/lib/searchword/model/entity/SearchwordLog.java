package com.blogsearch.lib.searchword.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(of = {"id", "searchword"})
public class SearchwordLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="searchword_log_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "searchword_id")
    private Searchword searchword;
    private LocalDateTime regDt;

    public void changeSearchword(Searchword searchword) {
        this.searchword = searchword;
        searchword.getSearchwordLogs().add(this);
    }

    public SearchwordLog() {
    }

    public SearchwordLog(Long id, Searchword searchword, LocalDateTime regDt) {
        this.id = id;
        this.searchword = searchword;
        this.regDt = regDt;
    }

    public SearchwordLog(Searchword searchword) {
        this.searchword = searchword;
    }
}
