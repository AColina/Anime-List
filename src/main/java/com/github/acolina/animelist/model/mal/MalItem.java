package com.github.acolina.animelist.model.mal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MalItem {
    private Long id;
    private String type;
    private String name;
    private String url;
    private String image_url;
    private String thumbnail_url;
    private Double es_score;
}
