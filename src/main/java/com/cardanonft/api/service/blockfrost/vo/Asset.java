package com.cardanonft.api.service.blockfrost.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Data
@Getter
@Setter
public class Asset {
    private String asset;
    private String policy_id;
    private String asset_name;
    private String fingerprint;
    private String quantity;
    private String initial_mint_tx_hash;
    private int mint_or_burn_count;
    private JSONObject onchain_metadata;
    private JSONObject metadata;
}
