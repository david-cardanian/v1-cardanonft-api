package com.cardanonft.api.vo.collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@ApiModel
@Getter
@Setter
public class CollectionVO {
    @ApiModelProperty(notes = "projectId")
    private Integer projectId;
     @ApiModelProperty(notes = "startDate")
    private String startDate;
}
