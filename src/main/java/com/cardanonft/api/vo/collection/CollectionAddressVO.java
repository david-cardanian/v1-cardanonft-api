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
@TypeAlias("CollectionAddressVO")
public class CollectionAddressVO {
    @ApiModelProperty(notes = "addrName")
    private String addrName;
    @ApiModelProperty(notes = "addressId")
    private String addressId;
    @ApiModelProperty(notes = "descriptionC")
    private String descriptionC;
}
