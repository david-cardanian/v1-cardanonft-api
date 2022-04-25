package com.cardanonft.api.vo.whitelist;

import com.cardanonft.api.entity.enums.WhitelistType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhitelistVO {
    private WhitelistType type;
    private String stakeAddress;
    private String luck;
}
