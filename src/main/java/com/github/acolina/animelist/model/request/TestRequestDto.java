package com.github.acolina.animelist.model.request;

import com.github.acolina.animelist.model.entity.Anime;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TestRequestDto {

    @NotNull
    private Long itemId;
    @NotNull
    private Anime anime;
    private boolean other;
    private String description;

    public Long getItemId() {
        return itemId;
    }

    public Anime getAnime() {
        return anime;
    }

    public boolean isOther() {
        return other;
    }

    public String getDescription() {
        return description;
    }
}
