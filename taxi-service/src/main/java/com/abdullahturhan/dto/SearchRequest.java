package com.abdullahturhan.dto;

import lombok.Data;

@Data
public class SearchRequest {
    private Integer page;
    private Integer size;
}