package com.cardanonft.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ApiModel
@Getter
@Setter
public class UserImageUploadRequest {
    @ApiModelProperty(required = false,notes = "files")
    private List<MultipartFile> files;
    @ApiModelProperty(required = false,notes = "userId")
    private String userId;
    @ApiModelProperty(required = false,notes = "mapParcelId")
    private Integer mapParcelId;
    /*
    1 : east, 2 : west, 3 : south, 4 : north
*/
    @ApiModelProperty(required = false,notes = "village_direction")
    private String villageDirection;
}


