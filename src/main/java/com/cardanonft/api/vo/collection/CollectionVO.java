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
    private int projectId;
    @ApiModelProperty(notes = "auctionId")
    private int auctionId;
    @ApiModelProperty(notes = "auctionDetailId")
    private int auctionDetailId;
    @ApiModelProperty(notes = "auctionName")
    private String auctionName;
    @ApiModelProperty(notes = "subName")
    private String subName;
    @ApiModelProperty(notes = "description")
    private String description;
    @ApiModelProperty(notes = "startDate")
    private String startDate;
    @ApiModelProperty(notes = "endDate")
    private String endDate;
    @ApiModelProperty(notes = "caCloseYn")
    private int caCloseYn;
    @ApiModelProperty(notes = "lowestBidPrice")
    private int lowestBidPrice;
    @ApiModelProperty(notes = "highstBidPrice")
    private int highstBidPrice;
    @ApiModelProperty(notes = "cadCloseYn")
    private int cadCloseYn;
    @ApiModelProperty(notes = "nftId")
    private int nftId;
    @ApiModelProperty(notes = "collectionId")
    private int collectionId;
    @ApiModelProperty(notes = "nftName")
    private String nftName;
    @ApiModelProperty(notes = "nftNameKor")
    private String nftNameKor;
    @ApiModelProperty(notes = "metaStr")
    private String metaStr;
    @ApiModelProperty(notes = "ipfs")
    private String ipfs;
    @ApiModelProperty(notes = "imgUrl")
    private String imgUrl;
}
