package com.cardanonft.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class CollectionSearchRequest {
    @ApiModelProperty(required = false,notes = "projectId")
    private int projectId;
    @ApiModelProperty(required = false,notes = "collectionId")
    private int collectionId;
    @ApiModelProperty(required = false,notes = "startDate")
    private String startDate;
}


