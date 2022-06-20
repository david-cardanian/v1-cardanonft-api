package com.cardanonft.api.request.game;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class TestRequest {
    private String id;
    private String password;
}
