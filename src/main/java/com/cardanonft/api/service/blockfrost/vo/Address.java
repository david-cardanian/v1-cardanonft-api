package com.cardanonft.api.service.blockfrost.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Address {
    private String address;
    private List<Amount> amount;
    private String stake_address;
    private String type;
    private String script;
    @Data
    @Getter
    @Setter
    public class Amount {
        private String unit;
        private String quantity;
    }
}
