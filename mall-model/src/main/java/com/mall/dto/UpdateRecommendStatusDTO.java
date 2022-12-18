package com.mall.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateRecommendStatusDTO implements Serializable {
    private List<Integer> ids;

    private Integer recommendStatus;

}
