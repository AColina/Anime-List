package com.github.acolina.animelist.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class Item implements Serializable {

    @Id
    @SequenceGenerator(name = "item_id_seq",
            sequenceName = "item_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "item_id_seq")
    @Column(columnDefinition = "serial", updatable = false, nullable = false, unique = true)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 80)
    @Column(unique = true, nullable = false)
    private String name;


    public String getName() {
        return name;
    }
}
