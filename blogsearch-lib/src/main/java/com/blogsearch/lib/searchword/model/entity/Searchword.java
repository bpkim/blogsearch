package com.blogsearch.lib.searchword.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = {"id", "searchword"})
public class Searchword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="searchword_id")
    private Long id;
    private String searchword;
    @OneToMany(mappedBy = "searchword")
    List<SearchwordLog> searchwordLogs = new ArrayList<>();

    public Searchword() {
    }

    public Searchword(String searchword){
        this.searchword = searchword;
    }
}
