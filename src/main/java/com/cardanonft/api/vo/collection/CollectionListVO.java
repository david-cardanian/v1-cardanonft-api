package com.cardanonft.api.vo.collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

@Data
@ApiModel
@Getter
@Setter
@TypeAlias("CollectionListVO")
public class CollectionListVO {
    @ApiModelProperty(notes = "nftId")
    private int nftId;
    @ApiModelProperty(notes = "collectionId")
    private int collectionId;
    @ApiModelProperty(notes = "projectId")
    private int projectId;
    @ApiModelProperty(notes = "priority")
    private int priority;
    @ApiModelProperty(notes = "tokenName")
    private String tokenName;
    @ApiModelProperty(notes = "nftName")
    private String nftName;
    @ApiModelProperty(notes = "nftNameKor")
    private String nftNameKor;
    @ApiModelProperty(notes = "description")
    private String description;
    @ApiModelProperty(notes = "metaStr")
    private String metaStr;
    @ApiModelProperty(notes = "ipfs")
    private String ipfs;
    @ApiModelProperty(notes = "imgUrl")
    private String imgUrl;
    @ApiModelProperty(notes = "thumnailUrl")
    private String thumnailUrl;
    @ApiModelProperty(notes = "gotchaMinCount")
    private int gotchaMinCount;
    @ApiModelProperty(notes = "ratio")
    private int ratio;
    @ApiModelProperty(notes = "targetQuantity")
    private int targetQuantity;
    @ApiModelProperty(notes = "mintCount")
    private int mintCount;
    @ApiModelProperty(notes = "ratioAdjustYn")
    private String ratioAdjustYn;
    @ApiModelProperty(notes = "multiMintYn")
    private String multiMintYn;
    @ApiModelProperty(notes = "uniqueYn")
    private String uniqueYn;
    @ApiModelProperty(notes = "param1")
    private String param1;
    @ApiModelProperty(notes = "param2")
    private String param2;
    @ApiModelProperty(notes = "param3")
    private String param3;
    @ApiModelProperty(notes = "param4")
    private String param4;
    @ApiModelProperty(notes = "param5")
    private String param5;
    @ApiModelProperty(notes = "keyword")
    private String keyword;
    @ApiModelProperty(notes = "collectionName")
    private String collectionName;
    @ApiModelProperty(notes = "addrName")
    private String addrName;
    @ApiModelProperty(notes = "addressId")
    private String addressId;
    @ApiModelProperty(notes = "descriptionC")
    private String descriptionC;
}
