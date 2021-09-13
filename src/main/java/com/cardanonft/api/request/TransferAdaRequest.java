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
public class TransferAdaRequest {
    @ApiModelProperty(required = false,notes = "fromAddress")
    private String fromAddress;

    @ApiModelProperty(required = false,notes = "toAddress")
    private String toAddress;

    @ApiModelProperty(required = false,notes = "sendAmt")
    private long sendAmt;

    @ApiModelProperty(required = false,notes = "sendAllYn")
    private boolean sendAllYn;

    @ApiModelProperty(required = false,notes = "exceptFeeYn")
    private boolean exceptFeeYn;
}
