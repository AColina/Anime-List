package com.github.acolina.animelist.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Test implements Serializable {

    @Id
    @SequenceGenerator(name = "test_id_seq",
            sequenceName = "test_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "test_id_seq")
    @Column(columnDefinition = "serial", updatable = false, nullable = false, unique = true)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item")
    private Item item;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime")
    private Anime anime;
    @Column
    private boolean other;
    @Column
    private String description;
    @Column(name = "creation_date", nullable = false)
    private LocalDate crationDate;

    public Test() {
        crationDate = LocalDate.now();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

}
