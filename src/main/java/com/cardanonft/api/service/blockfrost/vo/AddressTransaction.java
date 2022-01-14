package com.cardanonft.api.service.blockfrost.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressTransaction {
    private String tx_hash;
    private String tx_index;
}
