package com.cardanonft.api.vo.collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

@Data
@ApiModel
@Getter
@Setter
@TypeAlias("CollectionHistoryVO")
public class CollectionHistoryVO {
    @ApiModelProperty(notes = "auctionHistotyId")
    private int auctionHistotyId;
    @ApiModelProperty(notes = "auctionId")
    private int auctionId;
    @ApiModelProperty(notes = "auctionDetailId")
    private int auctionDetailId;
    @ApiModelProperty(notes = "collectionId")
    private int collectionId;
    @ApiModelProperty(notes = "status")
    private int status;
    @ApiModelProperty(notes = "amount")
    private int amount;
    @ApiModelProperty(notes = "fromAddress")
    private String fromAddress;
    @ApiModelProperty(notes = "viewAddress")
    private String viewAddress;
    @ApiModelProperty(notes = "createdAt")
    private String createdAt;
}
