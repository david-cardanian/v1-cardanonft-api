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
public class MoonToggleRequest {
    @ApiModelProperty(required = false,notes = "mapParcelId")
    private Integer mapParcelId;
    @ApiModelProperty(required = false,notes = "moonOnoff")
    private String moonOnoff;
}


