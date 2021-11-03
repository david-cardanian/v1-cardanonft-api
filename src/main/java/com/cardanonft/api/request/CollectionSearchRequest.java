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
    @ApiModelProperty(required = false,notes = "nftId")
    private int nftId;
    @ApiModelProperty(required = false,notes = "auctionId")
    private int auctionId;
    @ApiModelProperty(required = false,notes = "auctionDetailId")
    private int auctionDetailId;
    @ApiModelProperty(required = false,notes = "createdAt")
    private String createdAt;
}


