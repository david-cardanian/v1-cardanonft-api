package com.cardanonft.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "webgame_scoreboard", schema = "cardano", catalog = "")
public class WebgameScoreboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;
    private String nickName;

    private Integer gameId;

    private Integer score;

    private String gameHash;

    private boolean enabled = true;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;
}
