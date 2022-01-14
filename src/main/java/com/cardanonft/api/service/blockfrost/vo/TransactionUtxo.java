package com.cardanonft.api.service.blockfrost.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class TransactionUtxo {
    private String hash;
    private List<Amount> amount;
    private List<Tran> inputs;
    private List<Tran> outputs;
    @Data
    @Getter
    @Setter
    public class Tran{
        private String address;
        private String tx_hash;
        private List<Amount> amount;
        private String output_index;
    }
    @Data
    @Getter
    @Setter
    public class Amount{
        private String unit;
        private String quantity;
    }
}
